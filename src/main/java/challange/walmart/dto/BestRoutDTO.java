package challange.walmart.dto;

import challange.walmart.model.DeliveryPoint;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Romero Meireles on 02/03/15.
 */
public class BestRoutDTO  {
	@JsonProperty("bestPath")
	private List<DeliveryPoint> bestPath = new LinkedList<DeliveryPoint>();
	@JsonProperty("cost")
	private Double cost;

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
}
