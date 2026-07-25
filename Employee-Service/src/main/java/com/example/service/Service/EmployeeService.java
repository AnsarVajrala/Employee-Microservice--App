package com.example.service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.service.GlobalException.ResourceNotFoundException;
import com.example.service.Mapper.EmployeeMapper;
import com.example.service.Repository.EmployeeRepository;
import com.example.service.dto.Department;
import com.example.service.dto.EmployeeDTO;
import com.example.service.dto.EmployeeDepartmentResponse;
import com.example.service.dto.EmployeeProjectResponse;
import com.example.service.dto.ProjectDTO;
import com.example.service.entity.Employee;
import com.example.service.feign.DepartmentClient;
import com.example.service.feign.ProjectClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EmployeeService {
	//private static final Logger logger =LoggerFactory.getLogger(EmployeeService.class);
	private final EmployeeRepository erepository;
	@Autowired
	private DepartmentClient departmentClient;
	private final EmployeeMapper mapper;
	private ProjectClient projectClient;
	
	public EmployeeService(EmployeeRepository erepository,
			EmployeeMapper mapper,DepartmentClient departmentClient,ProjectClient projectClient) {
		this.erepository = erepository;
		this.mapper=mapper;
		this.departmentClient=departmentClient;
		this.projectClient=projectClient;
	}
	
	//Paging and sorting
	public Page<EmployeeDTO> getEmployees(int page, int size, String sortBy,String direction){
		Sort sort =direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		
		return erepository.findAll(pageable).map(mapper::toDTO);
	}
	
	//Fetching all Employee details
	public List<EmployeeDTO> getAllEmployees(){
		//logger.info("Fetching all Employee Details");
		return erepository.findAll()
				.stream()
				.map(mapper::toDTO).toList();
	}
	
	@CircuitBreaker(name="departmentService", fallbackMethod="departmentFallback")
	public EmployeeDepartmentResponse getEmployees(Long id) {
		Employee employee = erepository.findById(id).orElseThrow();
		
		Department department=departmentClient.getDepartmentById(employee.getDepartmentId());
		EmployeeDepartmentResponse response =new EmployeeDepartmentResponse();
		response.setEmployee(mapper.toDTO(employee));
		response.setDepartment(department);
		return response;
	}
	
	public EmployeeDepartmentResponse departmentFallback(Long id, Exception ex) {
		Employee employee =erepository.findById(id).orElseThrow();
		Department department=new Department();
		department.setDepartmentName("Department Service is Unavailble");
		EmployeeDepartmentResponse response =new EmployeeDepartmentResponse();
		response.setEmployee(mapper.toDTO(employee));
		response.setDepartment(department);
		return response;
	}
	
	@CircuitBreaker(name="projectService", fallbackMethod="projectFallback")
	public EmployeeProjectResponse getEmployeeProject(Long id) {
		Employee employee = erepository.findById(id).orElseThrow();
		
		List<ProjectDTO> project=projectClient.getProjectByEmployeeId(employee.getId());
		EmployeeProjectResponse response =new EmployeeProjectResponse();
		response.setEmployee(mapper.toDTO(employee));
		response.setProjects(project);
		return response;
	}
	
	public EmployeeProjectResponse projectFallback(Long id, Exception ex) {
		Employee employee =erepository.findById(id).orElseThrow();
		ProjectDTO project =new ProjectDTO();
		project.setProjectName("Project Service is Unavailble");
		List<ProjectDTO> projects =new ArrayList<>();
		projects.add(project);
		EmployeeProjectResponse response =new EmployeeProjectResponse();
		response.setEmployee(mapper.toDTO(employee));
		response.setProjects(projects);
		return response;
	}
	
	//Fetching employee detail using ID
	public EmployeeDTO getEmployeeId(Long id){
		//logger.info("Fetching Employee Detail By ID: " + id);
		Employee employee =erepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found with id :" + id));
		
		return mapper.toDTO(employee);
	}
	
	//Saving the Employee
	public EmployeeDTO SaveEmployee(EmployeeDTO dto) {
		//logger.info("saving Employee: {}", dto.getFirstName());
		Employee employee = mapper.toEntity(dto);
	
		Employee SavedEmployee = erepository.save(employee);
		
		return mapper.toDTO(SavedEmployee);
	}
	
	//updating the Employee
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employee) {
		//logger.info("updating the Employee: {},{}", id,employee.getFirstName());
		Employee existing = erepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee details not found with id :" + id));
		existing.setFirstName(employee.getFirstName());
		existing.setLastName(employee.getLastName());
		existing.setEmail(employee.getEmail());
		existing.setPhone(employee.getPhone());
		existing.setSalary(employee.getSalary());
		existing.setDesignation(employee.getDesignation());
		
		Employee updateEmployee = erepository.save(existing);
		
		return mapper.toDTO(updateEmployee);
	}
	
	//Deleting the Employee details
	public void deleteEmployee(Long id) {
		//logger.info("delete employee by ID: " + id);
		erepository.deleteById(id);
	}
	
	public EmployeeDepartmentResponse getEmployee(Long id) {
		Employee employee =erepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee details not found with id :" + id));
		
		Department depaertment = departmentClient.getDepartmentById(employee.getDepartmentId());
		EmployeeDepartmentResponse response = new EmployeeDepartmentResponse();
		response.setEmployee(mapper.toDTO(employee));
		response.setDepartment(depaertment);
		
		return response;	
	}
	
	public EmployeeProjectResponse getEmployeeWithProjects(Long id) {
		Employee employee= erepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee details not found with id :" + id));
		
		List<ProjectDTO> projects = projectClient.getProjectByEmployeeId(id);
		EmployeeProjectResponse response =new EmployeeProjectResponse();
		response.setEmployee(mapper.toDTO(employee));
		response.setProjects(projects);
		return response;
	}
	
}
