package com.epam.practice.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.messaging", havingValue = "prod")
public class ConditionalBean {
	
	public void message() {
		System.out.println("Inside the prod messaging service class");
	}
}
