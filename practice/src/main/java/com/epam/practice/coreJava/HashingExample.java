package com.epam.practice.coreJava;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/*
 * Converts a string into a hash code using the built-in hashCode() method.
 * The hash code is an integer representation of the string, which can be used for various purposes such as storing in hash-based data structures (e.g., HashMap) or for quick comparisons.
 * convert a key into index for faster retrival of values in a hash table. 
 * The hash code is used to determine the bucket where the key-value pair should be stored or retrieved from.
 * Capacity:16 default. when created it will create an array of 16 buckets to store the key-value pairs.
 * Load factor:0.75. it is a perfect balance between memory usage and performance.
 * threshold: capacity * load factor = 16 * 0.75 = 12 - when we insert 13th key-value pair, the hash table will be resized to accommodate more entries.
 * when the hash table is resized, the capacity is doubled (32 buckets) and all existing key-value pairs are rehashed to fit into the new bucket array.
 */

public class HashingExample {
	public static void main(String[] args) {
		/*
		 * Firstly how the hash code is generated for a string in Java?
		 * ex: the string is Ramya - the hash code is calculated as follows:
		 * hash code = (R * 31^4) + (a * 31^3) + (m * 31^2) + (y * 31^1) + (a * 31^0)
		 * where R, a, m, y are the ASCII values of the characters in the string. The hash code is an integer value that represents the string.
		 * actual formula for calculating the hash code of a string in Java is:
		 * hash code = s[0] * 31^(n-1) + s[1] * 31^(n-2) + ... + s[n-1] * 31^0 where n is the length of the string and s[i] is the ASCII value of the character at index i in the string.
		 * so same key same hash code, but different keys can also have same hash code, this is called hash collision.
		 */
		
		Map<String, Integer> map = new HashMap<>(); // default capacity is 16 and load factor is 0.75
		map.put("ramya", 1); 
		/*hashcode of "ramya" is 108279718. next we do furthur processing  hash = h ^ (h >>> 16) where h is the hash code of the key,
		this is done to spread the hash codes more uniformly across the buckets and reduce the chances of collisions.
		This is faster than modulo operation and helps in improving the performance of the hash table. 
		This will consider the last 4 bits of the hashcode
		*/
		System.out.println(map.hashCode());	// hash code of the map, it is calculated based on the hash codes of the keys and values in the map.
		
		map.put("sri", 2);
		System.out.println(map.hashCode());
		
		// there is no public method to get the capcity or load factor of the HashMap, but we can use reflection to get the capacity and load factor of the HashMap.
		// refelection is restricted in java 9 and above, so we need to add the following VM argument to run this code: --add-opens java.base/java.util=ALL-UNNAMED
//		try {
//				Field tableField = HashMap.class.getDeclaredField("table"); // get the field named "table" which is the array of buckets in the HashMap
//				tableField.setAccessible(true); // make the field accessible
//				Object[] table = (Object[]) tableField.get(map); // get the value of the table field
//				System.out.println("Capacity: " + table.length); // print the capacity of the HashMap
//		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Map<String, Integer> map1 = new HashMap<>(16);

		for(int i = 1; i <= 20; i++){
		    map1.put("key" + i, i);
		    System.out.println("Size: " + map1.size());
		}
		
/*
 * When we insert the 13th key-value pair, the size of the map becomes 13, which exceeds the threshold of 12 (capacity * load factor). 
 * At this point, the HashMap will resize itself by doubling its capacity to 32 and rehashing all existing key-value pairs to fit into the new bucket array. 
 * This process is known as rehashing and it helps in maintaining the performance of the HashMap as it grows in size.
 */
		
/*
 * Collision handling in HashMap: When two different keys have the same hash code, they will be stored in the same bucket.
 * ex: if we have two keys "Aa" and "BB", they will have the same hash code of 2112. So both keys will be stored in the same bucket.
 * we store the key-value pairs in a linked list or a balanced tree (since Java 8) in the bucket to handle collisions.
 * if the number of key-value pairs in a bucket exceeds a certain threshold (default is 8), the linked list will be converted into a balanced tree to improve the performance of the HashMap.
 * but only when the capacity of the HashMap is greater than 64, otherwise it will remain as a linked list. This is done to avoid the overhead of creating a balanced tree for small buckets.
 */
		
	}
}
