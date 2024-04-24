package com.tus.department.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tus.department.entity.DepartmentEntity;
import com.tus.department.repository.DepartmentRepository;

@SpringBootTest
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;

	@BeforeEach
	void setUp() {

		DepartmentEntity department = DepartmentEntity.builder().departmentName("CS").departmentAddress("Cochin")
				.departmentCode("CS-25").departmentId(1L).build();
		
		Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CS")).thenReturn(department);

	}

	@Test
	@DisplayName("Get Data based on valid Department Name")
	//@Disabled --> Annotation for skipping the test
	public void whenValidDepartmentName_thenDepartmentShouldFound() {
		String departmentName = "CS";
		DepartmentEntity foundDept = departmentService.findDepartmentByName(departmentName);
		assertEquals(departmentName, foundDept.getDepartmentName());
	}

}
