package jpa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "employee_type")
@DiscriminatorValue("employee")
@NamedQueries({
	@NamedQuery(name = "getEmployee", query = "SELECT e FROM Employee e WHERE e.id = :id"),
	@NamedQuery(name = "getEmployeeList", query = "SELECT e FROM Employee e ORDER BY e.id"),
	@NamedQuery(name = "getAverageSalaryByGender", query = "SELECT e.gender, avg(e.salary), avg(e.age) FROM Employee e group by e.gender"),
	@NamedQuery(name = "updateEmployee", query = "UPDATE Employee SET cpr = :cpr, name = :name, gender = :gender, age = :age, email = :email, mobile = :mobile, salary = :salary WHERE id = :id"),
	@NamedQuery(name = "deleteEmployee", query = "DELETE FROM Employee WHERE id = :id"),
	@NamedQuery(name = "getEmployeeListByCity", query = "SELECT e FROM Employee e WHERE e.address.city = :city ORDER BY e.id"),
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "getEmployeeNative", query = "select * from employee where emp_id = :id"),
	@NamedNativeQuery(name = "getEmployeeListNative", query = "select * from employee order by emp_id"),
	@NamedNativeQuery(name = "getAverageSalaryByGenderNative", query = "select emp_gender, avg(emp_salary) as emp_salary_avg, avg(emp_age) as emp_age_avg from employee group by emp_gender"),
	@NamedNativeQuery(name = "insertEmployeeNative", query = "insert into employee(emp_cpr, emp_name, emp_gender, emp_age, emp_email, emp_mobile, emp_salary) values(:cpr, :name, :gender, :age, :email, :mobile, :salary) returning emp_id"),
	@NamedNativeQuery(name = "updateEmployeeNative", query = "update employee set emp_cpr = :cpr, emp_name = :name, emp_gender = :gender, emp_age = :age, emp_email = :email, emp_mobile = :mobile, emp_salary = :salary where emp_id = :id"),
	@NamedNativeQuery(name = "deleteEmployeeNative", query = "delete from employee where emp_id = :id"),
	@NamedNativeQuery(name = "getEmployeeListByCityNative", query = "select * from employee, address where emp_address_id = addr_id and addr_city = :city order by emp_id"),
})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer id;
	@Column(name = "emp_cpr")
	private Integer cpr;
	@Column(name = "emp_name")
	private String name;
	@Column(name = "emp_gender")
	private Character gender;
	@Column(name = "emp_age")
	private Short age;
	@Column(name = "emp_mobile")
	private Integer mobile;
	@Column(name = "emp_email")
	private String email;
	@Column(name = "emp_salary")
	private Double salary;
	@JoinColumn(name = "emp_address_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Certificate> certificates;
	
	public Employee() {
		
	}
	
	public Employee(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCpr() {
		return cpr;
	}
	
	public void setCpr(Integer cpr) {
		this.cpr = cpr;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Character getGender() {
		return gender;
	}
	
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public Short getAge() {
		return age;
	}
	
	public void setAge(Short age) {
		this.age = age;
	}
	
	public Integer getMobile() {
		return mobile;
	}
	
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Certificate> getCertificates() {
		return certificates;
	}
	
	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", cpr=" + cpr + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", mobile=" + mobile + ", email=" + email + ", salary=" + salary + ", address=" + address
				+ ", certificates=" + certificates + "]";
	}
}