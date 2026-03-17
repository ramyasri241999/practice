package com.epam.practice.solidprinciples;

public class SingleResponsiblityExample {
		public static void main(String[] args) {
		
		Paint paint = new Paint("Sunset", "Red");
		Painting painting = new Painting();
		painting.paint(paint);
		painting.save(paint); // single responsibility principle is violated here, saving should be handled by a separate class.
		//only single task should be handled by a class, in this case painting should only handle painting and saving should be handled by a separate class.
		
		
		PaintingSaver paintingSaver = new PaintingSaver();
		paintingSaver.save(paint); // now the responsibility of saving is handled by a separate class, maintaining single responsibility principle.
		
		}
}
		
		
class Painting{
	public void paint(Paint paint) {
     		System.out.println("Painting: " + paint.getName() + " with color: " + paint.getColor());
	}
	
	public void save(Paint paint) { // should be in a separate class to maintain single responsibility principle
		System.out.println("Saving user: " + paint.getName() + " " + paint.getColor());
	}
}

class Paint{
	private String name;
	private String color;
	public Paint(String name, String color) {
		this.name = name;
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}


class PaintingSaver{ // now this class is responsible for saving the painting, maintaining single responsibility principle
	public void save(Paint paint) {
		System.out.println("Saving user: " + paint.getName() + " " + paint.getColor());
	}
}

