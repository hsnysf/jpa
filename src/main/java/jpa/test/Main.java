package jpa.test;

import java.util.ArrayList;

import jpa.model.Address;
import jpa.model.Certificate;
import jpa.model.Manager;
import jpa.repository.EmployeeRepository;

public class Main {

	public static void main(String[] args) {
		
		EmployeeRepository employeeRepository = new EmployeeRepository();
		
		employeeRepository.begin();
		
		Address address = new Address();
		address.setBuilding(1);
		address.setRoad(2);
		address.setBlock(3);
		address.setCity("Manama");
		
		Manager employee = new Manager();
		employee.setAllowance(100.0);
		employee.setDegree(3);
		
		employee.setCpr(123);
		employee.setName("Hasan");
		employee.setGender('M');
		employee.setAge((short)20);
		employee.setMobile(123456789);
		employee.setEmail("abc@gmail.com");
		employee.setSalary(300.5);
		employee.setAddress(address);
		
		Certificate certificate1 = new Certificate();
		certificate1.setTitle("Postgre");
		certificate1.setYear((short)2010);
		certificate1.setEmployee(employee);
		
		Certificate certificate2 = new Certificate();
		certificate2.setTitle("Java EE");
		certificate2.setYear((short)2000);
		certificate2.setEmployee(employee);
		
		employee.setCertificates(new ArrayList<Certificate>());
		employee.getCertificates().add(certificate1);
		employee.getCertificates().add(certificate2);
		
		employeeRepository.addEmployee(employee);

		employeeRepository.commit();
		employeeRepository.begin();
		
		address.setBuilding(4);
		address.setRoad(5);
		address.setBlock(6);
		address.setCity("Aali");
		
		employee.setAllowance(200.0);
		employee.setDegree(4);
		
		employee.setCpr(1234);
		employee.setName("Fatima");
		employee.setGender('F');
		employee.setAge((short)30);
		employee.setMobile(987654321);
		employee.setEmail("test@gmail.com");
		employee.setSalary(450.6);
		
		certificate1.setTitle("DB2");
		certificate1.setYear((short)2005);
		
		certificate2.setTitle("Java");
		certificate2.setYear((short)2020);
		
		employeeRepository.updateEmployee(employee);
		employeeRepository.commit();
		employeeRepository.begin();
		
		System.out.println(employeeRepository.getEmployee(new Manager(employee.getId())));
		
		employeeRepository.deleteEmployee(employee);
		
		System.out.println(employeeRepository.getEmployeeList());
		
		System.out.println(employeeRepository.searchEmployeeList(employee));
		
		employeeRepository.getAverageSalaryByGender();
		
		employeeRepository.getEmployeeListByCity();
		
		employeeRepository.commit();
	}
}
