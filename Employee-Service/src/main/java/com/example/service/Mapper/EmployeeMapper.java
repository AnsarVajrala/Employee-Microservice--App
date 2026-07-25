package com.example.service.Mapper;

import org.springframework.stereotype.Component;

import com.example.service.dto.EmployeeDTO;
import com.example.service.entity.Employee;

@Component
public class EmployeeMapper {
	public EmployeeDTO toDTO(Employee employee) {
		if(employee == null) {
			return null;
		}
		
		EmployeeDTO dto =new EmployeeDTO();
		dto.setId(employee.getId());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setEmail(employee.getEmail());
		dto.setPhone(employee.getPhone());
		dto.setSalary(employee.getSalary());
		dto.setDesignation(employee.getDesignation());
		
		return dto;
	}
	
	public Employee toEntity(EmployeeDTO dto) {
		
		if(dto==null) {
			return null;
		}
		
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail());
		employee.setPhone(dto.getPhone());
		employee.setSalary(dto.getSalary());
		employee.setDesignation(dto.getDesignation());
		
		return employee;
	}
}

