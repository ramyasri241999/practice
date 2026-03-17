package com.epam.practice.advancedJava;

import java.util.Optional;

public class OptionalExample {
	public static void main(String[] args) {
		//without optional - problem of null pointer exception
		String str = null;
		//System.out.println(str.length()); // this will throw null pointer exception
		
		//with optional - we can avoid null pointer exception
		Optional<String> optionalStr = Optional.ofNullable(str); // This will create an Optional object that may or may not contain a non-null value
		
		if(optionalStr.isPresent()) { // This checks if the Optional contains a non-null value
			System.out.println(optionalStr.get().length()); // This will print the length of the string if it is present
		} else {
			System.out.println("String is null"); // This will print if the string is null
		}
		
		//you may think that we can just check for null and then get the length, but using Optional makes our code more readable and less error-prone. 
		//It clearly indicates that the value may be absent and forces us to handle that case explicitly.
		//eg: if(str != null) {
		
		
		//with optional we can check for nulls of book, author, address and city in a more elegant way using map and orElse
		Optional<Book> optionalBook = Optional.ofNullable(new Book());
		String city = optionalBook
						.map(Book::getAuthor) // This will return an Optional<Author>
						.map(Author::getAddress) // This will return an Optional<Address>
						.map(Address::getCity) // This will return an Optional<String>
						//.ifPresent(cityName -> System.out.println("City: " + cityName)); // This will print the city name if it is present
						.orElse("City not available"); // This will return the city name if present, otherwise it will return "City not available"
	
	//we can also use orElseGet to provide a default value using a supplier, which is useful if the default value is expensive to compute
		String city2 = optionalBook
						.map(Book::getAuthor)
						.map(Author::getAddress)
						.map(Address::getCity)
						.orElseGet(() -> "City not available"); // This will return the city name if present, otherwise it will return "City not available" using a supplier
		//or else will execute even if the value is present  but orElseGet will only trigger when the value is empty
		//orElse     → eager execution
		//orElseGet  → lazy execution
		System.out.println("City: " + city);
		System.out.println("City: " + city2);
		//throwing an exception if the value is not present
		String city3 = optionalBook
						.map(Book::getAuthor)
						.map(Author::getAddress)
						.map(Address::getCity)
						.orElseThrow(() -> new RuntimeException("City not available")); // This will return the city name if present, otherwise it will throw a RuntimeException
	}
}
/*
 * we have book class which has author class which has address class which has city name. we want to get the city name of the author of the book. 
 * how can we do that without null pointer exception?
 * lets first see how we can do that without optional and then see how we can do that with optional.
 * without optional:
 * Book book = new Book();	
 * String city = book.getAuthor().getAddress().getCity(); // this will throw null pointer exception if any of the objects is null
 * we can check for null at each step, but that will make our code messy and hard to read.
 * if(book.getAuthor() != null) { 
 * 	if(book.getAuthor().getAddress() != null) {
 * 		if(book.getAuthor().getAddress().getCity() != null) {
 * 			String city = book.getAuthor().getAddress().getCity();
 * 		}
 * 	}
 * }
 */
class Book{
	private Author author;

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
}

class Author{
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
class Address{
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}


