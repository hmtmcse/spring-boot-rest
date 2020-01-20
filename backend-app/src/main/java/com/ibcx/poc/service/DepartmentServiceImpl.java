package com.ibcx.poc.service;

import com.ibcx.poc.dao.DepartmentDaoRepository;
import com.ibcx.poc.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDaoRepository departmentDaoRepository;


	@Override
	public List<Department> getDepartments() {
		return departmentDaoRepository.findAll();
	}

	@Override
	public Optional<Department> getDepartmentById(int departmentId) {
		return departmentDaoRepository.findById(departmentId);
	}

	@Override
	public Department addNewDepartment(Department department) {
		return departmentDaoRepository.save(department);
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentDaoRepository.save(department);
	}

	@Override
	public void deleteDepartmentById(int departmentId) {
		departmentDaoRepository.deleteById(departmentId);
	}
}