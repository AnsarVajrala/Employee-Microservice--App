package com.example.service.Controller;

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

import com.example.service.Service.ProjectService;
import com.example.service.dto.ProjectDTO;

@RequestMapping("/api/project")
@RestController
public class ProjectController {
	private final ProjectService service;
	
	public ProjectController(ProjectService service) {
		this.service=service;
	}
	
	@GetMapping("/page")
	public Page<ProjectDTO> getProjects(@RequestParam(defaultValue ="0") int page,
			@RequestParam(defaultValue ="5") int size,
			@RequestParam(defaultValue ="id") String sortBy,
			@RequestParam(defaultValue ="asc") String direction){
		return service.getProjects(page, size, sortBy, direction);
	}
	
	@GetMapping("/employee/{employeeId}")
	public List<ProjectDTO> getProjectByEmployeeId(@PathVariable Long employeeId){
		return service.getProjectsByEmployeeId(employeeId);
	}
	
	@GetMapping
	public List<ProjectDTO> getAllProjects(){
		return service.getAllProjects();
	}
	
	@GetMapping("/{id}")
	public ProjectDTO getProjectById(@PathVariable Long id) {
		return service.getProjectById(id);
	}
	
	@PostMapping
	public ProjectDTO saveProject(@RequestBody ProjectDTO project) {
		return service.saveProject(project);
	}
	
	@PutMapping("/{id}")
	public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO project) {
		return service.updateProject(id, project);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProject(@PathVariable Long id) {
		service.deleteProject(id);
		return "Project details deleted successfully";
	}
}

