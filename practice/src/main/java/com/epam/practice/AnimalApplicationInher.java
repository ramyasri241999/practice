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
		ani.cost(new Object());// prints Animal Cost is ::  -- calls animal method because it has object
		ani.cost("Hello"); // this is not overridding but overloading so decides at the compile time -- calls animal method because it has object
		//the cost method in tiger class is not overriding the cost method in animal class because it has different parameters, so it is overloading the cost method in animal class, and since the reference type is Animal, it will call the cost method in animal class for both calls.
		
		
		Tiger t = new Tiger();
		Tiger.eat(); // Tiger eats
		System.out.println("is wild:: "+t.isWild()); //true
		t.live(); //Animal lives
		t.cost(new Object());  // prints Animal Cost is ::  -- calls animal method because it has object
		t.cost("Hi");  // prints Tiger Cost is ::  -- calls tiger method because it has String.does protected access modifier allows access within the same package 
		//and also allows access to subclasses, so the cost method in tiger class can be accessed by the tiger class and also by the animal class since it is a subclass of animal class, but it cannot be accessed by any other class that is not a subclass of animal class or is not in the same package.
	
	
		CompleteInterfaceImpl impl = new CompleteInterfaceImpl();
		impl.abstractMethod(); //Implementing abstract method
	
		AbstractExampleImpl abstractExample = new AbstractExampleImpl("Abstract Example");
		abstractExample.print(); //Name: Abstract Example
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


abstract class AbstractExample{
	
	protected String name ;
	
public AbstractExample(String name) {
		this.name = name;
		System.out.println("Inside Abstract example constructor");
	}
	
	public abstract void print() ;  //must implement
	
	void call() {
		System.out.println(""); //already implemented
	}
	
	 // 🔹 Final Method (cannot override)
    public final void sleep() {
        System.out.println(name + " is sleeping");
    }

    // 🔹 Static Method (belongs to class)
    public static void info() {
        System.out.println("Animals are living beings");
    }
}

class AbstractExampleImpl extends AbstractExample{

	public AbstractExampleImpl(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void print() {
		System.out.println("Name: " + name);
		
	}
	
}
interface CompleteInterface {

    // implicitly: public static final
    int CONSTANT_VALUE = 100;

    String NAME = "InterfaceDemo";

    // implicitly: public abstract
    void abstractMethod();

    default void defaultMethod() {
        System.out.println("Inside default method");
        privateHelper();
    }

    static void staticMethod() {
        System.out.println("Inside static method");
        privateStaticHelper();
    }

    private void privateHelper() {
        System.out.println("Private instance helper");
    }
    private static void privateStaticHelper() {
        System.out.println("Private static helper");
    }
}

class CompleteInterfaceImpl implements CompleteInterface {
	int CONSTANT_VALUE = 200; // this is not overriding the interface variable since it is static and final, so it is a new variable in the class that has the same name as the interface variable, but it is not related to the interface variable, and it can have a different value than the interface variable.
// remaining method of the interface must be implemented since it is not a default method and also not a static method, and it is not a private method, so it must be implemented by the class that implements the interface.
	@Override
	public void abstractMethod() {
		System.out.println("Implementing abstract method");
		System.out.println("Constant value from interface: " + CompleteInterface.CONSTANT_VALUE); // Accessing the constant value from the interface using the interface name since it is static and final, and it cannot be accessed using the instance of the class that implements the interface since it is not inherited by the class, but it can be accessed using the interface name.
		System.out.println("Constant value from class: " + CONSTANT_VALUE); // Accessing the constant value from the class using the instance of the class since it is a new variable in the class that has the same name as the interface variable, but it is not related to the interface variable, and it can have a different value than the interface variable.
		System.out.println("Name from interface: " + CompleteInterface.NAME); // Accessing the name from the interface using the interface name since it is static and final, and it cannot be accessed using the instance of the class that implements the interface since it is not inherited by the class, but it can be accessed using the interface name.
		//System.out.println("Name from class: " + NAME); // Accessing the name from the class using the instance of the class since it is
	}
	
}
