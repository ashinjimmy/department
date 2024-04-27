package com.tus.department.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.department.dto.DepartmentDto;
import com.tus.department.entity.DepartmentEntity;
import com.tus.department.exceptions.DepartmentNotFoundException;
import com.tus.department.service.DepartmentService;

@RestController
@RequestMapping("/api")
public class DepartmentController {

	private DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private static final  Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@PostMapping("/departments")
	public ResponseEntity<DepartmentEntity> saveDepartment(@RequestBody DepartmentDto departmentDto) {
		DepartmentEntity registeredInfo = departmentService.saveDepartment(departmentDto);
		LOGGER.info("Inside the saveDepartment of DepartmentService !!");
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredInfo);
	}

	@GetMapping("/departments")
	public List<DepartmentEntity> fetchAllDepartment() {
		return departmentService.fetchAllDepartments();
	}

	@GetMapping("/departments/{id}")
	public ResponseEntity<DepartmentEntity> findDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.findDepartmentById(departmentId));
	}

	@DeleteMapping("/departments/{id}")
	public ResponseEntity<String> deleteDeptById(@PathVariable("id") Long departmentId) {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.deleteDeptById(departmentId));
	}

	@PutMapping("/departments/{id}")
	public ResponseEntity<DepartmentEntity> updateDeptById(@PathVariable("id") Long departmentId,
			@RequestBody DepartmentDto departmentDto) {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDeptById(departmentId, departmentDto));
	}
	
	@GetMapping("/departments/name/{name}")
	public ResponseEntity<DepartmentEntity> findDepartmentByName(@PathVariable("name") String name) throws DepartmentNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.findDepartmentByName(name));
	}
	
	

}
