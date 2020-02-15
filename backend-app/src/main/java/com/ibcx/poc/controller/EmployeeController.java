package com.ibcx.poc.controller;

import com.ibcx.poc.model.Department;
import com.ibcx.poc.model.Employee;
import com.ibcx.poc.service.DepartmentService;
import com.ibcx.poc.service.EmployeeService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.ResponseBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public ResponseEntity<Object> getById(@PathVariable Long id) {
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
	public ResponseEntity<Object> update(@RequestBody Employee employee, @PathVariable Long id) {
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
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
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

	@RequestMapping(value = "employee/export", method = RequestMethod.GET)
	public void export(Employee employee, HttpServletResponse response) throws IOException, JRException, SQLException {
		JasperPrint jasperPrint = null;

		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

		OutputStream out = response.getOutputStream();
		jasperPrint = employeeService.generateReport();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}

}