package challange.walmart.rest.server;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPath;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by Romero Meireles on 28/02/15.
 */

@RestController
public class DeliveryWebService implements Serializable {

	@Autowired
	LogisticsService logisticsService;

    @RequestMapping(
			consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE },
			headers = "Content-Type=application/json",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            value = "/delivery"
    )
	@Transactional
    public DeliveryPath createLogisticsMap(
            @RequestParam(value = "name", required = true) String mapName,
            @RequestParam(value = "origin_point", required = true) String originPointName,
            @RequestParam(value = "destiny_point", required = true) String destinyPointName,
            @RequestParam(value = "distance", required = true) Double distance
    ) {
		this.logisticsService.createLogisticsMap(mapName, originPointName, destinyPointName, distance);

		return this.logisticsService.findDeliveryPathByName(mapName);
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

		DeliveryPoint originPoint = this.logisticsService.findDeliveryPointByName(originPointName);
		DeliveryPoint destinyPoint = this.logisticsService.findDeliveryPointByName(destinyPointName);

		bestRout = this.logisticsService.calculateBestRoute(originPoint, destinyPoint, autonomy, fuelPrice);

		return bestRout;
	}
}
