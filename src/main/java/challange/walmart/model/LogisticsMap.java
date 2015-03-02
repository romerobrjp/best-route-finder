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
	@Column(nullable = false)
	private String originPoint;
	@Column(nullable = false)
	private String destinyPoint;
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

	public String getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(String originPoint) {
		this.originPoint = originPoint;
	}

	public String getDestinyPoint() {
		return destinyPoint;
	}

	public void setDestinyPoint(String destinyPoint) {
		this.destinyPoint = destinyPoint;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}
}
