package com.example.springboot.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String department_code;

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	private List<Employee> employees;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}