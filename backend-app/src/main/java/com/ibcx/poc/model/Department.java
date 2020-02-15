package com.ibcx.poc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="department_id")
	private Long id;

	@Size(max=5)
	private String department_code;

	@Size( max=30)
	private String name;

	private Boolean isActive = true;

	public Department(){}

}