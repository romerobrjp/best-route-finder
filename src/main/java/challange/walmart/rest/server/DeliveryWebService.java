package challange.walmart.rest.server;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPath;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.service.LogisticsService;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiVerb;
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


@Api(
	name="Logistics Map API",
	description="This application allows you to create Logistics Maps and also to" +
	" find the best route between two points of a Logistics Map")
@RestController
public class DeliveryWebService implements Serializable {

	@Autowired
	LogisticsService logisticsService;

	@ApiMethod(
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		description = "Creates a LogisticsMap",
		path = "/delivery",
		produces = MediaType.APPLICATION_JSON_VALUE,
		responsestatuscode = "200",
		verb = ApiVerb.POST
	)
	@ApiResponseObject(clazz = DeliveryPath.class)
    @RequestMapping(
			consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE },
			headers = "Content-Type=application/json",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE },
            value = "/delivery")
	@Transactional
    public DeliveryPath createLogisticsMap(
            @RequestParam(value = "map_name", required = true)
			@ApiQueryParam(name="map_name", description="Name of the Map to be created", required = true)
				String mapName,
            @RequestParam(value = "origin_point", required = true)
			@ApiQueryParam(name="origin_point", description="Name of the origin point to be registered in the Logistics Map", required=true)
				String originPointName,
            @RequestParam(value = "destiny_point", required = true)
			@ApiQueryParam(name="destiny_point", description="Name of the destiny point to be registered in the Logistics Map", required=true)
				String destinyPointName,
            @RequestParam(value = "distance", required = true)
			@ApiQueryParam(name="distance", description="The distance, in kilometers, between the origin point and the destiny point", required=true)
				Double distance
    ) {
		this.logisticsService.createLogisticsMap(mapName, originPointName, destinyPointName, distance);

		return this.logisticsService.findDeliveryPathByName(mapName);
    }

	@ApiMethod(
		consumes = MediaType.TEXT_PLAIN_VALUE,
		description = "Finds the best route between two points",
		path = "/delivery",
		produces = MediaType.APPLICATION_JSON_VALUE,
		responsestatuscode = "200",
		verb = ApiVerb.GET
	)
	@ApiResponseObject(clazz = BestRoutDTO.class)
	@RequestMapping(
		consumes = MediaType.ALL_VALUE,
		headers = "Content-Type=application/json;charset=UTF-8",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE,
		value = "/delivery")
	public BestRoutDTO getShortestRouteTo(
		@RequestParam(value = "origin_point", required = true)
		@ApiQueryParam(name="origin_point", description="Name of the origin point", required = true)
			String originPointName,
		@RequestParam(value = "destiny_point", required = true)
		@ApiQueryParam(name="destiny_point", description="Name of the destiny point", required = true)
			String destinyPointName,
		@RequestParam(value = "autonomy", required = true)
		@ApiQueryParam(name="autonomy", description="Vehicle autonomy value (numbers only)", required = true)
			Double autonomy,
		@RequestParam(value = "fuel_price", required = true)
		@ApiQueryParam(name="map_name", description="Fuel price (numbers only, splitted by dots", required = true)
			Double fuelPrice
	) {
		BestRoutDTO bestRout;

		DeliveryPoint originPoint = this.logisticsService.findDeliveryPointByName(originPointName);
		DeliveryPoint destinyPoint = this.logisticsService.findDeliveryPointByName(destinyPointName);

		bestRout = this.logisticsService.calculateBestRoute(originPoint, destinyPoint, autonomy, fuelPrice);

		return bestRout;
	}
}
