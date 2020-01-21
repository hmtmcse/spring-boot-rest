package com.ibcx.poc.service;

import com.ibcx.poc.dao.EmployeeDaoRepository;
import com.ibcx.poc.model.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public JasperPrint generateReport() {
        JasperPrint jasperPrint = null;
        try {
            String reportPath = "jrxmls";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\employee.jrxml");
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employeeDaoRepository.findAll());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Websparrow.org");
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Emp-Rpt.pdf");

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jasperPrint;
    }
}