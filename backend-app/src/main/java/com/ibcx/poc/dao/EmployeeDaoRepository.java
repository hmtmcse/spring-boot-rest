package com.ibcx.poc.dao;

import com.ibcx.poc.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDaoRepository extends JpaRepository<Employee, Integer> {

}