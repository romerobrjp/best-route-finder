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
	private Point originPoint;
	@OneToOne
	@JoinColumn(name = "destiny_point_id", nullable = false)
	private Point destinyPoint;
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

	public Point getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(Point originPoint) {
		this.originPoint = originPoint;
	}

	public Point getDestinyPoint() {
		return destinyPoint;
	}

	public void setDestinyPoint(Point destinyPoint) {
		this.destinyPoint = destinyPoint;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}
}
