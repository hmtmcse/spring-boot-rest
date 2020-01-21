package com.ibcx.poc.service;

import java.util.List;
import java.util.Optional;

import com.ibcx.poc.model.Employee;
import net.sf.jasperreports.engine.JasperPrint;

public interface EmployeeService {

	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(int empid);
	public Employee addEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(int empid);
	public JasperPrint generateReport();

}