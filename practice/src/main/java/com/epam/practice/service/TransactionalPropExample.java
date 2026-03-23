package com.epam.practice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalPropExample {
	
	public static void main(String[] args) {
		TransactionalPropExample example = new TransactionalPropExample();
		example.outer();
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void outer(){
		System.out.println("Outer method started");
		inner();
		System.out.println("Outer method completed");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void inner() {
		System.out.println("Inner method started");
		// Simulate some work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Inner method completed");
	}
}
