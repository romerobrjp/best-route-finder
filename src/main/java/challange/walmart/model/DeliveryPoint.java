package challange.walmart.model;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Romero Meireles on 02/03/15.
 */

@Entity(name = "delivery_point")
@ApiObject(name = "DeliveryPoint", description = "Represents a point of a path")
public class DeliveryPoint implements Comparable<DeliveryPoint> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiObjectField(name = "id", description = "Generated automatically by database", required = true)
	private Long id;
	@Column(nullable = false)
	@ApiObjectField(name = "name", description = "Represents the name of the point", required = true)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@ApiObjectField(name = "adjacencies", description = "Represents the adjacent paths that a point has", required = true)
	private List<DeliveryPath> adjacencies = new LinkedList<DeliveryPath>();
	@OneToOne
	@JoinTable(name = "previous_point_id")
	//@ApiObjectField(name = "previous_point", description = "Indicates the previous point of the current point")
	private DeliveryPoint previousDeliveryPoint;
	@Column(name = "min_distance")
	@ApiObjectField(name = "name", description = "Represents the minimum distance between the current point and hist next")
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

	public List<DeliveryPath> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(List<DeliveryPath> adjacencies) {
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
