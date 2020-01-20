package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.Department;
import com.example.accessingdatamysql.model.Employee;
import com.example.accessingdatamysql.service.DepartmentService;
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
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/department/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<Object>(ResponseBuilder.makeResponse(departmentService.getDepartments()), HttpStatus.OK);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable int id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (!department.isPresent()) {
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR,  "department not found"), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(ResponseBuilder.makeResponse(department), HttpStatus.OK);
    }

    @RequestMapping(value = "/department/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody Department department) {
        try {
            departmentService.addNewDepartment(department);
        } catch (Exception ex) {
            //TODO will generate/catch specific message
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Department successfully added"), HttpStatus.OK);
	}

    @RequestMapping(value = "/department/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Department department, @PathVariable int id) {
        Optional<Department> dep = departmentService.getDepartmentById(id);
        if (!dep.isPresent()) {
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, "invalid department"), HttpStatus.OK);
        }
        try {
            departmentService.updateDepartment(department);
        } catch (Exception ex) {
            //TODO will generate/catch specific message
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Department successfully updated"), HttpStatus.OK);
    }

    @RequestMapping(value = "/department/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (!department.isPresent()) {
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, "invalid department"), HttpStatus.OK);
        }
        try {
            departmentService.deleteDepartmentById(id);
        } catch (Exception ex) {
            //TODO will generate/catch specific message
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_ERROR, ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Successfully Deleted!"), HttpStatus.OK);
    }
}