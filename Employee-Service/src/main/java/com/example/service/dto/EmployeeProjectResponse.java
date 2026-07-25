package com.example.service.dto;

import java.util.List;

public class EmployeeProjectResponse {
	private EmployeeDTO employee;
	private List<ProjectDTO> projects;
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public List<ProjectDTO> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectDTO> projects) {
		this.projects = projects;
	}
	
}
