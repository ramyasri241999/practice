package com.epam.practice.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="Customer")
public class CustomerResponse{
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long customerId;

	    @NotBlank(message = "Name must not be blank")
	    @Column(nullable = false)
	    private String name;

	    @NotBlank(message = "Email must not be blank")
	    @Email(message = "Invalid email format")
	    @Column(nullable = false, unique = true)
	    private String email;

	    @NotBlank(message = "Phone must not be blank")
	    @Pattern(
	        regexp = "^[0-9]{10}$",
	        message = "Phone number must be exactly 10 digits"
	    )
	    @Column(length = 10)
	    private String phone;

	    @Column(nullable = false)
	    private LocalDateTime createdAt;

	    // getters and setters
	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "CustomerResponse [customerId=" + customerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", createdAt=" + createdAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, customerId, email, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerResponse other = (CustomerResponse) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}
	
	
	
}