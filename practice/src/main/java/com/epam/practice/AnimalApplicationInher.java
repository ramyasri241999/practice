package com.epam.practice;




public class AnimalApplicationInher {

	
	public static void main(String[] args) {
		//SpringApplication.run(UserApplication.class,args);
		Animal a = new Animal();
		System.out.println(a.legCount()); //4
		a.sound(); // Animal Sound
	//	a.isWild(); // no visibility as it is private
		Animal.eat(); // animal eats -- static methods should be called static way
		a.live(); //Animal lives
		a.cost(new Object());
		
		Animal an = new Dog();
		an.sound(); //Dog sound
		System.out.println(an.legCount()); //4  --  inheritance
		//an.fetch();  -- compile time error since compiler only knows the reference
		an.live(); //Dog lives 
		
		
		Dog.eat();  // Animal eats - no method of eat in dog class so animal eat method is called.
		Dog d = new Dog();
		System.out.println(d.fetch()); //ball fetched
		System.out.println(d.legCount()); //4
		d.sound(); // Dog sound
		d.live(); //Dog lives
		//Tiger t = new Animal(); // compliation error . cannot convert Animal to Tiger
		
		Animal ani = new Tiger();
		//ani.isWild();  isWild has no visability 
		ani.cost(new Object());
		ani.cost("Hello"); // this is not overridding but overloading so decides at the compile time -- calls animal method because it has object
		
		Tiger t = new Tiger();
		Tiger.eat(); // Tiger eats
		System.out.println("is wild:: "+t.isWild()); //true
		t.live(); //Animal lives
		t.cost(new Object());
		t.cost("Hi");  
	}
}


class Animal{
	 void sound(){
		 System.out.println("Animal Sound"); 
	 }
	 
	 int legCount() {
		 return 4;
	 }
	 
	 private boolean isWild() {  //visibility test
		 return false;
	 }
	 static void eat() {  // class level test
		 System.out.println("Animal eats"); 
	 }
	 
	 public Object live() {
		 System.out.println("Animal lives"); 
		 return new Object();
	 }
	 void cost(Object obj) {
			System.out.println("Animal Cost is ::");
		}
}


class Dog extends Animal{
	
	@Override // checks compilation errors
	void sound() {
		System.out.println("Dog sound");
		
	}
	
	String fetch() {
		return "ball fetched";
	}
	
//	protected Object live() { 
//		return new Object();
//		} //Cannot reduce the visibility of the inherited method(protected) from Animal(public) . we can use reverse
	
	public String live() {   // Object to String accepeted since its narrowing
		System.out.println("Dog lives");
		return "Dog Lives";    
	}
	
	
}

class Tiger extends Animal{
	void sound() {
		System.out.println("Tiger Sound ");
	}
	
	public boolean isWild() {
		return true;
	}
	
	static void eat() {
		 System.out.println("Tiger eats"); 
	 }
	
	protected void cost(String obj) {
		System.out.println("Tiger Cost is ::");   //we increased the visibility and also covariant parameters
	}
}