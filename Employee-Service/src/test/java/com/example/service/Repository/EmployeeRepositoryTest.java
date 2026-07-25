package com.example.service.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.service.entity.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testSaveEmployee() {
		Employee employee =new Employee();
		employee.setFirstName("John");
		employee.setEmail("john@gmail.com");
		employee.setLastName("Doe");
		employee.setPhone("9876543211");
		employee.setSalary(50000.0);
		employee.setDesignation("Developer");
		
		Employee saved=employeeRepository.save(employee);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assertEquals("John",saved.getFirstName());
	}
	
	@Test
	public void testFindByid() {
		Employee employee =new Employee();
		employee.setFirstName("John");
		employee.setEmail("john@gmail.com");
		employee.setLastName("Doe");
		employee.setPhone("9876543211");
		employee.setSalary(50000.0);
		employee.setDesignation("Developer");
		
		Employee saved=employeeRepository.save(employee);
		Optional<Employee> result =employeeRepository.findById(saved.getId());
		assertTrue(result.isPresent());
		assertEquals("John", result.get().getFirstName());
	}
	
	@Test
	public void testFindAll() {
		Employee emp1 =new Employee();
		emp1.setFirstName("John");
		emp1.setEmail("john1@gmail.com");
		emp1.setPhone("11111111111");
		emp1.setSalary(50000.0);
		
		Employee emp2 =new Employee();
		emp2.setFirstName("David");
		emp2.setEmail("david@gmail.com");
		emp2.setPhone("2222222222");
		emp2.setSalary(60000.0);
		
		employeeRepository.save(emp1);
		employeeRepository.save(emp2);
		
		List<Employee> employees =employeeRepository.findAll();
		assertEquals(6,employees.size());
		
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee emp1 =new Employee();
		emp1.setFirstName("John");
		emp1.setEmail("john1@gmail.com");
		emp1.setPhone("11111111111");
		emp1.setSalary(50000.0);
		Employee saved = employeeRepository.save(emp1);
		
		saved.setFirstName("Peter");
		
		Employee updated =employeeRepository.save(saved);
		assertEquals("Peter", updated.getFirstName());
	}
	
	@Test
	public void testDeleteEmployee() {
		Employee emp1 =new Employee();
		emp1.setFirstName("John");
		emp1.setEmail("john1@gmail.com");
		emp1.setPhone("11111111111");
		emp1.setSalary(50000.0);
		Employee saved = employeeRepository.save(emp1);
		employeeRepository.deleteById(saved.getId());
		
		Optional<Employee> result=employeeRepository.findById(saved.getId());
		assertFalse(result.isPresent());		
	}
}
