package challange.walmart.service;

import challange.walmart.dto.BestRoutDTO;
import challange.walmart.model.DeliveryPoint;

/**
 * Created by Romero Meireles on 02/03/15.
 */
public interface LogisticsService {
	public BestRoutDTO calculateBestRoute(DeliveryPoint origin, DeliveryPoint destiny, Double autonomy, Double fuelPrice);
}
