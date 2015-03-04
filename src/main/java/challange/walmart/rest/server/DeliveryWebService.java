package challange.walmart.rest.server;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.model.PointsPath;
import challange.walmart.repository.DeliveryPointRepository;
import challange.walmart.repository.PointsPathRepository;
import challange.walmart.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Romero Meireles on 28/02/15.
 */

@RestController
public class DeliveryWebService implements Serializable {

	@Autowired
	DeliveryService deliveryService;
	@Autowired
	DeliveryPointRepository deliveryPointRepository;
	@Autowired
	PointsPathRepository pointsPathRepository;

    @RequestMapping(
			consumes = { MediaType.APPLICATION_FORM_URLENCODED },
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON },
            value = "/delivery"
    )
	@Transactional
    public PointsPath createLogisticsMap(
            @RequestParam(value = "name", required = true) String mapName,
            @RequestParam(value = "origin_point", required = true) String originPointName,
            @RequestParam(value = "destiny_point", required = true) String destinyPointName,
            @RequestParam(value = "distance", required = true) Float distance
    ) {
		DeliveryPoint originPoint = deliveryPointRepository.findByName(originPointName);
		DeliveryPoint destinyPoint = deliveryPointRepository.findByName(destinyPointName);
		PointsPath pointsPathOrigin = new PointsPath(mapName);

		if (destinyPoint == null) {
			destinyPoint = new DeliveryPoint(destinyPointName);
		}
		else {
			if (originPoint != null) {
				originPoint.setPreviousDeliveryPoint(destinyPoint);
			}
		}

		if (originPoint == null) {
			originPoint = new DeliveryPoint(originPointName);

			pointsPathOrigin.setDistance(distance);
			pointsPathOrigin.setOriginDeliveryPoint(originPoint);
			pointsPathOrigin.setDestinyDeliveryPoint(destinyPoint);

			originPoint.getAdjacencies().add(pointsPathOrigin);

			this.pointsPathRepository.save(pointsPathOrigin);
		}
		else {
			if (originPoint.getAdjacencies() != null) {
				boolean alreadyHasDestinyAdjacency = false;

				for (Iterator<PointsPath> ppIt  = originPoint.getAdjacencies().iterator(); ppIt.hasNext();) {
					PointsPath pp = ppIt.next();
					if (pp.getDestinyDeliveryPoint().getName().equals(destinyPoint)) {
						alreadyHasDestinyAdjacency = true;
						break;
					}
				}

				if (!alreadyHasDestinyAdjacency) {
					pointsPathOrigin = new PointsPath(mapName);
					pointsPathOrigin.setDistance(distance);
					pointsPathOrigin.setOriginDeliveryPoint(originPoint);
					pointsPathOrigin.setDestinyDeliveryPoint(destinyPoint);

					originPoint.getAdjacencies().add(pointsPathOrigin);

					this.pointsPathRepository.save(pointsPathOrigin);
				}
			}
			else {
				pointsPathOrigin = new PointsPath(mapName);
				pointsPathOrigin.setDistance(distance);
				pointsPathOrigin.setOriginDeliveryPoint(originPoint);
				pointsPathOrigin.setDestinyDeliveryPoint(destinyPoint);

				originPoint.getAdjacencies().add(pointsPathOrigin);

				this.pointsPathRepository.save(pointsPathOrigin);
			}
		}

		this.deliveryPointRepository.save(originPoint);
		this.deliveryPointRepository.save(destinyPoint);

		return pointsPathOrigin;
    }

	@RequestMapping(
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON,
		value = "/delivery"
	)
	public List<DeliveryPoint> getShortestRouteTo(
		@RequestParam(value = "origin_point", required = true) String originPointName,
		@RequestParam(value = "destiny_point", required = true) String destinyPointName,
		@RequestParam(value = "autonomy", required = true) Float autonomy,
		@RequestParam(value = "fuel_price", required = true) Float fuelPrice
	) {
		BestRoutDTO bestRout = new BestRoutDTO();
		List<DeliveryPoint> path = null;

		DeliveryPoint originPoints = this.deliveryPointRepository.findByName(originPointName);
		DeliveryPoint destinyPoints = this.deliveryPointRepository.findByName(destinyPointName);

		path = this.deliveryService.calculateBestRoute(originPoints, destinyPoints, autonomy, fuelPrice);

		return path;
	}
}
