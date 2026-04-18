package com.epam.practice.java17features;

public class SealedClassExample {

}

sealed class Vehicle permits Car, Bike {
	public void drive() {
		System.out.println("Driving a vehicle");
	}
}
/*
 * classes that extend a sealed class must be final or sealed. 
 * If they are final, they cannot be extended further. 
 * If they are sealed, they must specify which classes can extend them.
 * if they are non-sealed, they can be extended by any class.but that class must be in the same package as the sealed class.
 */
final class Car extends Vehicle { 
	@Override
	public void drive() {
		System.out.println("Driving a car");
	}
}

final class Bike extends Vehicle {
	@Override
	public void drive() {
		System.out.println("Driving a bike");
	}
}
