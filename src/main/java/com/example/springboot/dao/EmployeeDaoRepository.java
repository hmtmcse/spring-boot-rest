package com.example.springboot.dao;

import com.example.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDaoRepository extends JpaRepository<Employee, Integer> {

}