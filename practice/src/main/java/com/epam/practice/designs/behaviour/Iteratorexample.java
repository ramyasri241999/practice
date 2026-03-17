package com.epam.practice.designs.behaviour;

import java.util.Iterator;
import java.util.List;

/*
 * Iterator design Pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
 * 
 */
public class Iteratorexample {
	public static void main(String[] args) {
		NameRepository namesRepository = new NameRepository();
		IteratorCustom iter = namesRepository.getIterator();
		while( iter.hasNext()) {
			String name = (String)iter.next();
			System.out.println("Name : " + name);
		}
		
		
		List<String> list = List.of("Ramya","John","Alex");
		//java.util.Iterator is a built in interface in java which provides a way to access the elements of a collection sequentially without exposing its underlying representation. 
		//It has two methods hasNext() and next().
		Iterator<String> it = list.iterator(); 

		while(it.hasNext()) {
			it.next(); // it returns the next element in the iteration and moves the cursor to the next element.
			it.remove(); // it removes the last element returned by the iterator. This method can be called only once per call to next(). It throws IllegalStateException if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
		    System.out.println(it.next());
		}
		
		for(String name : list) {
			//wrong//
			list.remove("John"); // ConcurrentModificationException because we are trying to modify the collection while iterating over it using for each loop, because for each loop internally uses iterator to iterate over the collection, and iterator does not allow modification of the collection while iterating over it.
			System.out.println(name);
		}// for each loop internally uses iterator to iterate over the collection.
		//stream api also uses iterator to iterate over the collection.
		 list.stream().forEach(System.out::println);
		
	}
}

/*
 * Iterator interface has hasNext and next methods. 
 * hasNext method returns true if there are more elements to iterate, otherwise false. 
 * next method returns the next element in the iteration.
 */

interface IteratorCustom{ 
	boolean hasNext();
	Object next();
}

interface Container{
	IteratorCustom getIterator();
}

class NameRepository implements Container{
	public String names[] = {"John","Jane","Jack","Jill"};
	
	@Override
	public IteratorCustom getIterator() {
		return new NameIterator();
	}
	
	private class NameIterator implements IteratorCustom{
		int index;
		
		@Override
		public boolean hasNext() {
			if(index < names.length) {
				return true;
			}
			return false;
		}
		
		@Override
		public Object next() {
			if(this.hasNext()) {
				return names[index++];
			}
			return null;
		}
		
	}
}

/*
 * common mistakes:
 * 1. Not checking for null before calling next() method of the iterator, which can lead to NullPointerException if the collection is empty.
 * 2. Not checking for hasNext() before calling next() method of the iterator, which can lead to NoSuchElementException if there are no more elements to iterate.
 * 3. Modifying the collection while iterating over it using for each loop or stream api, which can lead to ConcurrentModificationException because for each loop and stream api internally uses iterator to iterate over the collection, and iterator does not allow modification of the collection while iterating over it.
 * 4.set(i) - using set method of the list while iterating over it using for each loop or stream api, 
 * 		which can lead to ConcurrentModificationException because for each loop and stream api internally uses iterator to iterate over the collection, 
 * 		and iterator does not allow modification of the collection while iterating over it.
 */

