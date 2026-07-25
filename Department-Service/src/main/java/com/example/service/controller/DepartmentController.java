package com.example.service.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.dto.DepartmentDTO;
import com.example.service.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	private final DepartmentService service;
	
	public DepartmentController(DepartmentService service) {
		this.service=service;
	}
	
	@GetMapping("/page")
	public Page<DepartmentDTO> getDepartments(@RequestParam(defaultValue ="0") int page,
			@RequestParam(defaultValue ="5") int size,
			@RequestParam(defaultValue ="id") String sortBy,
			@RequestParam(defaultValue ="asc") String direction){
		return service.getDepartments(page, size, sortBy, direction);
	}
	
	@GetMapping
	public List<DepartmentDTO> getAllDepartments(){
		return service.getAllDepartments();
	}
	
	@GetMapping("/{id}")
	public DepartmentDTO getDepartmentById(@PathVariable Long id) {
		return service.getDepartmentById(id);
	}
	
	@PostMapping
	public DepartmentDTO saveDepartment(@RequestBody DepartmentDTO department) {
		return service.saveDepartment(department);
	}
	
	@PutMapping("/{id}")
	public DepartmentDTO updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO department) {
		return service.updateDepartment(id, department);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		service.deleteDepartment(id);
		return "Department details deleted successfully";
	}

}
