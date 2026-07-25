package com.example.service.mapper;

import org.springframework.stereotype.Component;

import com.example.service.dto.DepartmentDTO;
import com.example.service.entity.Department;

@Component
public class DepartmentMapper {
	public DepartmentDTO toDTO(Department department) {
		if(department ==null) {
			return null;
		}
		DepartmentDTO dto = new DepartmentDTO();
		
		dto.setId(department.getId());
		dto.setDepartmentName(department.getDepartmentName());
		return dto;
	}
	
	public Department toEntity(DepartmentDTO dto) {
		if(dto==null) {
			return null;
		}
		
		Department department =new Department();
		
		department.setId(dto.getId());
		department.setDepartmentName(dto.getDepartmentName());
		
		return department;
	}
}
