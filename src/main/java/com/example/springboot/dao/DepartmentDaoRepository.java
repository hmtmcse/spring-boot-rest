package com.example.springboot.dao;

import com.example.springboot.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDaoRepository extends JpaRepository<Department, Integer> {

}