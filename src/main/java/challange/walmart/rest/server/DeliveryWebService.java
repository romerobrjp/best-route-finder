package challange.walmart.rest.server;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.model.PointsPath;
import challange.walmart.repository.DeliveryPointRepository;
import challange.walmart.repository.PointsPathRepository;
import challange.walmart.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Romero Meireles on 28/02/15.
 */

@RestController
public class DeliveryWebService implements Serializable {

	@Autowired
	LogisticsService logisticsService;
	@Autowired
	DeliveryPointRepository deliveryPointRepository;
	@Autowired
	PointsPathRepository pointsPathRepository;

    @RequestMapping(
			consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE },
			headers = "Content-Type=application/json",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            value = "/delivery"
    )
	@Transactional
    public PointsPath createLogisticsMap(
            @RequestParam(value = "name", required = true) String mapName,
            @RequestParam(value = "origin_point", required = true) String originPointName,
            @RequestParam(value = "destiny_point", required = true) String destinyPointName,
            @RequestParam(value = "distance", required = true) Double distance
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
		consumes = MediaType.ALL_VALUE,
		headers = "Content-Type=application/json;charset=UTF-8",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE,
		value = "/delivery"
	)
	public BestRoutDTO getShortestRouteTo(
		@RequestParam(value = "origin_point", required = true) String originPointName,
		@RequestParam(value = "destiny_point", required = true) String destinyPointName,
		@RequestParam(value = "autonomy", required = true) Double autonomy,
		@RequestParam(value = "fuel_price", required = true) Double fuelPrice
	) {
		BestRoutDTO bestRout;

		DeliveryPoint originPoint = this.deliveryPointRepository.findByName(originPointName);
		DeliveryPoint destinyPoint = this.deliveryPointRepository.findByName(destinyPointName);

		bestRout = this.logisticsService.calculateBestRoute(originPoint, destinyPoint, autonomy, fuelPrice);

		return bestRout;
	}
}
