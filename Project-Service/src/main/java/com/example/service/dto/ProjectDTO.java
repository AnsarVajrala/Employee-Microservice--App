package com.example.service.dto;

import java.time.LocalDate;

public class ProjectDTO {
	private Long id;
	private String projectName;
	private String clientName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;
	private Long employeeId;
	
	public ProjectDTO(Long id, String projectName, String clientName, LocalDate startDate, LocalDate endDate,
			String status, Long employeeId) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.clientName = clientName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.employeeId = employeeId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public ProjectDTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

