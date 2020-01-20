package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.model.Department;
import com.example.accessingdatamysql.model.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

	public List<Department> getDepartments();
	public Optional<Department> getDepartmentById(int departmentId);
	public Department addNewDepartment(Department department) throws Exception;
	public Department updateDepartment(Department department) throws Exception;
	public void deleteDepartmentById(int departmentId) throws Exception;

}