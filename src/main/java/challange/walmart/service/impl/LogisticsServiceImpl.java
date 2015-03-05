package challange.walmart.service.impl;

import challange.walmart.internals.djikstra.DjikstraCustom;
import challange.walmart.model.DeliveryPoint;
import challange.walmart.model.PointsPath;
import challange.walmart.repository.PointsPathRepository;
import challange.walmart.repository.DeliveryPointRepository;
import challange.walmart.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Romero Meireles on 02/03/15.
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {
	@Autowired
	PointsPathRepository pointsPathRepository;
	@Autowired
	DeliveryPointRepository deliveryPointRepository;
	@Autowired
	DjikstraCustom djikstraCustom;

	@Override
	public List<DeliveryPoint> calculateBestRoute(DeliveryPoint origin, DeliveryPoint destiny, Float autonomy, Float fuelPrice) {
		List<DeliveryPoint> bestPath = null;

		djikstraCustom.computePathsFromOrigin(origin);
		destiny = deliveryPointRepository.findByName(destiny.getName());
		System.out.println("Distance from " + origin + " to " + destiny + ": " + destiny.getMinDistance());
		bestPath = djikstraCustom.getShortestPathToDestiny(destiny);
		System.out.println("Path: " + bestPath);

		return bestPath;
	}
}
