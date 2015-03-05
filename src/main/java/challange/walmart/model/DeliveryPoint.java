package challange.walmart.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Romero Meireles on 02/03/15.
 */

@Entity(name = "delivery_point")
public class DeliveryPoint implements Comparable<DeliveryPoint> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.EAGER)
	private List<PointsPath> adjacencies = new LinkedList<PointsPath>();
	@OneToOne
	@JoinTable(name = "previous_point_id")
	private DeliveryPoint previousDeliveryPoint;
	@Column(name = "min_distance")
	private Double minDistance = Double.POSITIVE_INFINITY;

	public DeliveryPoint() {
	}

	public DeliveryPoint(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public int compareTo(DeliveryPoint other) {
		return Double.compare(minDistance, other.minDistance);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PointsPath> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(List<PointsPath> adjacencies) {
		this.adjacencies = adjacencies;
	}

	public DeliveryPoint getPreviousDeliveryPoint() {
		return previousDeliveryPoint;
	}

	public void setPreviousDeliveryPoint(DeliveryPoint previousDeliveryPoint) {
		this.previousDeliveryPoint = previousDeliveryPoint;
	}

	public Double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Double minDistance) {
		this.minDistance = minDistance;
	}
}
