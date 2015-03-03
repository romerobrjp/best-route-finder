package challange.walmart.model;

import javax.persistence.*;

/**
 * Created by Romero Meireles on 02/03/15.
 */
@Entity
@Table(name = "point_route")
public class PointRoute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@OneToOne
	@JoinColumn(name = "destiny_point", nullable = false)
	public Point destinyPoint;
	@Column(nullable = false)
	public double distance;

	public PointRoute() {
	}

	public PointRoute(Point destinyPoint, double distance) {
		this.destinyPoint = destinyPoint;
		this.distance = distance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getDestinyPoint() {
		return destinyPoint;
	}

	public void setDestinyPoint(Point destinyPoint) {
		this.destinyPoint = destinyPoint;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
