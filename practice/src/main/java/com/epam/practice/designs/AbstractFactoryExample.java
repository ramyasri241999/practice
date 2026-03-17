package com.epam.practice.designs;

public class AbstractFactoryExample {
	
	//Abstract Factory creates related objects together while ensuring consistency within a product family.
	public static void main(String[] args) {
		AbstractFactory flowerFactory = FactoryProducer.getFactory(type.FLOWER.name());
		flower rose = flowerFactory.getFlower(type.ROSE.name());
		rose.bloom();
		AbstractFactory colorFactory = FactoryProducer.getFactory(type.COLOR.name());
		Color red = colorFactory.getColor(type.RED.name());
		red.fill();
		
	}
}

enum type{
	FLOWER, COLOR,ROSE,LILY,RED,GREEN
}

interface flower{
	void bloom();
}

class Rose implements flower{
	@Override
	public void bloom() {
		System.out.println("Rose is blooming");
	}
}
class Lily implements flower{
	@Override
	public void bloom() {
		System.out.println("Lily is blooming");
	}
}

interface Color{
	void fill();
}

class Red implements Color{
	@Override
	public void fill() {
		System.out.println("Red color filled");
	}
}
class Green implements Color{
	@Override
	public void fill() {
		System.out.println("Green color filled");
	}
}

interface AbstractFactory{
	flower getFlower(String flowerType);
	Color getColor(String colorType);
}

class FlowerFactory implements AbstractFactory{
	@Override
	public flower getFlower(String flowerType) {
		if(flowerType == null) return null;
		switch(flowerType.toUpperCase()) {
			case "ROSE": return new Rose();
			case "LILY": return new Lily();
			default: return null;
		}
	}
	
	@Override
	public Color getColor(String colorType) {
		return null; // not implemented in this factory
	}
}

class ColorFactory implements AbstractFactory{
	@Override
	public flower getFlower(String flowerType) {
		return null; // not implemented in this factory
	}
	
	@Override
	public Color getColor(String colorType) {
		if(colorType == null) return null;
		switch(colorType.toUpperCase()) {
			case "RED": return new Red();
			case "GREEN": return new Green();
			default: return null;
		}
	}
}

class FactoryProducer{
	public static AbstractFactory getFactory(String choice) {
		if(choice == null) return null;
		switch(choice.toUpperCase()) {
			case "FLOWER": return new FlowerFactory();
			case "COLOR": return new ColorFactory();
			default: return null;
		}
	}
}

