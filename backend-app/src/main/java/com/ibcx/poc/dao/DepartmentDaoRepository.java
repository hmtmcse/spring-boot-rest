package com.ibcx.poc.dao;

import com.ibcx.poc.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDaoRepository extends JpaRepository<Department, Long> {

}