package com.epam.practice.dsa;

import java.util.HashMap;

/*
 * Hashing is one of the most used patterns in coding interviews. Many O(N²) problems become O(N) using hashing.
 * how to find::
 * "find if element exists"

"check duplicates"

"find pair with sum"

"count frequency"
 */
public class HashingExample {
	public static void main(String[] args) {
		subarraySumEqualsK();
	}
	
	public static void subarraySumEqualsK() {
		int arr[] = {1, 1, 1};
		int k = 2;
		int count = 0;
		for(int i=0; i<arr.length; i++) {
			int sum = 0;
			for(int j=i; j<arr.length; j++) {
				sum += arr[j];
				if(sum == k) {
					count++;
				}
			}
		}
		System.out.println("Count of subarrays with sum " + k + " is: " + count); // o(n^2). not a good solution. we can do better using hashing
		
	}
	
	public static void subarraySumEqualsKHashing() {
		int arr[] = {1, 1, 1};
		int k = 2;
		int count = 0;
		int sum = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1); // to handle the case when sum itself is equal to k
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
			if(map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		System.out.println("Count of subarrays with sum " + k + " is: " + count); // o(n). much better solution using hashing
	}
	
	public static void maximumFrequency() {
		int arr[] = {1, 2, 2, 3, 3, 3};
		HashMap<Integer, Integer> map = new HashMap<>();
		int maxFreq = 0;
		int maxFreqElement = arr[0];
		for(int i=0; i<arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			if(map.get(arr[i]) > maxFreq) {
				maxFreq = map.get(arr[i]);
				maxFreqElement = arr[i];
			}
		}
		System.out.println("Element with maximum frequency is: " + maxFreqElement + " with frequency: " + maxFreq);
	}
}

/*
 * dvantages of Hashing

✅ Fast lookup
✅ Reduces nested loops
✅ Turns many O(N²) problems into O(N)
✅ Works well with arrays and strings
*/
