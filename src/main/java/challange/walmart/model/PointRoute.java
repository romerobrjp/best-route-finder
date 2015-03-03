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
	@JoinColumn(name = "destiny_point")
	public DeliveryPoint destinyDeliveryPoint;
	public double distance;

	public PointRoute() {
	}

	public PointRoute(DeliveryPoint destinyDeliveryPoint, double distance) {
		this.destinyDeliveryPoint = destinyDeliveryPoint;
		this.distance = distance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DeliveryPoint getDestinyDeliveryPoint() {
		return destinyDeliveryPoint;
	}

	public void setDestinyDeliveryPoint(DeliveryPoint destinyDeliveryPoint) {
		this.destinyDeliveryPoint = destinyDeliveryPoint;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
