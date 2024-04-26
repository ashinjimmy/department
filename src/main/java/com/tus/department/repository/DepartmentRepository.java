package com.tus.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.department.entity.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{
	
	public DepartmentEntity findByDepartmentName(String departmentName);
	
	public DepartmentEntity findByDepartmentNameIgnoreCase(String departmentName);

}
