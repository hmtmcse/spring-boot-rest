package com.example.accessingdatamysql.dao;

import com.example.accessingdatamysql.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDaoRepository extends JpaRepository<Department, Integer> {

}