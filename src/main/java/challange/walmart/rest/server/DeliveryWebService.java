package challange.walmart.rest.server;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.LogisticsMap;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.model.PointRoute;
import challange.walmart.repository.LogisticsMapRepository;
import challange.walmart.repository.DeliveryPointRepository;
import challange.walmart.repository.PointRouteRepository;
import challange.walmart.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 * Created by Romero Meireles on 28/02/15.
 */

@RestController
public class DeliveryWebService implements Serializable {

	@Autowired
	DeliveryService deliveryService;
	@Autowired
	LogisticsMapRepository logisticsMapRepository;
	@Autowired
	DeliveryPointRepository deliveryPointRepository;
	@Autowired
	PointRouteRepository pointRouteRepository;

    @RequestMapping(
			consumes = { MediaType.APPLICATION_FORM_URLENCODED },
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON },
            value = "/delivery"
    )
	@Transactional
    public LogisticsMap createDeliveryMap(
            @RequestParam(value = "name", required = true) String mapName,
            @RequestParam(value = "point_a", required = true) String originPointName,
            @RequestParam(value = "point_b", required = true) String destinyPointName,
            @RequestParam(value = "distance", required = true) Float distance
    ) {
        LogisticsMap logisticsMap = new LogisticsMap();
		logisticsMap.setName(mapName);

		DeliveryPoint deliveryPointA = new DeliveryPoint(originPointName);
		DeliveryPoint deliveryPointB = new DeliveryPoint(destinyPointName);

		PointRoute pointRouteA = new PointRoute();
		PointRoute pointRouteB = new PointRoute();

		deliveryPointA.getAdjacencies().add(pointRouteA);
		pointRouteA.setDestinyDeliveryPoint(deliveryPointB);
		pointRouteA.setDistance(distance);

		deliveryPointB.getAdjacencies().add(pointRouteB);
		deliveryPointB.setPreviousDeliveryPoint(deliveryPointA);

		this.deliveryPointRepository.save(deliveryPointA);
		this.deliveryPointRepository.save(deliveryPointB);

		this.pointRouteRepository.save(pointRouteA);
		this.pointRouteRepository.save(pointRouteB);

		logisticsMap.setName(mapName);
		logisticsMap.setOriginDeliveryPoint(deliveryPointA);
		logisticsMap.setDestinyDeliveryPoint(deliveryPointB);
		logisticsMap.setDistance(distance);

		this.logisticsMapRepository.save(logisticsMap);

        return logisticsMap;
    }

	@RequestMapping(
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON,
		value = "/delivery"
	)
	public BestRoutDTO getShortestRouteTo(
		@RequestParam(value = "origin_point", required = true) String originPointName,
		@RequestParam(value = "destiny_point", required = true) String destinyPointName,
		@RequestParam(value = "autonomy", required = true) Float autonomy,
		@RequestParam(value = "fuel_price", required = true) Float fuelPrice
	) {
		BestRoutDTO bestRout = new BestRoutDTO();

		DeliveryPoint originDeliveryPoint = this.deliveryPointRepository.findByName(originPointName);
		DeliveryPoint destinyDeliveryPoint = this.deliveryPointRepository.findByName(destinyPointName);

		bestRout = this.deliveryService.calculateBestRoute(originDeliveryPoint, destinyDeliveryPoint, autonomy, fuelPrice);

		return bestRout;
	}
}
