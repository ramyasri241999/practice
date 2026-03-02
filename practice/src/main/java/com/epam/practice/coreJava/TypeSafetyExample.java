package com.epam.practice.coreJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeSafetyExample {
	
	public static void main(String[] args) {
		//withoutGenerics();
		withGenerics();
		Box<List<String>> box = new Box<>();
		List<String> list = Arrays.asList("Hi","hello","welcome");
	
		box.setValue(list);
		System.out.println("box :: "+box);
		
		Box<Integer> box2 = new Box<>();
		box2.setValue(10);
		System.out.println("box Integers:: "+box2);
		
		print(box2);
		
		printNumbers(0);
		printNumbers(0l);
		
		List<? extends Number> l = new ArrayList<>();   //? -- wildCard , T is Bounded
		//l.add(10);   //compile error
		
		List<? super Integer> listNumbers = new ArrayList<>(); // any parent of Integer - Integer, Number,Object
		listNumbers.add(0);
		
		Pair<Integer, String> pair = new Pair<>();
		pair.setKey("Ramya");
		pair.setValue(1);
		
		System.out.println("pair "+pair);
		
		StringContainer strContainer = new StringContainer();
		strContainer.fill("hello container String");
		
		
		
	}


	//No type safety

	//Casting required

	//Runtime exception

public static void withoutGenerics() {
	List list = new ArrayList();
	list.add(10);
	list.add("HI");
	System.out.println("list without generics ::" + list);
	for(int i=0;i<list.size();i++) {
		String s = (String)list.get(i);         //ClassCastException
		System.out.println("list elements :: "+s);
	}
}


public static void withGenerics() {
	List<String> list = new ArrayList<>();
	//list.add(10); //The method add(int, String) in the type List<String> is not applicable for the arguments (int)
	list.add("Ramya");
	list.add("Sri");
	list.add("Lakshmi");
	System.out.println("List with Generics :: "+list);
}


public static <T> void print(T value) {
	System.out.println("Print value in generic method "+ value);
}

public static <T extends Number> void printNumbers(T value) {     //upper bound
	System.out.println("print value in bounded type :: "+ value);
}



}

class Box<T>{  //Generic class
	
	private T value;
	
	public T getValue() {
		return this.value;
		
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Box [value=" + value + "]";
	}
	
	
}


class Pair<K,V>{
	
	private V key;
	private K value;
	public V getKey() {
		return key;
	}
	public void setKey(V key) {
		this.key = key;
	}
	public K getValue() {
		return value;
	}
	public void setValue(K value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Pair [key=" + key + ", value=" + value + "]";
	}
	
	
}

interface Container <T>{
	
	void fill(T t);
	
	T fix();
}

class StringContainer implements Container<String>{

	@Override
	public void fill(String t) {
		System.out.println("Inside the String Container fill method "+t);
	}

	@Override
	public String fix() {
		
		return "String Container fix method";
	}
	
}



