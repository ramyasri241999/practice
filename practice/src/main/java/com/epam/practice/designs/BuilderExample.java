package com.epam.practice.designs;

public class BuilderExample {
	//Book book = new Book.Builder().setTitle("Design Patterns").setAuthor("Gang of Four").setPrice(29.99).build();
	
	Book book = new Book.Builder("Design Patterns", 29.99).setAuthor("Gang of Four").build();
}
//Builder Pattern separates the construction of a complex object from its representation, 
//allowing step-by-step object creation with optional parameters and improved readability.
class Book{
	private String title;
	private String author;
	private double price;
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public double getPrice() {
		return price;
	}
	
	private Book(Builder builder) {
		this.title = builder.title;
		this.author = builder.author;
		this.price = builder.price;
	}
	
	public static class Builder{
		private String title;
		private String author;
		private double price;
		
		public Builder(String title, double price) {  // this is for required fields, we can also have a constructor with all the fields and then we can have setter methods for optional fields
			this.title = title;
			this.price = price;
			
		}
		
//		public Builder setTitle(String title) {
//			this.title = title;
//			return this;
//		}
//		
		public Builder setAuthor(String author) {  //optional field, we can choose to set it or not, if we don't set it, it will be null
			this.author = author;
			return this;
		}
		
//		public Builder setPrice(double price) {
//			this.price = price;
//			return this;
//		}
		
		public Book build() {
			return new Book(this);
		}
		
	}
	
}
