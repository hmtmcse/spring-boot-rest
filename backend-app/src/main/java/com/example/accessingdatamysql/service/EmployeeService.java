package com.example.accessingdatamysql.service;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.model.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(int empid);
	public Employee addEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(int empid);

}