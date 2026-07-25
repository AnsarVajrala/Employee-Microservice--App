package com.example.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.service.Mapper.EmployeeMapper;
import com.example.service.Repository.EmployeeRepository;
import com.example.service.Service.EmployeeService;
import com.example.service.dto.EmployeeDTO;
import com.example.service.entity.Employee;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	@Mock 
	private EmployeeMapper employeeMapper;
	
	@Test
	public void testGetEmployeeById() {
		Employee emp =new Employee();
		emp.setId(1L);
		EmployeeDTO dto =new EmployeeDTO();
		dto.setId(1L);
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
		when(employeeMapper.toDTO(emp)).thenReturn(dto);
		EmployeeDTO result =employeeService.getEmployeeId(1L);
		assertNotNull(result);
		assertEquals(1L, result.getId());
		verify(employeeRepository).findById(1L);
	}
	
	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setEmail("john@gmail.com");
		employee.setLastName("Doe");
		employee.setPhone("9876543211");
		employee.setSalary(50000.0);
		employee.setDesignation("Developer");
		
		EmployeeDTO dto = new EmployeeDTO();
		dto.setFirstName("John");
		dto.setEmail("john@gmail.com");
		dto.setLastName("Doe");
		dto.setPhone("9876543211");
		dto.setSalary(50000.0);
		dto.setDesignation("Developer");
		
		when(employeeMapper.toEntity(dto)).thenReturn(employee);
		when(employeeMapper.toDTO(employee)).thenReturn(dto);
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		
		EmployeeDTO saved =employeeService.SaveEmployee(dto);
		assertEquals("John", saved.getFirstName());
		
		verify(employeeRepository).save(any(Employee.class));
		
	}
	
	@Test
	public void testGetAllEmployees() {
		
		List<Employee> list =List.of(
				new Employee(),
				new Employee());
		
		when(employeeRepository.findAll()).thenReturn(list);
		List<EmployeeDTO> result =employeeService.getAllEmployees();
		
		assertEquals(2,result.size());
		verify(employeeRepository).findAll();
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee employee =new Employee();
		employee.setId(1L);
		employee.setFirstName("John");
		
		EmployeeDTO dto =new EmployeeDTO();
		dto.setId(1L);
		dto.setFirstName("John");
		
		//when(employeeMapper.toEntity(dto)).thenReturn(employee);
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		when(employeeMapper.toDTO(employee)).thenReturn(dto);
		EmployeeDTO result =employeeService.updateEmployee(1L, dto);
		
		assertEquals("John",result.getFirstName());
		
	}
	
	@Test
	public void testDeleteEmployee() {
		doNothing().when(employeeRepository).deleteById(1L);
		
		employeeService.deleteEmployee(1L);
		
		verify(employeeRepository).deleteById(1L);
	}
}
