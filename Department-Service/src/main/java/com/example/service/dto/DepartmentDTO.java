package com.example.service.dto;

public class DepartmentDTO {
	private Long id;
	private String departmentName;
	
	public DepartmentDTO() {
	}
	public DepartmentDTO(Long id, String departmentName) {
		this.id = id;
		this.departmentName = departmentName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
}
