package com.example.springboot.model;

import javax.persistence.*;

@Entity
@Table (name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}