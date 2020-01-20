package com.example.accessingdatamysql.dao;

import com.example.accessingdatamysql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDaoRepository extends JpaRepository<Employee, Integer> {

}