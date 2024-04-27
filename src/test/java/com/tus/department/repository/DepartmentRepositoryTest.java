package com.tus.department.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tus.department.entity.DepartmentEntity;

@DataJpaTest
 class DepartmentRepositoryTest {


	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@BeforeEach
	void setUp() {

		DepartmentEntity department = DepartmentEntity.builder().departmentId(1L).departmentName("Mech").departmentAddress("Delhi")
				.departmentCode("MECH").build();

		testEntityManager.persist(department);
	}
	
	@Test
	@Disabled
	public void whenFindById_thenReturnDepartment() {
		DepartmentEntity department =  departmentRepository.findById(1L).get();
		
		assertEquals(department.getDepartmentName(), "Mech");
	}
	
	
}
