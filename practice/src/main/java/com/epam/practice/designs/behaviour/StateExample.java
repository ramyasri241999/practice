package com.epam.practice.designs.behaviour;

/*
 * State design Pattern : Varies based on the state of the object, the behavior of the object changes. 
 * It allows an object to alter its behavior when its internal state changes. The object will appear to change its class.
 * Ex: An order can be in different states like new, processing, shipped, delivered. Based on the state of the order, the behavior of the order will change.
 * Spring Ex: transaction management, based on the state of the transaction, the behavior of the transaction will change.
 */
public class StateExample {
	
	public static void main(String[] args) {
		Order order = new Order();
		order.status();
		order.status();
		order.status();
		order.status();
		order.status();
	}
}

interface OrderState{
	void next(Order order);
}

class Order{
	private OrderState state;
	
	public Order() {
		this.state = new NewOrderState();
	}
	
	public void setstate(OrderState state) {
		this.state = state;
	}
	
	public void status() {
		this.state.next(this);
	}
}

class NewOrderState implements OrderState{
	
	@Override
	public void next(Order order) {
		System.out.println("Order is in new state");
		order.setstate(new ProcessingOrderState());
	}
}

class ProcessingOrderState implements OrderState{
	
	@Override
	public void next(Order order) {
		System.out.println("Order is in processing state");
		order.setstate(new ShippedOrderState());
	}
}

class ShippedOrderState implements OrderState{
	@Override
	public void next(Order order) {
		System.out.println("Order is in shipping state");
		order.setstate(new DeliveredOrderState());
	}
}

class DeliveredOrderState implements OrderState{
	@Override
	public void next(Order order) {
		System.out.println("Order is in delivered state");
	}
}

/*
* Best Practices

✔ Keep state logic small
✔ Avoid storing too much data inside states
✔ Use immutable state objects
✔ Manage transitions carefully
*/
