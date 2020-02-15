package com.ibcx.poc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import util.GenderType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@ToString

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 30)
    private String name;

    private String photo;

    private Date joining_date;

    private Date date_of_birth;

    @Size(max = 20)
    private String designation;

    private BigDecimal basic_salary;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;


    public Employee(){}

}