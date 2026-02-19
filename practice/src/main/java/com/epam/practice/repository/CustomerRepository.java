package com.epam.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.practice.dto.Customer;
import com.epam.practice.dto.CustomerResponse;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerResponse,Long>{
	CustomerResponse save(Customer customer);
}