package com.epam.practice.java11features;

import java.util.List;

/*
 * Java 11 features::
 * 1. Local-Variable Syntax for Lambda Parameters: Java 11 allows you to use the var keyword in lambda expressions, making it easier to declare parameters without specifying their types explicitly.
 * 2. String Methods: Java 11 introduced several new methods for the String class, including isBlank(), lines(), strip(), stripLeading(), and stripTrailing(). These methods provide additional functionality for working with strings.
 */
public class Java11Example {
public static void main(String[] args) {
	String str = "   Hello, World!   ";
	
	// Using isBlank() to check if the string is blank
	System.out.println("Is the string blank? " + str.isBlank());  // Output: false
	
	String lineStr = "Line 1\nLine 2\nLine 3";
	// Using lines() to split the string into lines
	lineStr.lines().forEach(System.out::println); // Output: Line 1, Line 2, Line 3
	
	// Using strip() to remove leading and trailing whitespace
	System.out.println("Stripped string: '" + str.strip() + "'"); // Output: 'Hello, World!'
	
	// Using stripLeading() to remove leading whitespace
	System.out.println("Leading stripped string: '" + str.stripLeading() + "'"); // Output: 'Hello, World!   '
	
	// Using stripTrailing() to remove trailing whitespace
	System.out.println("Trailing stripped string: '" + str.stripTrailing() + "'"); // Output: '   Hello, World!'
	
	// Using var in lambda parameters
	var list = List.of(1, 2, 3, 4, 5);
	list.forEach((var number) -> System.out.println(number)); // Output: 1, 2, 3, 4, 5
	
	var a= 3;var b = 10; // Using var for local variable declaration
	//a="Ramya"; - cannot convert from String to int. var is not a dynamic type, it is still statically typed. the type of variable is determined at compile time based on the assigned value.
	
	
	
}
}
