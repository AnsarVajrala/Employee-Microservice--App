package com.example.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.service.Service.EmployeeService;
import com.example.service.dto.EmployeeDTO;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	private final ObjectMapper objectMapper =new ObjectMapper();
	
	@MockitoBean
	private EmployeeService employeeService;
	
	@Test
	public void testGetAllEmployees() throws Exception{
		EmployeeDTO dto=new EmployeeDTO();
		dto.setId(1L);
		dto.setFirstName("John");
		
		when(employeeService.getAllEmployees()).thenReturn(List.of(dto));
		mockMvc.perform(get("/api/employee")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].firstName").value("John"));
	}
	
	@Test
	public void testGetEmployeeById() throws Exception{
		EmployeeDTO dto=new EmployeeDTO();
		dto.setId(1L);
		dto.setFirstName("John");
		
		when(employeeService.getEmployeeId(1L)).thenReturn(dto);
		mockMvc.perform(get("/api/employee/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
	}
	
	@Test
	public void testSaveEpmloyee() throws Exception{
		EmployeeDTO dto =new EmployeeDTO();
		dto.setFirstName("John");
		dto.setEmail("john@gmail.com");
		
		when(employeeService.SaveEmployee(any(EmployeeDTO.class))).thenReturn(dto);
		
		mockMvc.perform(post("/api/employee").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk())
		   .andExpect(jsonPath("$.firstName").value("John"));
	}
	
	@Test
	public void testUpdateEmployee() throws Exception{
		EmployeeDTO dto =new EmployeeDTO();
		dto.setFirstName("Peter");
		when(employeeService.updateEmployee(eq(1L), any(EmployeeDTO.class))).thenReturn(dto);
		
		mockMvc.perform(put("/api/employee/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk())
		   .andExpect(jsonPath("$.firstName").value("Peter"));
	}
	
	@Test
	public void testDeleteEmployee() throws Exception{
		doNothing().when(employeeService).deleteEmployee(1L);
		
		mockMvc.perform(delete("/api/employee/1")).andExpect(status().isOk());
		
		
	}
}

