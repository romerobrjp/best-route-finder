package challange.walmart.model;

import javax.persistence.*;

/**
 * Created by Romero Meireles on 28/02/15.
 */

@Entity
@Table(name = "logistics_map")
public class LogisticsMap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne
	@JoinColumn(name = "origin_point_id", nullable = false)
	private DeliveryPoint originDeliveryPoint;
	@OneToOne
	@JoinColumn(name = "destiny_point_id", nullable = false)
	private DeliveryPoint destinyDeliveryPoint;
	@Column(nullable = false)
	private Float distance;

	public LogisticsMap() {
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

	public DeliveryPoint getOriginDeliveryPoint() {
		return originDeliveryPoint;
	}

	public void setOriginDeliveryPoint(DeliveryPoint originDeliveryPoint) {
		this.originDeliveryPoint = originDeliveryPoint;
	}

	public DeliveryPoint getDestinyDeliveryPoint() {
		return destinyDeliveryPoint;
	}

	public void setDestinyDeliveryPoint(DeliveryPoint destinyDeliveryPoint) {
		this.destinyDeliveryPoint = destinyDeliveryPoint;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}
}
