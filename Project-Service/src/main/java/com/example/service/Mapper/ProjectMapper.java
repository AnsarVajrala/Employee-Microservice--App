package com.example.service.Mapper;

import org.springframework.stereotype.Component;

import com.example.service.dto.ProjectDTO;
import com.example.service.entity.Project;

@Component
public class ProjectMapper {
	
	public ProjectDTO toDTO(Project project) {
		if(project == null) {
			return null;
		}
		
		ProjectDTO dto =new ProjectDTO();
		
		dto.setId(project.getId());
		dto.setProjectName(project.getProjectName());
		dto.setClientName(project.getClientName());
		dto.setStartDate(project.getStartDate());
		dto.setEndDate(project.getEndDate());
		dto.setStatus(project.getStatus());
		dto.setEmployeeId(project.getEmployeeId());
		
		return dto;
	}
	
	public Project toEntity(ProjectDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Project project = new Project();
		
		project.setId(dto.getId());
		project.setProjectName(dto.getProjectName());
		project.setClientName(dto.getClientName());
		project.setStartDate(dto.getStartDate());
		project.setEndDate(dto.getEndDate());
		project.setStatus(dto.getStatus());
		project.setEmployeeId(dto.getEmployeeId());
		
		return project;
	}

}

