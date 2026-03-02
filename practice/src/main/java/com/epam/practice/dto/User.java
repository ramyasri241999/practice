package com.epam.practice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@JsonManagedReference
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private UserProfile userProfile;
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public UserProfile getUserProfile() {
		return userProfile;
	}



	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", createdAt=" + createdAt + ", userProfile=" + userProfile + "]";
	}

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
	
	@JsonBackReference
	@OneToOne(optional=false)
	@JoinColumn(name ="user_id",nullable=false,unique=true)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", address=" + address + ", phone=" + phone + ", dob=" + dob + ", user=" + user
				+ "]";
	}
	
	
}

