package challange.walmart.service.impl;

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

	@Override
	public List<DeliveryPoint> calculateBestRoute(DeliveryPoint origin, DeliveryPoint destiny, Float autonomy, Float fuelPrice) {
		List<DeliveryPoint> bestPath;

		this.computePathsFromOrigin(origin);
		System.out.println("Distance from " + origin + " to " + destiny + ": " + destiny.getMinDistance());
		bestPath = getShortestPathToDestiny(destiny);
		System.out.println("Path: " + bestPath);

		return bestPath;
	}

	public static void computePathsFromOrigin(DeliveryPoint originPoint) {
		originPoint.setMinDistance(0.);
		PriorityQueue<DeliveryPoint> deliveryPointQueue = new PriorityQueue<DeliveryPoint>();
		deliveryPointQueue.add(originPoint);

		while (!deliveryPointQueue.isEmpty()) {
			DeliveryPoint u = deliveryPointQueue.poll();

			// Visit each edge exiting u
			for (PointsPath pr : u.getAdjacencies())
			{
				DeliveryPoint p = pr.getDestinyDeliveryPoint();
				double distance = pr.getDistance();
				double distanceThroughU = u.getMinDistance() + distance;
				if (distanceThroughU < p.getMinDistance()) {
					deliveryPointQueue.remove(p);
					p.setMinDistance(distanceThroughU);
					p.setPreviousDeliveryPoint(u);
					deliveryPointQueue.add(p);
				}
			}
		}
	}

	public static List<DeliveryPoint> getShortestPathToDestiny(DeliveryPoint deliveryPointDestiny)
	{
		List<DeliveryPoint> path = new ArrayList<DeliveryPoint>();

		for (DeliveryPoint deliveryPoint = deliveryPointDestiny; deliveryPoint != null; deliveryPoint = deliveryPoint.getPreviousDeliveryPoint()) {
			path.add(deliveryPoint);
		}

		Collections.reverse(path);
		return path;
	}
}
