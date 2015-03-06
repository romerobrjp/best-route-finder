package challange.walmart.service;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPath;
import challange.walmart.model.DeliveryPoint;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Romero Meireles on 02/03/15.
 */
public interface LogisticsService {
	public void createLogisticsMap(String mapName,
								   String originPointName,
								   String destinyPointName,
								   Double distance);

	public BestRoutDTO calculateBestRoute(DeliveryPoint origin,
										  DeliveryPoint destiny,
										  Double autonomy,
										  Double fuelPrice);

	public DeliveryPoint findDeliveryPointByName(String name);

	public DeliveryPath findDeliveryPathByName(String name);
}
