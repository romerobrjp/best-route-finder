package challange.walmart.service;

import challange.walmart.model.DeliveryPoint;

import java.util.List;

/**
 * Created by Romero Meireles on 02/03/15.
 */
public interface LogisticsService {
	public List<DeliveryPoint> calculateBestRoute(DeliveryPoint origin, DeliveryPoint destiny, Float autonomy, Float fuelPrice);
}
