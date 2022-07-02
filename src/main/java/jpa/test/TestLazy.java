package jpa.test;

import jpa.model.Manager;
import jpa.repository.EmployeeRepository;

public class TestLazy {

	public static void main(String[] args) {
		
		EmployeeRepository employeeRepository = new EmployeeRepository();
		
		Manager employee = new Manager();
		employee.setId(39);
		
		employee = employeeRepository.getEmployee(employee);
		System.out.println("============================");
		System.out.println(employee.getAddress());
		System.out.println("============================");
		System.out.println(employee.getCertificates());
	}
}
