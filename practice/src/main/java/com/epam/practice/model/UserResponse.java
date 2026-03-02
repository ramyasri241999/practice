package com.epam.practice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;



public class UserResponse {
	

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    private String address;
    private String phone;
    private LocalDate dob;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", username=" + username + ", email=" + email + ", createdAt=" + createdAt
				+ ", address=" + address + ", phone=" + phone + ", dob=" + dob + "]";
	}

	 
}

