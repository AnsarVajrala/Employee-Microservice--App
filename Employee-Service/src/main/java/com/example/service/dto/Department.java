package com.example.service.dto;

public class Department {
	private Long id;
	private String departmentName;
	public Department(Long id, String departmentName) {
		super();
		this.id = id;
		this.departmentName = departmentName;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
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
