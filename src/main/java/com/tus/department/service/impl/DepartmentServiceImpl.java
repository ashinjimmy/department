package com.tus.department.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tus.department.dto.DepartmentDto;
import com.tus.department.entity.DepartmentEntity;
import com.tus.department.exceptions.DepartmentNotFoundException;
import com.tus.department.repository.DepartmentRepository;
import com.tus.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentEntity saveDepartment(DepartmentDto departmentDto) {
		DepartmentEntity depEntity = new DepartmentEntity();
		depEntity.setDepartmentName(departmentDto.getDepartmentName());
		depEntity.setDepartmentAddress(departmentDto.getDepartmentAddress());
		depEntity.setDepartmentCode(departmentDto.getDepartmentCode());
		DepartmentEntity registeredInfo = departmentRepository.save(depEntity);
		return registeredInfo;
	}

	@Override
	public List<DepartmentEntity> fetchAllDepartments() {
		List<DepartmentEntity> listofDepartment = departmentRepository.findAll();
		return listofDepartment;
	}

	@Override
	public DepartmentEntity findDepartmentById(Long departmentId) throws DepartmentNotFoundException {

		Optional<DepartmentEntity> deptInfo = departmentRepository.findById(departmentId);
		if (!deptInfo.isPresent()) {
			throw new DepartmentNotFoundException("Department is not available !!");
		}
		return deptInfo.get();
	}

	@Override
	public String deleteDeptById(Long departmentId) {
		DepartmentEntity deptInfo = departmentRepository.findById(departmentId).get();
		departmentRepository.deleteById(departmentId);
		return "Deleted the data successfully !!";
	}

	@Override
	public DepartmentEntity updateDeptById(Long departmentId, DepartmentDto departmentDto) {
		DepartmentEntity deptInfo = departmentRepository.findById(departmentId).get();
		if (Objects.nonNull(departmentDto.getDepartmentName())
				&& !"".equalsIgnoreCase(departmentDto.getDepartmentName())) {
			deptInfo.setDepartmentName(departmentDto.getDepartmentName());
		}

		if (Objects.nonNull(departmentDto.getDepartmentAddress())
				&& !"".equalsIgnoreCase(departmentDto.getDepartmentAddress())) {
			deptInfo.setDepartmentAddress(departmentDto.getDepartmentAddress());
		}

		if (Objects.nonNull(departmentDto.getDepartmentCode())
				&& !"".equalsIgnoreCase(departmentDto.getDepartmentCode())) {
			deptInfo.setDepartmentCode(departmentDto.getDepartmentCode());
		}

		DepartmentEntity updatedInfo = departmentRepository.save(deptInfo);
		return updatedInfo;
	}

	@Override
	public DepartmentEntity findDepartmentByName(String name) {
		return departmentRepository.findByDepartmentNameIgnoreCase(name);
	}

}
