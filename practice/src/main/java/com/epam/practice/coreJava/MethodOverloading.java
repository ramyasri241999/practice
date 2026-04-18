package com.epam.practice.coreJava;

public class MethodOverloading {
	
	public static void main(String[] args) {
		MethodOverloadingExample example = new MethodOverloadingExample();
		System.out.println(example.add(5, 10)); // calls the method with two int parameters
		System.out.println(example.add(5.5, 10.5)); // calls the method with two double parameters
		System.out.println(example.add(5, 10, 15)); // calls the method with three int parameters
	}
	

}

class MethodOverloadingExample{
	public  int add(int a, int b) {
		return a + b;
	}
	
//	public String add(int a, int b) {   just return type alone cannot be used to overload a method, the method signature must be different (number of parameters or type of parameters)
//		return "The sum of " + a + " and " + b + " is " + (a + b);
//	}
	
	public  double add(double a, double b) {
		return a + b;
	}
	
	public  int add(int a, int b, int c) {
		return a + b + c;
	}
}