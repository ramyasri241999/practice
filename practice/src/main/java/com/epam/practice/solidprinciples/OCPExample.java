package com.epam.practice.solidprinciples;

/*
 * Softwares should be open for extension but closed for modification. 
 * It means that we should be able to add new functionality to a software without changing the existing code. 
 * This principle is achieved by using interfaces and abstract classes. 
 * By using interfaces and abstract classes, we can create new classes that implement the interfaces 
 * or extend the abstract classes without changing the existing code.
 * Ex: If we have a payment processing system that supports different payment types (e.g., card, PayPal), 
 * we can use the OCP principle to design the system in a way that allows us to add new payment types without modifying the existing code.
 * Spring Ex:Collection.sort() method is an example of OCP principle, we can add new sorting algorithms without modifying the existing code, we can simply create a new class that implements the Comparator interface and pass it to the sort() method.
 */
public class OCPExample {
	
	public static void main(String[] args) {
		
	}
	
	public void pay(String paymentType) { 
		if(paymentType.equals("card")) { //if we want to add new payment type, we have to modify this method, which violates the OCP principle
			// process card payment
			System.out.println("Processing card payment...");
		} else if(paymentType.equals("paypal")) {
			// process paypal payment
			System.out.println("Processing PayPal payment...");
		}
	}
	

}

/* To adhere to the OCP principle, we can use an interface for payment processing and implement it for each payment type. 
 * This way, we can add new payment types without modifying the existing code.
*/
interface Payment{ // now if we want to add new payment type, we can simply create a new class that implements the Payment interface without modifying the existing code, which adheres to the OCP principle.
	void pay();
	}

class CardPayment implements Payment{
	@Override
	public void pay() {
		System.out.println("Processing card payment...");
	}
}

class PayPal implements Payment{
	@Override
	public void pay() {
		System.out.println("Processing PayPal payment...");
	}
}

/*
 * Enums and switch dont adhere to the OCP principle because if we want to add new payment type, we have to modify the existing code, which violates the OCP principle.
 */
