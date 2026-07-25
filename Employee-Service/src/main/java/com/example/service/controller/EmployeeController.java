package com.example.service.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.service.Service.EmployeeService;
import com.example.service.dto.EmployeeDTO;
import com.example.service.dto.EmployeeDepartmentResponse;
import com.example.service.dto.EmployeeProjectResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {
	private final EmployeeService service;
	public EmployeeController(EmployeeService service) {
		this.service =service;
	}
	
	@GetMapping("/page")
	public Page<EmployeeDTO> getEmployees(@RequestParam(defaultValue ="0") int page,
			@RequestParam(defaultValue ="5") int size,
			@RequestParam(defaultValue ="id") String sortBy,
			@RequestParam(defaultValue ="asc") String direction){
		return service.getEmployees(page, size, sortBy, direction);
	}
	
	@GetMapping
	public List<EmployeeDTO> getAllEmployees(){
		return service.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable Long id) {
		return service.getEmployeeId(id);
	}
	
	@GetMapping("/details/{id}")
	public EmployeeDepartmentResponse getEmployee(@PathVariable Long id) {
		return service.getEmployee(id);
	}
	
	@GetMapping("/projects/{id}")
	public EmployeeProjectResponse getEmployeeProjects(@PathVariable Long id) {
		return service.getEmployeeWithProjects(id);
	}
	
	@PostMapping
	public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employee) {
		return service.SaveEmployee(employee);
	}
	
	@PutMapping("/{id}")
	public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employee ) {
		return service.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		service.deleteEmployee(id);
		return "Employee deleted successfully";
	}
}

