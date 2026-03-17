package com.epam.practice.designs.behaviour;


/* order of importance
 * =========================
 	1️ Observer
	2️ Template Method
	3 Chain of Responsibility
	4️ Command
	5️ State
	6️ Mediator
	7️ Iterator
	8️ Memento
	9️ Visitor
 	10 Interpreter
 */


/*
 * passes the request along the chain of potential handlers until one of them handles it. 
 * The handler is typically an object that implements a specific interface, 
 * and each handler in the chain has a reference to the next handler. 
 * When a request is received, the handler checks if it can process the request; if it can, it does so,
 *  otherwise it passes the request to the next handler in the chain. 
 *  like line 1, line 2 and manager in a company. if line 1 can not handle the request, 
 *  it will pass to line 2 and if line 2 can not handle the request, it will pass to manager.
 *  Ex:: ATM machine dispensing cash. The ATM has different handlers for different denominations of currency (e.g., $100, $50, $20).
 *  Spring Ex : filterChain.doFilter(request, response) in spring framework is an example of chain of responsibility pattern.
 */
public class ChainOfRespExample {
	public static void main(String[] args) {
		CurrencyHandler fiveHundredHandler = new FiveHundredHandler();
		CurrencyHandler twoHundredHandler = new TwoHundredHandler();
		CurrencyHandler oneHundredHandler = new OneHundredHandler();
		
		fiveHundredHandler.setNextHandler(twoHundredHandler);
		twoHundredHandler.setNextHandler(oneHundredHandler);
		
		int amount = 1327;
		fiveHundredHandler.handleRequest(amount);
	}
}


abstract class CurrencyHandler { //This is the handler which stores the next handler in the chain and defines the method to handle the request.
	protected CurrencyHandler nextHandler;

	public void setNextHandler(CurrencyHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract void handleRequest(int amount);
}


class FiveHundredHandler extends CurrencyHandler{ //this is the first handler in the chain. if there is remainder and there is next handler,
	//it will be handed to next handler, otherwise it will print the message that it cannot dispense the remaining amount.
	
	@Override
	public void handleRequest(int amount) {
		if(amount>=500) {
			int count = amount/500;
			System.out.println("Dispensing " + count + " 500 rupee notes");
			 int remainder = amount % 500;
			 if(remainder>0 && nextHandler!=null) {
				 nextHandler.handleRequest(remainder); // passing the remaining amount to the next handler in the chain
			 }
			 else if(remainder>0) {
				 System.out.println("Cannot dispense the remaining amount: " + remainder);
			 }
		}
	}
}

class TwoHundredHandler extends CurrencyHandler{

	@Override
	public void handleRequest(int amount) {
		if(amount>=200) {
			int count = amount/200;
			System.out.println("Dispensing " + count + " 200 rupee notes");
			int remainder = amount % 200;
			if(remainder>0 && nextHandler!=null) {
				 nextHandler.handleRequest(remainder); // passing the remaining amount to the next handler in the chain
			 }
			 else if(remainder>0) {
				 System.out.println("Cannot dispense the remaining amount: " + remainder);
			 }
		}
	}
}
class OneHundredHandler extends CurrencyHandler{

	@Override
	public void handleRequest(int amount) {
		if(amount>=100) {
			int count = amount/100;
			System.out.println("Dispensing " + count + " 100 rupee notes");
			int remainder = amount % 100;
			if(remainder>0 && nextHandler!=null) { //mostly this is last in our case so it prints the remianing amount.
				 nextHandler.handleRequest(remainder); // passing the remaining amount to the next handler in the chain
			 }
			 else if(remainder>0) {
				 System.out.println("Cannot dispense the remaining amount: " + remainder);
			 }
		}
	}
}
