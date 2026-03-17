package com.epam.practice.designs.behaviour;


/*
 * Defines the skeleton of an algorithm in an operation, deferring some steps to subclasses. 
 * Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.
 * Ex: abstract<List> - which arraylist, linkedlist, vector etc. all have the same structure but different implementations of the methods.
 * Ex: JdbcTemplate - which provides a template for executing SQL queries, and the subclasses can provide different implementations for different databases.
 */
public class TemplateExample {
	public static void main(String[] args) {
		Beverage tea = new Tea();
		tea.prepareDrink();
		
		Beverage softDrink = new SoftDrink();
		softDrink.prepareDrink();
	}
}


abstract class Beverage{
	public final void prepareDrink() 
	{ //final method to prevent overriding. This is the template method that defines the skeleton of the algorithm.
		boilWater();
		pourInCup();
		addIngredients();
	}
	
	private void boilWater() {
		System.out.println("Boiling water");
	}
	
	private void pourInCup() {
		System.out.println("Pouring into cup");
	}
	
	abstract void addIngredients();
}

class Tea extends Beverage{
	
	@Override
	void addIngredients() { // this is the step that is deferred to the subclass, and it can be implemented differently for different beverages. This is the step that varies, and the rest of the algorithm remains the same.
		System.out.println("Adding ingredients");
	}
}
class SoftDrink extends Beverage{
	
	@Override
	void addIngredients() {// this is the step that is deferred to the subclass, and it can be implemented differently for different beverages. This is the step that varies, and the rest of the algorithm remains the same.	
		System.out.println("Adding ingredients");
	}
}


/*
 * Best Practices

✔ Keep template method final
✔ Use protected methods for steps
✔ Avoid too many hooks
✔ Use composition if algorithm varies too much
 */
