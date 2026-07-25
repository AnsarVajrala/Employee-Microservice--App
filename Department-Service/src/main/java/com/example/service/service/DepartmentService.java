package com.example.service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.service.Repository.DepartmentRepository;
import com.example.service.dto.DepartmentDTO;
import com.example.service.entity.Department;
import com.example.service.mapper.DepartmentMapper;

@Service
public class DepartmentService {
	private final DepartmentRepository repository;
	private final DepartmentMapper mapper;
	
	public DepartmentService(DepartmentRepository repository, DepartmentMapper mapper) {
		this.repository=repository;
		this.mapper=mapper;
	}
	
	//Paging and sorting
		public Page<DepartmentDTO> getDepartments(int page, int size, String sortBy,String direction){
			Sort sort =direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
			Pageable pageable = PageRequest.of(page, size, sort);
			
			return repository.findAll(pageable).map(mapper::toDTO);
		}
		
	// fetch department details
	public List<DepartmentDTO> getAllDepartments(){
		return repository.findAll().
				stream()
				.map(mapper::toDTO)
				.toList();
	}
	
	//fetch department details by id
	public DepartmentDTO getDepartmentById(Long id) {
		Department department = repository.findById(id).orElseThrow(
				() -> new RuntimeException("Department not found with id :" +id));
		return mapper.toDTO(department);
	}
	
	// add the department data
	public DepartmentDTO saveDepartment(DepartmentDTO dto) {
		Department department = mapper.toEntity(dto);
		
		Department SavedDepartment = repository.save(department);
		
		return mapper.toDTO(SavedDepartment);
	}
	
	//update the department data
	public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
		Department existing = repository.findById(id)
				.orElseThrow(()-> new RuntimeException("Department not found with id :" +id));
		existing.setDepartmentName(dto.getDepartmentName());
		Department updatedDepartment = repository.save(existing);
		return mapper.toDTO(updatedDepartment);
	}
	
	//delete the department
	public void deleteDepartment(Long id) {
		repository.deleteById(id);
	}
}
