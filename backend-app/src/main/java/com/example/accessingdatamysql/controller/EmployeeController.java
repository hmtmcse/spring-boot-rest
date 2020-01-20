package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.Department;
import com.example.accessingdatamysql.model.Employee;
import com.example.accessingdatamysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.ResponseBuilder;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee/all", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll() {
		return new ResponseEntity<Object>(ResponseBuilder.makeResponse(employeeService.getEmployees()), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getById(@PathVariable int id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if (!employee.isPresent()) {
			return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR,  "Employee not found"), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(ResponseBuilder.makeResponse(employee), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Employee employee) {
		try {
			employeeService.addEmployee(employee);
		} catch (Exception ex) {
			//TODO will generate/catch specific message
			return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Employee successfully added"), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Employee employee, @PathVariable int id) {
		Optional<Employee> empObject = employeeService.getEmployeeById(id);
		if (!empObject.isPresent()) {
			return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, "invalid employee"), HttpStatus.OK);
		}
		try {
			employeeService.updateEmployee(employee);
		} catch (Exception ex) {
			//TODO will generate/catch specific message
			return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Employee successfully updated"), HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteById(@PathVariable int id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		if (!employee.isPresent()) {
			return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, "invalid department"), HttpStatus.OK);
		}
		try {
			employeeService.deleteEmployeeById(id);
		} catch (Exception ex) {
			//TODO will generate/catch specific message
			return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Successfully Deleted!"), HttpStatus.OK);
	}

}