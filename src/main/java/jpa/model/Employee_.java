package jpa.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {
	
	public static volatile SingularAttribute<Employee, Integer> id;
	public static volatile SingularAttribute<Employee, Integer> cpr;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Character> gender;
	public static volatile SingularAttribute<Employee, Short> age;
	public static volatile SingularAttribute<Employee, Integer> mobile;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, Double> salary;
	public static volatile SingularAttribute<Employee, Address> address;
}