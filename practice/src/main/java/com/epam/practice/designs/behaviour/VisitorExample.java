package com.epam.practice.designs.behaviour;

/*
 * Visitor Pattern lets you add new operations to existing object structures without modifying the classes of those objects.
 */
public class VisitorExample {
	public static void main(String[] args) {
		Circle circle = new Circle(5);
		Rectangle rectangle = new Rectangle(4, 6);

		AreaVisitor areaVisitor = new AreaVisitor();
		DrawVisitor drawVisitor = new DrawVisitor();

		circle.accept(areaVisitor); // Calculate and print area of the circle
		rectangle.accept(areaVisitor); // Calculate and print area of the rectangle

		circle.accept(drawVisitor); // Draw the circle
		rectangle.accept(drawVisitor); // Draw the rectangle
	}
}


interface ShapeVisitor {
	    void visit(Circle circle);
	    void visit(Rectangle rectangle);
}

interface Shape{
	void accept(ShapeVisitor visitor);
}

class Circle implements Shape{

double radius;
	public Circle(double radius) {
		this.radius = radius;
	}
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}
	
}

class Rectangle implements Shape{

	int width;
	int height;
	Rectangle(int width, int height){
		this.width=width;
		this.height=height;
	}
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}
	
}

class AreaVisitor implements ShapeVisitor{
	 public void visit(Circle circle) {
	        double area = Math.PI * circle.radius * circle.radius;
	        System.out.println("Circle area: " + area);
	    }

	    public void visit(Rectangle rect) {
	        int area = rect.width * rect.height;
	        System.out.println("Rectangle area: " + area);
	    }
}
class DrawVisitor implements ShapeVisitor {

    public void visit(Circle circle) {
        System.out.println("Drawing Circle");
    }

    public void visit(Rectangle rect) {
        System.out.println("Drawing Rectangle");
    }
}

/*
 * Best Practices

✔ Use when object structure is stable
✔ Use when operations change frequently
✔ Keep visitor logic small
 */

/*
 * Common Mistakes
Breaking encapsulation

Visitors sometimes access too many internal fields.

Using visitor when strategy is enough

Visitor should only be used when multiple element types exist.
 */

/*
 * Pitfalls / Attacks
Hard to add new element types

If you add:

Triangle

You must modify all visitors.

Bad for frequently changing structures.
 */
