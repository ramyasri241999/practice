package com.epam.practice.java21.record.pattern;

sealed interface Shape permits Rectangle, Circle, Square {}

record Rectangle(double width, double height) implements Shape {}
record Circle(double radius) implements Shape {}
record Square(double side) implements Shape {}

record NamedShape(String name, Shape shape) {}

public class RecordPatternsDemo {
    public static void main() {
        System.out.println("-- Record Patterns (JEP 440) --");

        Shape s1 = new Rectangle(3, 4);
        Shape s2 = new Circle(2);
        Shape s3 = new Square(5);

//        printArea(s1);
//        printArea(s2);
//        printArea(s3);
//
//
//        NamedShape named = new NamedShape("MyRect", new Rectangle(2, 10));
//        describeNamedShape(named);
//        NamedShape named2 = new NamedShape("MyRect", new Circle(2.5));
//        describeNamedShape(named2);
    }

//    private static void printArea(Shape shape) {
//        if (shape instanceof Rectangle(double w, double h)) {
//            System.out.println("Rectangle area = " + (w * h));
//        } else if (shape instanceof Circle(double r)) {
//            System.out.println("Circle area = " + (Math.PI * r * r));
//        } else if (shape instanceof Square(double side)) {
//            System.out.println("Square area = " + (side * side));
//        }
//    }

//    private static void describeNamedShape(Object obj) {
//
//        switch (obj) {
//            case NamedShape(String name, Rectangle(double w, double h)) ->
//                    System.out.println("Named rectangle '" + name + "' " + w + "x" + h);
//            case NamedShape(String name, Circle(double r)) ->
//                    System.out.println("Named circle '" + name + "' r=" + r);
//            case NamedShape(String name, Square(double side)) ->
//                    System.out.println("Named square '" + name + "' side=" + side);
//            default -> System.out.println("Unknown object: " + obj);
//        }
//    }
}
