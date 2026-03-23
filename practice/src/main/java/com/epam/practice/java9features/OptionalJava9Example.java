package com.epam.practice.java9features;

import java.util.Optional;

/*
 * In java 9, the Optional class was enhanced with several new methods to provide more functionality and improve the usability of the Optional API. 
 * Some of the new methods added in Java 9 include:
 * 1. ifPresentOrElse(): This method allows you to specify an action to be performed if a value is present, and another action to be performed if a value is not present. It takes two parameters: a Consumer that defines the action to be performed if a value is present, and a Runnable that defines the action to be performed if a value is not present.
 * 2. or(): This method allows you to provide an alternative Optional to be returned if the original Optional is empty. It takes a Supplier that provides the alternative Optional.
 * 3. stream(): This method converts an Optional into a Stream. If a value is present, it returns a Stream containing that value; otherwise, it returns an empty Stream. This can be useful for chaining Optional operations with Stream operations.
 * Overall, these new methods in Java 9 enhance the functionality of the Optional class and provide developers with more options for handling optional values in a more concise and expressive way.
 */
public class OptionalJava9Example {
	
	public static void main(String[] args) {
		Optional<String> optionalValue = Optional.of("Hello, World!");

		// Using ifPresentOrElse() to handle both cases
		optionalValue.ifPresentOrElse(
			value -> System.out.println("Value is present: " + value),
			() -> System.out.println("Value is not present")
		);

		// Using or() to provide an alternative Optional
		Optional<String> alternativeOptional = optionalValue.or(() -> Optional.of("Alternative Value"));
		System.out.println("Result of or(): " + alternativeOptional.get());

		// Using stream() to convert Optional to Stream
		optionalValue.stream()
			.map(String::toUpperCase)
			.forEach(System.out::println);
	}
}
