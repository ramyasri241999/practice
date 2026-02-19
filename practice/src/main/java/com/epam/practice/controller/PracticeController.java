package com.epam.practice.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.epam.practice.dto.CustomerResponse;
import com.epam.practice.service.PracticeService;
import com.epam.practice.dto.Customer;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class PracticeController{
	
	@Value("${customer.name}")
	private String customerName;
	private final PracticeService customerService;
	public PracticeController(PracticeService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/customers")
	public CustomerResponse createCustomer(@RequestBody Customer customer) {
		CustomerResponse customerResponse = customerService.saveCustomer(customer);
		System.out.println("customer name "+customerName);
		return customerResponse;
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomers(@PathVariable Long customerId) {
		//CustomerResponse customerResponse= customerService.getCustomers(customerId);
		System.out.println("customer name "+customerName);
		//return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerResponse);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CustomerResponse());
	}
	
}