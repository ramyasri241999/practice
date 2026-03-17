package com.epam.practice.dsa;

import java.util.HashSet;
import java.util.Set;

public class TwoPointerExample { //instead of using nested loops, we use two pointer approach
	//This reduces the time complexity from O(n^2) to O(n)
	/*
	 * sorted arrays
		pairs
		palindromes
		removing duplicates
		merging arrays
		partitioning
	 */
	
	/*
	 * three types of two pointer approach : 1. Fast and slow pointer 2. Left and right pointer 3. Sliding window
	 */

	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
		int target = 7;
		System.out.println(hasPairWithSum(arr, target)); // true . Opposite two pointers approach
		
		String str = "racecar";
		System.out.println(isPalindrome(str)); // true . Opposite two pointers approach
		
		int[] arr2 = {1, 1, 2, 2, 3, 4, 4};
		removeDuplicates(arr2); // [1, 2, 3, 4] // Fast and slow pointer approach
		
		int[] arr3 = {1, 2, 3, 4, 5};
		int k = 3;
		maximumSubArraySum(arr3, k); // 12 // Sliding window approach
	}
	
	// only for sorted arrays. if not, we go with complimentary approach using hashset.
	/*
	 * Input: arr = [1, 2, 3, 4, 5], target = 7
	 * Output: true
	 * Explanation: The pair (2, 5) sums up to 7.
	 */
	public static boolean hasPairWithSum(int[] arr, int target) {
		int left = 0; // left pointer starts at the beginning of the array
		int right = arr.length - 1; // right pointer starts at the end of the array
		
		while(left < right) { // while the left pointer is less than the right pointer
			int currentSum = arr[left] + arr[right]; // calculate the sum of the elements at the left and right pointers
			
			if(currentSum == target) { // if the current sum is equal to the target sum, we found a pair
				return true;
			} else if(currentSum < target) { // if the current sum is less than the target sum, we need to increase the sum by moving the left pointer to the right
				left++;
			} else { // if the current sum is greater than the target sum, we need to decrease the sum by moving the right pointer to the left
				right--;
			}
		}
		
		return false; // if we exit the loop without finding a pair, return false
	}
	/*
	 * Input: str = "racecar"
	 * Output: true
	 * Explanation: The string "racecar" is a palindrome because it reads the same backward as forward.
	 */
	public static boolean isPalindrome(String str) {
		int left = 0; // left pointer starts at the beginning of the string
		int right = str.length() - 1; // right pointer starts at the end of the string
		
		while(left < right) { // while the left pointer is less than the right pointer
			if(str.charAt(left) != str.charAt(right)) { // if the characters at the left and right pointers are not equal, it's not a palindrome
				return false;
			}
			left++; // move the left pointer to the right
			right--; // move the right pointer to the left
		}
		
		return true; // if we exit the loop without finding any unequal characters, it's a palindrome
	}
	
	
	// only for sorted arrays. if not, we go with complimentary approach using hashset.
	/*
	 * Input: arr = [1, 1, 2, 2, 3, 4, 4]
	 * Output: [1, 2, 3, 4]
	 * Explanation: The unique elements in the array are 1, 2, 3, and 4.
	 */
	public static void removeDuplicates(int[] arr) { // This example is for slow and fast pointer approach
		int left = 0; // left pointer starts at the beginning of the array
		
		for(int right = 1; right < arr.length; right++) { // right pointer starts at the second element of the array and moves to the end
			if(arr[left] != arr[right]) { // if the current element is not equal to the last unique element, we found a new unique element
				left++; // move the left pointer to the right
				arr[left] = arr[right]; // update the last unique element to the current element
			}
		}
		
		// after the loop, all unique elements are in the first left + 1 positions of the array
		for(int i = 0; i <= left; i++) {
			System.out.print(arr[i] + " "); // print the unique elements
		}
	}
	
	/*
	 * Input: arr = [1, 2, 3, 4, 5], k = 3
	 * Output: 12  
	 * Explanation: The subarray with the maximum sum is [3, 4, 5], which has a sum of 12.
	 */
	public static void maximumSubArraySum(int[] arr, int k) { // This example is for sliding window approach with fixed window size
		//arr is input array and k is the size of the window
		int currentSum = 0; // variable to keep track of the current sum of the window
		int maxSum = Integer.MIN_VALUE; // variable to keep track of the maximum sum of the window
		
		for(int right = 0; right < arr.length; right++) { // right pointer moves to the end of the array
			currentSum += arr[right]; // add the current element to the current sum
			
			if(right >= k - 1) { // if the window size is equal to k
				maxSum = Math.max(maxSum, currentSum); // update the maximum sum if the current sum is greater
				currentSum -= arr[right - (k - 1)]; // remove the leftmost element from the current sum to slide the window
			}
		}
	}
	
	
	//variable size sliding window approach
	/*
	 * Input: arr = [1, 2, 3, 4, 5], target = 9
	 * Output: [2, 3, 4]
	 * Explanation: The subarray with the sum equal to the target is [2, 3, 4].
	 */
	public static void subArrayWithTargetSum(int[] arr, int target) {
		int currentSum = 0; // variable to keep track of the current sum of the window
		int left = 0; // left pointer starts at the beginning of the array
		
		for(int right = 0; right < arr.length; right++) { // right pointer moves to the end of the array
			currentSum += arr[right]; // add the current element to the current sum
			
			while(currentSum > target) { // if the current sum is greater than the target, we need to shrink the window from the left
				currentSum -= arr[left]; // remove the leftmost element from the current sum
				left++; // move the left pointer to the right
			}
			
			if(currentSum == target) { // if the current sum is equal to the target, we found a subarray
				for(int i = left; i <= right; i++) {
					System.out.print(arr[i] + " "); // print the subarray
				}
				System.out.println(); // print a new line after printing the subarray
			}
		}
	}
	
	
	//Longest substring without repeating characters
	/*
	 * Input: str = "abcabcbb"
	 * Output: 3
	 * Explanation: The longest substring without repeating characters is "abc", which has a length of 3.
	 */
	public static void longestSubstringWithoutRepeatingCharacters(String str) {
		Set<Character> set = new HashSet<>(); // set to keep track of unique characters in the current window
		int left = 0; // left pointer starts at the beginning of the string
		int maxLength = 0; // variable to keep track of the maximum length of the substring
		
		for(int right = 0; right < str.length(); right++) { // right pointer moves to the end of the string
			while(set.contains(str.charAt(right))) { // if the current character is already in the set, we need to shrink the window from the left
				set.remove(str.charAt(left)); // remove the leftmost character from the set
				left++; // move the left pointer to the right
			}
			
			set.add(str.charAt(right)); // add the current character to the set
			maxLength = Math.max(maxLength, right - left + 1); // update the maximum length if the current window is larger
		}
		
		System.out.println(maxLength); // print the maximum length of the substring without repeating characters
	}
}
