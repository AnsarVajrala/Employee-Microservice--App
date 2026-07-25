package com.example.service.dto;

public class EmployeeDepartmentResponse {
	private EmployeeDTO employee;
	private Department department;
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	

}
