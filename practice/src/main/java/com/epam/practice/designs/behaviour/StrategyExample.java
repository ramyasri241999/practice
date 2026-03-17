
package com.epam.practice.designs.behaviour;
import java.util.function.Consumer;
public class StrategyExample {

	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		
		cart.setPaymentStrategy(new CreditCardPayment());
		cart.checkout(100);
		
		cart.setPaymentStrategy(new PayPalPayment());
		cart.checkout(200);
		
		
		PaymentStrategy creditCardPayment = PaymentType.CREDIT_CARD;
		creditCardPayment.pay(300); // enum style implementation of strategy pattern
		
		
		PaymentTypeEnum.CARD.pay(500.0); // Constructor + lambda style implementation of strategy pattern using enum and functional interface
	}
}

interface PaymentStrategy {
	void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
	@Override
	public void pay(int amount) {
		System.out.println("Paid " + amount + " using Credit Card.");
	}
}


class PayPalPayment implements PaymentStrategy {
	@Override
	public void pay(int amount) {
		System.out.println("Paid " + amount + " using PayPal.");
	}
}

class ShoppingCart {
	private PaymentStrategy paymentStrategy;
	
	public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}
	
	public void checkout(int amount) {
		if (paymentStrategy == null) {
			System.out.println("Please select a payment method.");
			return;
		}
		paymentStrategy.pay(amount);
	}
}


enum PaymentType implements PaymentStrategy {  // best option to implement strategy pattern is to use enum, because enum is a singleton and we can easily switch between different strategies by using enum constants, and we don't have to create multiple instances of the same strategy, we can simply use the enum constants to access the strategies
	CREDIT_CARD {
		@Override
		public void pay(int amount) {
			System.out.println("Paid " + amount + " using Credit Card enum.");
		}
	},
	PAYPAL {
		@Override
		public void pay(int amount) {
			System.out.println("Paid " + amount + " using PayPal enum.");
		}
	}
	
}



enum PaymentTypeEnum {    //Constructor +lambda style implementation of strategy pattern using enum and functional interface, this is more concise and readable, and we can easily add new payment types without modifying the existing code.

    CARD((amount) -> System.out.println("Paid using Card "+amount)),
    UPI((amount) -> System.out.println("Paid using UPI")),
    NETBANKING((amount) -> System.out.println("Paid using Netbanking"));

    private final Consumer<Double> strategy;  // we can use consumer or function or bifunction instead of runnable if we want to pass parameters to the strategy
//	private final Runnable strategy;  // we can use runnable if we don't want to pass parameters to the strategy
    PaymentTypeEnum(Consumer<Double> strategy) {  // if parameters are complex we can use functional interface.
        this.strategy = strategy;
    }
    
    //PaymentTypeEnum(Runnable strategy) {
    //        this.strategy = strategy;
    	//}

    public void pay(Double amount) {
        strategy.accept(amount);
    }
    
   //public void pay() {
    //     strategy.run();
	//}
}


