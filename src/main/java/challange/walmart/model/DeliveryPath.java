package challange.walmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;

/**
 * Created by Romero Meireles on 02/03/15.
 */
@Entity
@Table(name = "logistics_map")
@ApiObject(name = "LogisticsMap", description = "Represents a logistics map")
public class DeliveryPath {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiObjectField(name = "id")
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "origin_delivery_point")
	@JsonIgnore
	@ApiObjectField(name = "originDeliveryPoint", description = "Indicates the origin point in logistics the map", required = true)
	private DeliveryPoint originDeliveryPoint;
	@OneToOne
	@JoinColumn(name = "destiny_delivery_point")
	@JsonIgnore
	@ApiObjectField(name = "originDeliveryPoint", description = "Indicates the destiny point in the logistics map", required = true)
	private DeliveryPoint destinyDeliveryPoint;
	@Column(nullable = false)
	@ApiObjectField(name = "distance", description = "Indicates, in kilometers, the distance between two points in the logistics map", required = true)
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