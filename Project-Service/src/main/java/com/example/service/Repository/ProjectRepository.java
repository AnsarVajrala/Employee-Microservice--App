package com.example.service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.service.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByEmployeeId(Long employeeId);
}
