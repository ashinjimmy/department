package com.tus.department.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tus.department.dto.DepartmentDto;
import com.tus.department.entity.DepartmentEntity;
import com.tus.department.exceptions.DepartmentNotFoundException;

public interface DepartmentService {

	public DepartmentEntity saveDepartment(DepartmentDto departmentDto);

	public List<DepartmentEntity> fetchAllDepartments();

	public DepartmentEntity findDepartmentById(Long departmentId) throws DepartmentNotFoundException;

	public String deleteDeptById(Long departmentId);

	public DepartmentEntity updateDeptById(Long departmentId, DepartmentDto departmentDto);

	public DepartmentEntity findDepartmentByName(String name);

}
