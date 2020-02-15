package com.ibcx.poc.service;

import java.util.List;
import java.util.Optional;

import com.ibcx.poc.model.Employee;
import net.sf.jasperreports.engine.JasperPrint;

public interface EmployeeService {

	public List<Employee> getEmployees();
	public Optional<Employee> getEmployeeById(Long empid);
	public Employee addEmployee(Employee emp);
	public Employee updateEmployee(Employee emp);
	public void deleteEmployeeById(Long empid);
	public JasperPrint generateReport();

}