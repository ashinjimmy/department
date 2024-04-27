
package com.tus.department.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tus.department.dto.DepartmentDto;
import com.tus.department.entity.DepartmentEntity;
import com.tus.department.repository.DepartmentRepository;
import com.tus.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public DepartmentEntity saveDepartment(DepartmentDto departmentDto) {
		DepartmentEntity depEntity = new DepartmentEntity();
		depEntity.setDepartmentName(departmentDto.getDepartmentName());
		depEntity.setDepartmentAddress(departmentDto.getDepartmentAddress());
		depEntity.setDepartmentCode(departmentDto.getDepartmentCode());
		return departmentRepository.save(depEntity);
	}

	@Override
	public List<DepartmentEntity> fetchAllDepartments() {
		return  departmentRepository.findAll();
	}

	@Override
	public DepartmentEntity findDepartmentById(Long departmentId)  {
		Optional<DepartmentEntity> deptInfo = departmentRepository.findById(departmentId);
		if(deptInfo.isPresent()) {
			return deptInfo.get();
		}
		return new DepartmentEntity();
	}

	@Override
	public String deleteDeptById(Long departmentId) {
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
		return departmentRepository.save(deptInfo);
	}

	@Override
	public DepartmentEntity findDepartmentByName(String name) {
		return departmentRepository.findByDepartmentNameIgnoreCase(name);
	}

}
