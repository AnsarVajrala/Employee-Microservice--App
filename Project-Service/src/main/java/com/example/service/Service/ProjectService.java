package com.example.service.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.service.Mapper.ProjectMapper;
import com.example.service.Repository.ProjectRepository;
import com.example.service.dto.ProjectDTO;
import com.example.service.entity.Project;

@Service
public class ProjectService {
	private final ProjectRepository repository;
	private final ProjectMapper mapper;

	public ProjectService(ProjectRepository repository, ProjectMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// Paging and sorting
	public Page<ProjectDTO> getProjects(int page, int size, String sortBy, String direction) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);

		return repository.findAll(pageable).map(mapper::toDTO);
	}

	public List<ProjectDTO> getAllProjects() {
		return repository.findAll().stream().map(mapper::toDTO).toList();
	}

	public ProjectDTO getProjectById(Long id) {
		Project project = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not founded with id :" + id));
		return mapper.toDTO(project);
	}

	public ProjectDTO saveProject(ProjectDTO dto) {
		Project project = mapper.toEntity(dto);
		Project SavedProject = repository.save(project);
		return mapper.toDTO(SavedProject);
	}

	public ProjectDTO updateProject(Long id, ProjectDTO project) {
		Project existing = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not founded with id :" + id));

		// existing.setId(project.getId());
		existing.setProjectName(project.getProjectName());
		existing.setClientName(project.getClientName());
		existing.setStartDate(project.getStartDate());
		existing.setEndDate(project.getEndDate());
		existing.setStatus(project.getStatus());

		Project updateProject = repository.save(existing);

		return mapper.toDTO(updateProject);
	}

	public void deleteProject(Long id) {
		repository.deleteById(id);
		;
	}
	
	public List<ProjectDTO> getProjectsByEmployeeId(Long employeeId){
		return repository.findByEmployeeId(employeeId).stream().map(mapper::toDTO).toList();
	}

}

