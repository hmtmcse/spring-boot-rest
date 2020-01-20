package com.ibcx.poc.service;

import com.ibcx.poc.dao.EmployeeDaoRepository;
import com.ibcx.poc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDaoRepository employeeDaoRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeDaoRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int employeeId) {
        return employeeDaoRepository.findById(employeeId);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDaoRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDaoRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeDaoRepository.deleteById(employeeId);
    }
}