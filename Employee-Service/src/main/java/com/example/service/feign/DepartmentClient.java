package com.example.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.service.dto.Department;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface DepartmentClient {
	@GetMapping("/api/department/{id}")
	Department getDepartmentById(@PathVariable Long id);
}
