package com.tus.department.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tus.department.entity.DepartmentEntity;

@DataJpaTest
 class DepartmentRepositoryTest {


	@Autowired
	private TestEntityManager testEntityManager;

	@BeforeEach
	void setUp() {

		DepartmentEntity department = DepartmentEntity.builder().departmentId(1L).departmentName("Mech").departmentAddress("Delhi")
				.departmentCode("MECH").build();

		testEntityManager.persist(department);
	}
	
	
}
