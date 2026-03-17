package com.epam.practice.designs;

import java.util.function.Supplier;

public class FactoryDesignExample {
	
	//Definition: Defines an interface for creating an object but lets subclasses decide which class to instantiate.
	public static void main(String[] args) {
		
		ShapeFactory shapeFactory = new ShapeFactory();
		
//		Shape circle = shapeFactory.getShape("CIRCLE");
//		circle.draw();
//		
//		Shape rectangle = shapeFactory.getShape("RECTANGLE");
//		rectangle.draw();
//		
//		Shape square = shapeFactory.getShape("SQUARE");
//		square.draw();
		
		
		Shape circle = shapeFactory.getShape(ShapeFactory.ShapeType.CIRCLE);
		circle.draw();
		
		Shape rectangle = shapeFactory.getShape(ShapeFactory.ShapeType.RECTANGLE);
		rectangle.draw();
		
		Shape square = shapeFactory.getShape(ShapeFactory.ShapeType.SQUARE);
		square.draw();
		
		//enum implementation
		Shape circle1 = ShapeType.CIRCLE.create();
	}
}

	class ShapeFactory {
		enum ShapeType {
		    CIRCLE, RECTANGLE, SQUARE
		}
//		public Shape getShape(String shapeType) {
//			if(shapeType == null) {
//				return null;
//			}
//			if(shapeType.equalsIgnoreCase("CIRCLE")) {   // we have to write  "CIRCLE".equalsIgnoreCase(shapeType) to avoid NullPointerException if shapeType is null, but we are already checking for null at the beginning of the method, so we can safely use shapeType.equalsIgnoreCase("CIRCLE")
//				return new Circle();
//			} else if(shapeType.equalsIgnoreCase("RECTANGLE")) {
//				return new Rectangle();
//			} else if(shapeType.equalsIgnoreCase("SQUARE")) {
//				return new Square();
//			}
//			return null;
//		
//		}
		
//		public Shape getShape(String shapeType) {
//		    if (shapeType == null) return null;
//
//		    switch (shapeType.toUpperCase()) {
//		        case "CIRCLE": return new Circle();
//		        case "RECTANGLE": return new Rectangle();
//		        case "SQUARE": return new Square();
//		        default: return null;
//		    }
//		}
		
		public Shape getShape(ShapeType shapeType) {  // prefer using enum instead of string to avoid case sensitivity and typos
		    if (shapeType == null) return null;

		    switch (shapeType) {
		        case CIRCLE: return new Circle();
		        case RECTANGLE: return new Rectangle();
		        case SQUARE: return new Square();
		        default: return null;
		    }
	}
	}
	
	
	
	interface Shape {
		void draw();
	}
	
	class Circle implements Shape {
		@Override
		public void draw() {
			System.out.println("Drawing a Circle");
		}
	}
	
	class Rectangle implements Shape {
		@Override
		public void draw() {
			System.out.println("Drawing a Rectangle");
		}
	}
	class Square implements Shape {
		@Override
		public void draw() {
			System.out.println("Drawing a Square");
		}
	}
	
	
	
	//Advanced level factory design pattern using enum and functional interface to avoid switch case and if else statements, this is more scalable and maintainable as we can easily add new shapes without modifying the existing code.
	 enum ShapeType {
	    CIRCLE(Circle::new),
	    RECTANGLE(Rectangle::new),
	    SQUARE(Square::new);

	    private final Supplier<Shape> constructor;

	    ShapeType(Supplier<Shape> constructor) {
	        this.constructor = constructor;
	    }

	    public Shape create() {
	        return constructor.get();
	    }
	}
	
	
