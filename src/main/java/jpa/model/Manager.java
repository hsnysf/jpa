package jpa.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
@PrimaryKeyJoinColumn(name = "mngr_id")
@DiscriminatorValue("manager")
public class Manager extends Employee {

	@Column(name = "mngr_degree")
	private Integer degree;
	@Column(name = "mngr_allowance")
	private Double allowance;
	
	public Manager() {
		
	}
	
	public Manager(Integer id) {
		super(id);
	}
	
	public Integer getDegree() {
		return degree;
	}
	
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	
	public Double getAllowance() {
		return allowance;
	}
	
	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	@Override
	public String toString() {
		return "[degree=" + degree + ", allowance=" + allowance + ", employee=" + super.toString() + "]";
	}
}
