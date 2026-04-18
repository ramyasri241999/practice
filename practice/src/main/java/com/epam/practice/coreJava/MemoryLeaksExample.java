package com.epam.practice.coreJava;

import java.util.HashSet;
import java.util.Set;

public class MemoryLeaksExample {
	
	public static void main(String[] args) {
		Set<Ramya> set = new HashSet<>();
		for (int i = 0; i < 1000000; i++) {
			set.add(new Ramya("Lakshmi"));
		}
		
		System.out.println("Set size: " + set.size()); 
		/*
		 * size is 1000000 - when Ramya class does not override hashCode and equals method, then the default implementation from Object class will be used, which is based on the memory address of the object.
		 * size is 1 - when Ramya class overrides hashCode and equals method, then the set will consider all objects with the same name as equal and will only keep one instance in the set, resulting in a size of 1.
		 */
	}
}


class Ramya{
	
	private String name;
	public  Ramya(String name){
		 this.name = name;
	 }
	
	/*
	 * If we dont override hashCode and equals method, then the default implementation from Object class will be used, which is based on the memory address of the object.
	 * and size of the set will be 1000000 because each object will be considered as unique even if they have the same name.
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Ramya other = (Ramya) obj;
		return name.equals(other.name);
	}
	
	}
