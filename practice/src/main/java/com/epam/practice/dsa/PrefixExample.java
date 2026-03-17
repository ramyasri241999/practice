package com.epam.practice.dsa;

//sum of subarray from l to R
public class PrefixExample {
	public static void main(String[] args){
		prefixSum();  //to calculate sum of subarray from l to r in O(1) time after O(n) preprocessing time
	}
	
	public static int prefixSum() {
		int arr[] = {1,2,3,4,5};
		int prefixSum[] = new int[arr.length];
		prefixSum[0] = arr[0];
		for(int i=1; i<arr.length; i++) {
			prefixSum[i] = prefixSum[i-1] + arr[i]; //1, 3, 6, 10, 15
		}
		int l = 1, r = 3;
		int sum;
		if(l == 0) {
			sum = prefixSum[r];
		} else {
			sum = prefixSum[r] - prefixSum[l-1];
		}
		System.out.println("Sum of subarray from " + l + " to " + r + " is: " + sum);
		return sum;
		
	}
	
	
	
	
}

/*
 * Advantages

✅ Avoids repeated summation
✅ Efficient for multiple queries
✅ Converts O(N) per query → O(1)
✅ Very useful in range queries
 * 
 */

/*
 * Variations of Prefix Sum

1️ Subarray sum equals K

Leetcode 560

Uses:

Prefix Sum + HashMap

2️ Equilibrium index

Left sum = Right sum.

3️ 2D Prefix Sum (Matrix problems)

Used in:

Leetcode 304 Range Sum Query 2D

4️ Difference Array

Used in range updates.
 */


/*
 * When NOT to Use Prefix Sum

❌ When array changes frequently
❌ When updates are frequent

Use Segment Tree / Fenwick Tree instead.
 */

