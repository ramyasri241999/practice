package com.epam.practice.designs.structural;

public class DecoratorExample {//Definition: Allows behavior to be added to an individual object, dynamically, without affecting the behavior of other objects from the same class.
public static void main(String[] args) {
	Coffee coffee = new SimpleCoffee(50);  // creating a simple coffee with a cost of 50
	System.out.println(coffee.getdescription() + " costs " + coffee.cost());  // output: Simple Coffee costs 50
	
	coffee = new MilkDecorator(coffee);  // decorating the coffee with milk, and the cost of milk is 10
	System.out.println(coffee.getdescription() + " costs " + coffee.cost());  // output: Simple Coffee with milk costs 60
	
	coffee = new SugarDecorator(coffee);  // decorating the coffee with sugar, and the cost of sugar is 5`
	System.out.println(coffee.getdescription() + " costs " + coffee.cost());  // output: Simple Coffee with milk with sugar costs 65
	
	
	 Coffee anotherCoffee = new MilkDecorator(new SugarDecorator(new SimpleCoffee(40))); // creating another coffee with a cost of 40, and decorating it with sugar and milk, and the cost of sugar is 5 and the cost of milk is 10, so the total cost will be 40 + 5 + 10 = 55
     System.out.println(anotherCoffee.getdescription() + " costs " + anotherCoffee.cost());  // output: Simple Coffee with sugar with milk costs 55
}
}

interface Coffee{
	String getdescription() ;
	int cost();
}

class SimpleCoffee implements Coffee{
	private int cost;
	
	public SimpleCoffee(int cost) {
		this.cost = cost;
	}
	@Override
	public int cost() {
		return cost;
	}
	@Override
	public String getdescription() {
				return "Simple Coffee";
	}
}

abstract class CoffeeDecorator implements Coffee{
	protected Coffee coffee;  // composition - we are using the coffee interface to implement the milk decorator, and we can decorate any coffee that implements the coffee interface
	CoffeeDecorator(Coffee coffee){
		this.coffee = coffee;
	}
}

class MilkDecorator extends CoffeeDecorator{
MilkDecorator(Coffee coffee) {
		super(coffee);
	}
	@Override
	public String getdescription() {
		
		return "" + coffee.getdescription() + " with milk";  // adding the description of milk to the description of the coffee
	}

	@Override
	public int cost() {
		
		return coffee.cost() + 10;  // adding the cost of milk to the cost of the coffee
	}
	
}

class SugarDecorator extends CoffeeDecorator{
	
	SugarDecorator(Coffee coffee) {
		super(coffee);
	}
	@Override
	public String getdescription() {
		
		return "" + coffee.getdescription() + " with sugar";  // adding the description of sugar to the description of the coffee
	}

	@Override
	public int cost() {
		
		return coffee.cost() + 5;  // adding the cost of sugar to the cost of the coffee
	}
}