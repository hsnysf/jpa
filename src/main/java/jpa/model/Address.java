package jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addr_id")
	private Integer id;
	@Column(name = "addr_building")
	private Integer building;
	@Column(name = "addr_road")
	private Integer road;
	@Column(name = "addr_block")
	private Integer block;
	@Column(name = "addr_city")
	private String city;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getBuilding() {
		return building;
	}
	
	public void setBuilding(Integer building) {
		this.building = building;
	}
	
	public Integer getRoad() {
		return road;
	}
	
	public void setRoad(Integer road) {
		this.road = road;
	}
	
	public Integer getBlock() {
		return block;
	}
	
	public void setBlock(Integer block) {
		this.block = block;
	}
	
	public String getCity() {
		return city;
	}
	
	public String myCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", building=" + building + ", road=" + road + ", block=" + block + ", city=" + city
				+ "]";
	}
}
