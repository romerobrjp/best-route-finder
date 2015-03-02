package challange.walmart.rest.server;

import challange.walmart.model.LogisticsMap;
import challange.walmart.repository.DeliveryRepository;
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
	DeliveryRepository deliveryRepository;

    @RequestMapping(
			consumes = { MediaType.WILDCARD },
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON },
            value = "/delivery"
    )
	@Transactional
    public LogisticsMap createDeliveryMap(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "point_a", required = true) String pointA,
            @RequestParam(value = "point_b", required = true) String pointB,
            @RequestParam(value = "distance", required = true) Float distance
    ) {
        LogisticsMap logisticsMap = new LogisticsMap();
        logisticsMap.setName(name);
        logisticsMap.setOriginPoint(pointA);
        logisticsMap.setDestinyPoint(pointB);
        logisticsMap.setDistance(distance);

		this.deliveryRepository.save(logisticsMap);

        return logisticsMap;
    }
}
