package com.epam.practice.advancedJava;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FunctionalInterfaceExample {
	
	public static void main(String[] args) {
		
		Calculator c = (a,b) -> a+b;
		
		//built -in functional Interfaces
		
		Predicate<String> p = s -> s.length()>5;
		System.out.println("predicate result:: "+ p.test("Ramyaa"));  // boolean test(T t)
		// and(), or(), negate()
		Predicate<String> po = s->s.startsWith("R");
		System.out.println(" predicate and "+ p.and(po));
		
		Function<String,Integer> fu = s -> s.length();
		System.out.println("function result "+ fu.apply("Ramya"));  // R apply(T t);
		//andThen(), compose()
		
		
		Function<String, String> upper = s -> s.toUpperCase();
		Function<String, Integer> length = s -> s.length();
		Function<String, Integer> combined = upper.andThen(length);
		System.out.println("combined :: "+ combined);
		
		Consumer<String> co = s -> System.out.println(s);
		co.accept("lakshmi");  // void accept(T t)
		
		Supplier<Double> su = ()-> Math.random();
		System.out.println("Supplier "+ su.get());  // T get();
		
		
		BiFunction<Integer, Integer, Integer> bi = (a,b) -> a+b;
		System.out.println("bi function :: "+ bi.apply(2,3));
		
		//we have biconsumer, biprediacte as well
		
		IntFunction<Integer> intFunc = (s) ->s/10;
		System.out.println("int function "+ intFunc.apply(123));  // to avoid boxing/unboxing
	}

}

@FunctionalInterface
interface Calculator{
	
	int add(int a, int b);
	//int subtract(int a, int b);
	//void sub(int a , int b);   // if no @FunctionalInterface this method wont give any compilation error. If annotated -- Calculator is not a functional interface
// can have static, private, and default methods
	
	
	 boolean equals(Object obj);  // This is valid since it is overridden from object class -- Every interface implicitly has Object methods.
//hashcode , toString are all part of object class so they can be part of FI
}



