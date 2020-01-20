package com.example.springboot.service;

import java.util.List;
import java.util.Optional;

import com.example.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dao.EmployeeDaoRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDaoRepository employeeDaoRepository;

	@Override
	public List<Employee> getEmployees() {
		return employeeDaoRepository.findAll();
	}
	@Override
	public Optional<Employee> getEmployeeById(int empid) {
		return employeeDaoRepository.findById(empid);
	}
	@Override
	public Employee addNewEmployee(Employee emp) {
		return employeeDaoRepository.save(emp);
	}
	@Override
	public Employee updateEmployee(Employee emp) {
		return employeeDaoRepository.save(emp);
	}
	@Override
	public void deleteEmployeeById(int empid) {
		employeeDaoRepository.deleteById(empid);
	}
	@Override
	public void deleteAllEmployees() {
		employeeDaoRepository.deleteAll();
	}
}