package com.epam.practice.designs.structural;

public class AdapterExample {
/* Definition : 
 * The Adapter design pattern is a structural design pattern that allows objects with incompatible interfaces to work together. 
 * It acts as a bridge between two incompatible interfaces, enabling them to communicate and collaborate effectively. 
 * The Adapter pattern is often used when you want to integrate existing classes or libraries that have different interfaces into your application without modifying their code.
 */
	public static void main(String[] args) {
		oldPayment oldPayment = new oldPayment();
		newPayment paymentAdapter = new PaymentAdapter(oldPayment);
		paymentAdapter.processPayment();  // this will call the makePayment method of the old payment system through the adapter
	}
}

class oldPayment{
	public void makePayment() {
		System.out.println("Payment made using old payment system.");
	}
}

interface newPayment {
	void processPayment();   // our application expects this method to be called for processing payments
}


class PaymentAdapter implements newPayment {
	private oldPayment oldPayment;  // composition - we are using the old payment system to implement the new payment system
	
	public PaymentAdapter(oldPayment oldPayment) {
		this.oldPayment = oldPayment;
	}
	
	@Override
	public void processPayment() {  // this method is called by our application to process payments, and it internally calls the makePayment method of the old payment system to process the payment
		oldPayment.makePayment();
	}
}
