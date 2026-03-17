package com.epam.practice.designs.structural;

public class FacadeExample {
	
	//Definition: Provides a simplified interface to a complex subsystem, making it easier to use and understand.
	public static void main(String[] args) {
		OrderFacade order = new OrderFacade(); //client interacts with the OrderFacade, which provides a simplified interface to the complex subsystem of inventory, payment, shipping, and notification services
		order.placeOrder("user123", "product456", "credit card", 100.0);
	}

}

class InventoryService{
	public boolean checkProductAvailability(String product) {
		System.out.println("Checking availability of " + product);
		return true;  // for simplicity, we are assuming that the product is always available
	}
}

class PaymentService{
	public void processPayment(String paymentMethod) {
		System.out.println("Processing payment using " + paymentMethod);
	}
}

class ShippingService{
	public void arrangeShipping(String address) {
		System.out.println("Arranging shipping to " + address);
	}
}

class NotificationService{
	public void sendOrderConfirmation(String email) {
		System.out.println("Sending order confirmation to " + email);
	}
}

class OrderFacade{
	private InventoryService inventory;
	private PaymentService payment;
	private ShippingService shippingService;
	private NotificationService notification;
	private String product;
	
	public OrderFacade(){
		this.inventory = new InventoryService();
		this.payment = new PaymentService();
		this.shippingService = new ShippingService();
		this.notification = new NotificationService();
	}
	
	public void placeOrder(String userId, String productId,String paymentMethod, double amount) { // this method provides a simplified interface to the client for placing an order, and it internally interacts with the complex subsystem of inventory, payment, shipping, and notification services to complete the order placement process
		if(inventory.checkProductAvailability(productId)) {
			payment.processPayment(paymentMethod);
			shippingService.arrangeShipping("123 Main St, Anytown, USA");
			notification.sendOrderConfirmation("ramya@gmail.com");
			
			System.out.println("Order placed successfully for user " + userId + " for product " + productId);
		}
		else {
			System.out.println("Product " + productId + " is not available.");
		}
	}
	
}
