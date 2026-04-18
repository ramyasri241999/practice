package com.epam.practice.java9features;

import java.util.List;

/*
 * Java 9 features:
 * 1. Modular System: Java 9 introduced the Java Platform Module System (JPMS), which allows developers to modularize their applications and manage dependencies more effectively.
 * 2. JShell: Java 9 introduced a new tool called JShell, which is an interactive REPL (Read-Eval-Print Loop) for Java. It allows developers to quickly test and experiment with Java code without the need for a full development environment.
 * 3. Improved Stream API: Java 9 added several new methods to the Stream API, such as takeWhile(), dropWhile(), and iterate(), which provide more flexibility for working with streams of data.
 * 4. Private Methods in Interfaces: Java 9 allows developers to define private methods in interfaces, which can be used to share common code between default methods without exposing it to the implementing classes.
 * 5.compact Strings: Java 9 introduced a new string representation called Compact Strings, which uses a more efficient internal representation for strings that contain only Latin-1 characters, resulting in reduced memory usage and improved performance.
 */
public class MinorJava9Examples {
	
	public static void main(String[] args) {
		/*
		 * takewhile, dropwhile, iterate
		 */
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); //immutable list of numbers from 1 to 10
		numbers.stream()
			.takeWhile(n -> n < 5)
			.forEach(System.out::println); // Output: 1, 2, 3, 4
		numbers.stream()
			.dropWhile(n -> n < 5)
			.forEach(System.out::println); // Output: 5, 6, 7, 8, 9, 10
		
		// Using iterate() to generate a stream of even numbers
//		List<Integer> evenNumbers = List.iterate(0, n -> n + 2)
//			.limit(10)
//			.toList();
		
		//compact Strings example
		String latin1String = "Hello, World!"; // This string contains only Latin-1 characters which i
		/*
		 * Reduces memory usage by using a more efficient internal representation for strings that contain only Latin-1 characters.
		 * uses bytes instead of chars to store the string data, which can save memory when dealing with strings that contain only Latin-1 characters.
		 */
		
		//try with resource example
		
	}
}
