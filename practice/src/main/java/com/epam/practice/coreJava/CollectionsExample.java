package com.epam.practice.coreJava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class CollectionsExample { 
	
	public static void main(String[] args) {
		// List example -- open the class and see the implementation for better understanding.
		List<String> list = new ArrayList<>();  // good for random access since implements RandomAccess. It allows null values, Insertion order, not thread safe, faster than Vector.
		List<String> linkedlist = new LinkedList<>(); // good for insertion and deletion since it uses doubly linked list, allows null values, maintains insertion order, not thread safe, slower than ArrayList.
		List<String> vector = new Vector<>();  //methods are synchronized, thread safe, slower than ArrayList
		List<String> stack = new Stack<>();  //LIFO data structure, extends Vector, synchronized, thread safe
		list.add("Hello");
		list.add("World");
		System.out.println("List: " + list);
		list = Collections.synchronizedList(list); // returns a synchronized (thread-safe) list backed by the specified list. In order to guarantee serial access, it is critical that all access to the backing list is accomplished through the returned list.
		
		// Set example
		Set<String> set = new HashSet<>(); // does not maintain any order, allows null values, not thread safe
		Set<String> treeSet = new TreeSet<>(); // maintains natural(ascending) order, does not allow null values, not thread safe.Implements Navigableset interface, which provides methods for navigation which inturn extends the sortedSet
		Set<String> linkedHashSet = new LinkedHashSet<>(); // maintains insertion order, allows null values, not thread safe
		//So
		set.add("Hello");
		set.add("World");
		set.add("Hello"); // duplicate element, will not be added
		System.out.println("Set: " + set);
		
		Queue<String> queue = new LinkedList<>(); // FIFO data structure, allows null values, not thread safe
		Queue<String> priorityQueue = new PriorityQueue<>(); // elements are ordered according to their natural ordering or by a Comparator provided at queue construction time, does not allow null values, not thread safe
		Queue<String> priorityQueueWithComparator = new PriorityQueue<>((a,b) -> b.compareTo(a)); // elements are ordered according to the provided comparator, does not allow null values, not thread safe
		Queue<String> arrayDeque = new ArrayDeque<>(); // resizable array implementation of the Deque interface, allows null values, not thread safe
		
		// Map example
		Map<String, Integer> map = new HashMap<>(); // does not maintain any order, allows null keys and values, not thread safe
		Map<String, Integer> linkedHashMap = new LinkedHashMap<>(); // maintains insertion order, allows null keys and values, not thread safe
		Map<String, Integer> treeMap = new TreeMap<>(); // maintains natural(ascending) order of keys, does not allow null keys but allows null values, not thread safe
		Map<String, Integer> hashtable = new Hashtable<>(); // synchronized, thread safe, does not allow null keys and values, slower than HashMap
		Map<String,Integer> identityHashMap = new IdentityHashMap<>(); // uses reference equality instead of object equality when comparing keys, allows null keys and values, not thread safe
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		System.out.println("Map: " + map);
		
		identityHashMap.put(new String("One"), 1);
		identityHashMap.put(new String("One"), 2); // this will be added as a new entry since it uses reference equality
		identityHashMap.put("One", 3); // this will be added as a new entry since it uses reference equality
		identityHashMap.put("One", 4); // this will update the value of the existing entry since it uses reference equality
		System.out.println("IdentityHashMap: " + identityHashMap);


		Iterator it = list.iterator(); // returns an iterator over the elements in this list in proper sequence.
		System.out.println(it.next()); // returns the next element in the iteration. Can remove the element but enumeration does not have this method.
		
	}

}
 