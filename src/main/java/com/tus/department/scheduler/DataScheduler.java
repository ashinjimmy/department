package com.tus.department.scheduler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.tus.department.entity.DepartmentEntity;
import com.tus.department.service.DepartmentService;

@Component
//@EnableScheduling
public class DataScheduler {

	private DepartmentService departmentService;

	public DataScheduler(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//@Scheduled(fixedRate = 3000)
	public String fetchAndProccess() {
		System.out.println("Schedule Active!!!");

		List<DepartmentEntity> deptData = departmentService.fetchAllDepartments();
		String jsonObj = new Gson().toJson(deptData);
		System.out.println(jsonObj);
		
		return "Schedule Active!!!";
	}

}
