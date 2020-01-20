package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dao.DepartmentDaoRepository;
import com.example.accessingdatamysql.dao.EmployeeDaoRepository;
import com.example.accessingdatamysql.model.Department;
import com.example.accessingdatamysql.model.Employee;
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