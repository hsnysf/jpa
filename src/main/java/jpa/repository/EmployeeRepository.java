package jpa.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import jpa.model.Address;
import jpa.model.Address_;
import jpa.model.Employee;
import jpa.model.Employee_;
import jpa.model.Manager;

public class EmployeeRepository {

	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public EmployeeRepository() {
		
		factory = Persistence.createEntityManagerFactory("employee");
		
		manager = factory.createEntityManager();
	}
	
	public void begin() {
		
		manager.getTransaction().begin();
	}
	
	public void commit() {
		
		manager.getTransaction().commit();
	}
	
	public Employee addEmployee(Employee employee) {
		
		manager.persist(employee);
		
		/*
		//Query query = manager.createNativeQuery("insert into employee(emp_cpr, emp_name, emp_gender, emp_age, emp_email, emp_mobile, emp_salary) values(:cpr, :name, :gender, :age, :email, :mobile, :salary) returning emp_id");
		Query query = manager.createNamedQuery("insertEmployeeNative");
		query.setParameter("cpr", employee.getCpr());
		query.setParameter("name", employee.getName());
		query.setParameter("gender", employee.getGender());
		query.setParameter("age", employee.getAge());
		query.setParameter("email", employee.getEmail());
		query.setParameter("mobile", employee.getMobile());
		query.setParameter("salary", employee.getSalary());
		//employee.setId(Integer.parseInt(query.getSingleResult().toString()));
		*/
		
		return employee;
	}
	
	public Employee updateEmployee(Employee employee) {

		manager.merge(employee);

		/*
		Query query = manager.createQuery("UPDATE Employee SET cpr = :cpr, name = :name, gender = :gender, age = :age, email = :email, mobile = :mobile, salary = :salary WHERE id = :id");
		//Query query = manager.createNamedQuery("updateEmployee");
		//Query query = manager.createNativeQuery("update employee set emp_cpr = :cpr, emp_name = :name, emp_gender = :gender, emp_age = :age, emp_email = :email, emp_mobile = :mobile, emp_salary = :salary where emp_id = :id");
		//Query query = manager.createNamedQuery("updateEmployeeNative");
		query.setParameter("cpr", employee.getCpr());
		query.setParameter("name", employee.getName());
		query.setParameter("gender", employee.getGender());
		query.setParameter("age", employee.getAge());
		query.setParameter("email", employee.getEmail());
		query.setParameter("mobile", employee.getMobile());
		query.setParameter("salary", employee.getSalary());
		query.setParameter("id", employee.getId());
		query.executeUpdate();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaUpdate<Employee> criteria = builder.createCriteriaUpdate(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);
        
        criteria.set(root.get(Employee_.cpr), employee.getCpr());
        criteria.set(root.get(Employee_.name), employee.getName());
        criteria.set(root.get(Employee_.gender), employee.getGender());
        criteria.set(root.get(Employee_.age), employee.getAge());
        criteria.set(root.get(Employee_.mobile), employee.getMobile());
        criteria.set(root.get(Employee_.email), employee.getEmail());
        criteria.set(root.get(Employee_.salary), employee.getSalary());
        criteria.where(builder.equal(root.get(Employee_.id), employee.getId()));
        
        Query query = manager.createQuery(criteria);
        
        query.executeUpdate();
		*/
		
		return employee;
	}
	
	public Employee deleteEmployee(Employee employee) {
		
		manager.remove(employee);
		
		/*
		Query query = manager.createQuery("DELETE FROM Employee WHERE id = :id");
		//Query query = manager.createNamedQuery("deleteEmployee");
		//Query query = manager.createNativeQuery("delete from employee where emp_id = :id");
		//Query query = manager.createNamedQuery("deleteEmployeeNative");
		query.setParameter("id", employee.getId());
		query.executeUpdate();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaDelete<Employee> criteria = builder.createCriteriaDelete(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);
        
        criteria.where(builder.equal(root.get(Employee_.id), employee.getId()));
        
        Query query = manager.createQuery(criteria);
        
        query.executeUpdate();
		*/
		
		return employee;
	}
	
	public Manager getEmployee(Manager employee) {
		
		employee = manager.find(Manager.class, employee.getId());
		
		/*
		Query query = manager.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
		//Query query = manager.createNamedQuery("getEmployee", Employee.class);
		//Query query = manager.createNativeQuery("select * from employee where emp_id = :id", Employee.class);
		//Query query = manager.createNamedQuery("getEmployeeNative", Employee.class);
		
		query.setParameter("id", employee.getId());
		employee = (Employee) query.getSingleResult();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		criteria.select(root).where(builder.equal(root.get(Employee_.id), employee.getId()));
		TypedQuery<Employee> typedQuery = manager.createQuery(criteria);
		employee = typedQuery.getSingleResult();
		*/
		
		return employee;
	}
	
	public List<Employee> getEmployeeList() {
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		/*
		Query query = manager.createQuery("SELECT e FROM Employee e ORDER BY e.id", Employee.class);
		//Query query = manager.createNamedQuery("getEmployeeList", Employee.class);
		//Query query = manager.createNativeQuery("select * from employee order by emp_id", Employee.class);
		//Query query = manager.createNamedQuery("getEmployeeListNative", Employee.class);
		employeeList = query.getResultList();
		*/
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		criteria.select(root).orderBy(builder.asc(root.get(Employee_.id)));
		TypedQuery<Employee> typedQuery = manager.createQuery(criteria);
		employeeList = typedQuery.getResultList();
		
		return employeeList;
	}
	
	public List<Employee> searchEmployeeList(Employee employee) {

		List<Employee> employeeList = new ArrayList<Employee>();
		
		/*
		StringBuilder builder = new StringBuilder();
		//builder.append("SELECT e FROM Employee e where e.id > 0");
		builder.append("select * from employee where emp_id > 0");
		
		if(employee.getAge() != null) {
			
			//builder.append(" and e.age = :age");
			builder.append(" and emp_age = :age");
		}
		
		if(employee.getGender() != null) {
			
			//builder.append(" and e.gender = :gender");
			builder.append(" and emp_gender = :gender");
		}
		
		builder.append(" limit 5 offset 1");
		
		//Query query = manager.createQuery(builder.toString(), Employee.class);
		Query query = manager.createNativeQuery(builder.toString(), Employee.class);
		
		if(employee.getAge() != null) {
			
			query.setParameter("age", employee.getAge());
		}
		
		if(employee.getGender() != null) {
			
			query.setParameter("gender", employee.getGender());
		}
		
		query.setFirstResult(1);
		query.setMaxResults(5);
		employeeList = query.getResultList();
		*/
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(employee.getAge() != null) {
			predicates.add(criteriaBuilder.equal(root.get(Employee_.age), employee.getAge()));
		}
		
		if(employee.getGender() != null) {
			predicates.add(criteriaBuilder.equal(root.get(Employee_.gender), employee.getGender()));
		}
		
		criteria.select(root).where(predicates.toArray(new Predicate[]{}));
		TypedQuery<Employee> typedQuery = manager.createQuery(criteria);
		
		typedQuery.setFirstResult(1);
		typedQuery.setMaxResults(5);
		employeeList = typedQuery.getResultList();
		
		return employeeList;
	}
	
	public void getAverageSalaryByGender() {
		
		/*
		Query query = manager.createQuery("SELECT e.gender, avg(e.salary), avg(e.age) FROM Employee e group by e.gender");
		//Query query = manager.createNamedQuery("getAverageSalaryByGender");
		//Query query = manager.createNativeQuery("select emp_gender, avg(emp_salary) as emp_salary_avg, avg(emp_age) as emp_age_avg from employee group by emp_gender");
		//Query query = manager.createNamedQuery("getAverageSalaryByGenderNative");
		
		//List<?> result = query.getResultList();
		*/
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<?> criteria = builder.createQuery();
		Root<Employee> root = criteria.from(Employee.class);
		criteria.multiselect(root.get(Employee_.gender), builder.avg(root.get(Employee_.salary)), builder.avg(root.get(Employee_.age))).groupBy(root.get(Employee_.gender));
		TypedQuery<?> typedQuery = manager.createQuery(criteria);
		List<?> result = typedQuery.getResultList();
		
		for(Object object : result) {
			
			System.out.println(Arrays.deepToString((Object[])object));
		}
	}
	
	public List<Employee> getEmployeeListByCity() {
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		/*
		Query query = manager.createQuery("SELECT e FROM Employee e WHERE e.address.city = :city ORDER BY e.id", Employee.class);
		//Query query = manager.createNamedQuery("getEmployeeListByCity", Employee.class);
		//Query query = manager.createNativeQuery("select * from employee, address where emp_address_id = addr_id and addr_city = :city order by emp_id", Employee.class);
		//Query query = manager.createNamedQuery("getEmployeeListByCityNative", Employee.class);
		//query.setParameter("city", "Aali");
		//employeeList = query.getResultList();
		*/
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		Join<Employee, Address> joinEmployeeAddress = root.join(Employee_.address);
		
		criteria.select(root)
		.where(builder.equal(joinEmployeeAddress.get(Address_.city), "Aali"))
		.orderBy(builder.asc(root.get(Employee_.id)));
		
		TypedQuery<Employee> typedQuery = manager.createQuery(criteria);
		employeeList = typedQuery.getResultList();
		
		return employeeList;
	}
}
