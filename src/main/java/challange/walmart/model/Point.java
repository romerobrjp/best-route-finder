package challange.walmart.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Romero Meireles on 02/03/15.
 */

@Entity
public class Point implements Comparable<Point> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(nullable = false)
	public String name;
	@OneToMany
	@JoinColumn(name = "point_route_id")
	public List<PointRoute> adjacencies;
	@OneToOne
	@JoinColumn(name = "previous_city")
	public Point previousPoint;
	@Transient
	public Double minDistance = Double.POSITIVE_INFINITY;

	public Point() {
	}

	public Point(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Point other) {
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

	public List<PointRoute> getAdjacencies() {
		return adjacencies;
	}

	public void setAdjacencies(List<PointRoute> adjacencies) {
		this.adjacencies = adjacencies;
	}

	public Point getPreviousPoint() {
		return previousPoint;
	}

	public void setPreviousPoint(Point previousPoint) {
		this.previousPoint = previousPoint;
	}

	public Double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Double minDistance) {
		this.minDistance = minDistance;
	}
}
