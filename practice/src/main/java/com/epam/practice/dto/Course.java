package com.epam.practice.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false,unique = true)
	private String code;
	
	@Column(nullable= false)
	private int credits;
	
	@ManyToMany(mappedBy= "courses",fetch=FetchType.LAZY)
	private List<Student> student;
	
	
}

@Entity
@Table(name ="student")
class Student{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false,unique=true)
	@NotNull
	private String email;
	@Column(name = "enrollment_date",nullable=false)
	private LocalDate enrollmentDate;
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(name="student"
	,joinColumns = @JoinColumn(name ="student_id"), inverseJoinColumns= @JoinColumn(name = "course_id"))
	private List<Course> courses;
	
}

//
//class StudentCourse{
//	
//	private List<Student> students;
//	private List<Course> courses;
//}
