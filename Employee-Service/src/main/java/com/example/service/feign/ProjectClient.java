package com.example.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.service.dto.ProjectDTO;

@FeignClient("PROJECT-SERVICE")
public interface ProjectClient {
	@GetMapping("/api/project/employee/{employeeId}")
	List<ProjectDTO> getProjectByEmployeeId(@PathVariable("employeeId") Long employeeeId);
}
