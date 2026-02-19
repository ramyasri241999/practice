package com.epam.practice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique=true)
	private String name;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	private BigDecimal budget;
	
	@OneToMany(mappedBy="department",fetch=FetchType.LAZY)
	private List<Employees> employees;
}

@Entity
@Table(name="employees")
class Employees{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private BigDecimal salary;
	
	@Column(name = "hire_date",nullable = false)
	private LocalDate hireDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="department_id",nullable=false)
	private Department department;
	
}
