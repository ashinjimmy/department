package com.tus.department.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tus.department.dto.DepartmentDto;
import com.tus.department.entity.DepartmentEntity;
import com.tus.department.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	private DepartmentEntity department;

	@BeforeEach
	void setup() {

		department = DepartmentEntity.builder().departmentName("CS").departmentAddress("Cochin").departmentCode("CSs-25")
				.departmentId(1L).build();

	}

	@Test
	void saveDepartment() throws Exception {
	    DepartmentDto inputDept = DepartmentDto.builder()
	            .departmentName("CS")
	            .departmentAddress("Cochin")
	            .departmentCode("CSs-25")
	            .departmentId(1L)
	            .build();

	    Mockito.when(departmentService.saveDepartment(inputDept)).thenReturn(department);

	    mockMvc.perform(post("api/departments") // Corrected this line
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("{\r\n"
	                    + "    \"departmentName\":\"CS\",\r\n"
	                    + "    \"departmentAddress\":\"Cochin\",\r\n"
	                    + "    \"departmentCode\":\"CSs-25\"\r\n"
	                    + "}"))
	            .andExpect(status().isOk());
	}


	@Test
	@Disabled
	void fetchDepartmentById() {

	}
}
