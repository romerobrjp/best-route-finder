package challange.walmart.internals.djikstra;

import challange.walmart.model.DeliveryPoint;
import challange.walmart.model.PointsPath;
import challange.walmart.repository.DeliveryPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * created by Romero Meireles on 04/03/15.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DjikstraCustom {
	@Autowired
	DeliveryPointRepository deliveryPointRepository;

	@Transactional
	public void computePathsFromOrigin(DeliveryPoint originPoint) {
		originPoint.setMinDistance(0.);
		PriorityQueue<DeliveryPoint> deliveryPointQueue = new PriorityQueue<DeliveryPoint>();
		deliveryPointQueue.add(originPoint);

		while (!deliveryPointQueue.isEmpty()) {
			DeliveryPoint polledPoint = deliveryPointQueue.poll();

			// Visit each Path exiting polledPoint
			for (PointsPath polledPointPath : polledPoint.getAdjacencies()) {
				DeliveryPoint polledPointPathDestiny = polledPointPath.getDestinyDeliveryPoint();
				Double distanceThroughU = polledPoint.getMinDistance() + polledPointPath.getDistance();

				if (distanceThroughU < polledPointPathDestiny.getMinDistance()) {
					deliveryPointQueue.remove(polledPointPathDestiny);
					polledPointPathDestiny.setMinDistance(distanceThroughU);
					polledPointPathDestiny.setPreviousDeliveryPoint(polledPoint);

					//updating  minDistance
					deliveryPointRepository.save(polledPointPathDestiny);
					deliveryPointQueue.add(polledPointPathDestiny);
				}
			}
		}
	}

	public List<DeliveryPoint> getShortestPathToDestiny(DeliveryPoint deliveryPointDestiny) {
		List<DeliveryPoint> path = new ArrayList<DeliveryPoint>();

		for (DeliveryPoint deliveryPoint = deliveryPointDestiny; deliveryPoint != null; deliveryPoint = deliveryPoint.getPreviousDeliveryPoint()) {
			path.add(deliveryPoint);
		}

		Collections.reverse(path);
		return path;
	}

	public Double calculateCost(Double distance, Double fuel_price, Double autonomy) {
		return ((distance / autonomy) * fuel_price);
	}
}
