package com.epam.practice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique=true,nullable= false)
	@NotNull
	private String username;
	
	@Column(name = "email", unique=true,nullable= false)
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Column(nullable= false)
	private String password;
	
	@NotNull
	@Column(nullable= false)
	private LocalDateTime createdAt;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private UserProfile userProfile;

}

@Entity
@Table(name = "user_profile")
class UserProfile {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "address",nullable= false)
	@NotNull
	private String address;
	
	@Column(name = "phone",nullable= false)
	@NotNull
	private String phone;
	
	@Column(name = "dob",nullable= false)
	@NotNull
	private LocalDate dob;
	
	@OneToOne(optional=false)
	@JoinColumn(name ="user_id",nullable=false,unique=true)
	private User user;
}

