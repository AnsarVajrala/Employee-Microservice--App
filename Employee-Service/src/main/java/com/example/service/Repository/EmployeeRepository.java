package com.example.service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
