package com.epam.practice.solidprinciples;
/*
 * Definiton as per Barbara Liskov: If S is a subtype of T, then objects of type T may be replaced with objects of type S without altering any of the desirable properties of the program (correctness, task performed, etc.)
 * Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.
 * If b extends a, then it should behave in the same way as a. If b does not behave in the same way as a, then it violates the Liskov Substitution Principle.
 * Ex: If we have a class Bird with a method fly(), and we have a subclass Penguin that extends Bird, then Penguin should be able to fly without any issues. If Penguin cannot fly, then it violates the Liskov Substitution Principle.
 * sol: we can have interface Flyable and have the fly() method in that interface, and then have the Bird class implement the Flyable interface. This way, we can have different types of birds that can fly and some that cannot fly without violating the Liskov Substitution Principle.*
 */
public class LSPDemo {
	public static void main(String[] args) {
		Account savingsAccount = new SavingsAccount();
		Account currentAccount = new CurrentAccount();
		Account fixedDepositAccount = new FixedDepositAccount();
		savingsAccount.withdraw(100);
		currentAccount.withdraw(200);
		try {
			fixedDepositAccount.withdraw(300);
		} catch (UnsupportedOperationException e) {
			System.out.println(e.getMessage());
		}
	}
}


abstract class Account {
	abstract void withdraw(double amount);
	abstract void deposit(double amount);
}

class SavingsAccount extends Account {
	@Override
	void withdraw(double amount) {
		System.out.println("Withdrawing " + amount + " from Savings Account");
	}

	@Override
	void deposit(double amount) {
		// TODO Auto-generated method stub
		
	}
}

class CurrentAccount extends Account {
	@Override
	void withdraw(double amount) {
		System.out.println("Withdrawing " + amount + " from Current Account");
	}

	@Override
	void deposit(double amount) {
		// TODO Auto-generated method stub
		
	}
}


class FixedDepositAccount extends Account {
	@Override
	void withdraw(double amount) {
		throw new UnsupportedOperationException("Withdrawals are not allowed from a Fixed Deposit Account");
	}

	@Override
	void deposit(double amount) {
		// TODO Auto-generated method stub
		
	}
}

/*
 * violation: if we have a class FixedDepositAccount that extends Account, and we try to withdraw money from it, it will throw an exception because withdrawals are not allowed from a Fixed Deposit Account. 
 * This violates the Liskov Substitution Principle because we cannot replace an object of type Account with an object of type FixedDepositAccount without breaking the application.
 * 2. if child is stricter than parent, then it violates the Liskov Substitution Principle because the child class cannot be substituted for the parent class without breaking the application. 
 * For example, if we have a class Rectangle with a method setWidth() and setHeight(), and we have a subclass Square that extends Rectangle, then Square should be able to set its width and height independently. 
 * If Square overrides the setWidth() and setHeight() methods to set both width and height to the same value, then it violates the Liskov Substitution Principle 
 * because we cannot substitute a Square object for a Rectangle object without breaking the application.
 * 
 * 1.precondition violation
 * 2.postcondition violation
 * 3.Exception should not be thrown by the child class if it is not thrown by the parent class
 * 4.state invariants must hold for the child class if they hold for the parent class
 * 
 * 
 * prefer composition over inheritance to avoid Liskov Substitution Principle violation.
 */
