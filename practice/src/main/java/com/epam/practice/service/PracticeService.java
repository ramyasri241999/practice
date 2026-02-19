package com.epam.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.practice.dto.Customer;
import com.epam.practice.dto.CustomerResponse;
import com.epam.practice.repository.CustomerRepository;
@Service
public class PracticeService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	public CustomerResponse saveCustomer(Customer customer) {
		CustomerResponse customerResponse = customerRepository.save(customer);
		return customerResponse;
	}
	
	public CustomerResponse getCustomers(Long customerId) {
		CustomerResponse customerResponse = customerRepository.findById(customerId).orElseThrow(()->new RuntimeException("CustomerId not found: "+customerId));
		return customerResponse;
	}
}