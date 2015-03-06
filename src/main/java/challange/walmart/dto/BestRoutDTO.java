package challange.walmart.dto;

import challange.walmart.model.DeliveryPoint;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Romero Meireles on 02/03/15.
 */
public class BestRoutDTO  {
	private List<DeliveryPoint> bestPath = new LinkedList<DeliveryPoint>();
	private Double cost;
	private Double distance;

	public BestRoutDTO() {
	}

	public List<DeliveryPoint> getBestPath() {
		return bestPath;
	}

	public void setBestPath(List<DeliveryPoint> bestPath) {
		this.bestPath = bestPath;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(" >>> Path for best route: ");

		for (DeliveryPoint point : this.bestPath) {
			result.append(point.getName() + " > ");
		}

		result.append("\n >>> Total distance: " + this.distance);

		result.append("\n >>> Cost: " + this.cost);

		return result.toString();
	}
}
