package challange.walmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Romero Meireles on 02/03/15.
 */
@Entity
@Table(name = "logistics_map")
public class DeliveryPath {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "origin_delivery_point")
	@JsonIgnore
	private DeliveryPoint originDeliveryPoint;
	@OneToOne
	@JoinColumn(name = "destiny_delivery_point")
	@JsonIgnore
	private DeliveryPoint destinyDeliveryPoint;
	@Column(nullable = false)
	private Double distance;

	public DeliveryPath() {
	}

	public DeliveryPath(String name) {
		this.name = name;
	}

	public DeliveryPath(String name, DeliveryPoint originDeliveryPoint, DeliveryPoint destinyDeliveryPoint, Double distance) {
		this.name = name;
		this.originDeliveryPoint = originDeliveryPoint;
		this.destinyDeliveryPoint = destinyDeliveryPoint;
		this.distance = distance;
	}

	public DeliveryPath(DeliveryPoint destinyDeliveryPoint, Double distance) {
		this.destinyDeliveryPoint = destinyDeliveryPoint;
		this.distance = distance;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
}