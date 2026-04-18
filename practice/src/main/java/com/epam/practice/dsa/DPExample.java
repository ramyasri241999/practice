package com.epam.practice.dsa;
/*
 * Dynamic programming is a method for solving complex problems by breaking them down into simpler subproblems, 
 * storing the results of those subproblems to avoid redundant work. 
 * It is often used to optimize recursive algorithms that have overlapping subproblems, 
 * such as the Fibonacci sequence, knapsack problem, and longest common subsequence. 
 * Dynamic programming can be implemented using either a top-down approach (memoization) 
 * or a bottom-up approach (tabulation). 
 * The key idea is to store the results of previously computed subproblems in a table or array, 
 * allowing for efficient retrieval and reducing the overall time complexity of the algorithm.
 * 
 * Ways to identify if a problem can be solved using dynamic programming:
 * 1.Problem asks for count of ways. ex: how many ways, total combinations, total subsets, total paths etc. ex: count of ways to climb n stairs, count of subsets with sum k, count of paths in a grid etc.
 * 2.same subproblems repeat so ways(n) = ways(n-1) + ways(n-2) - this is the case of Fibonacci sequence, where the same subproblems are solved multiple times.
 * 3.Optimal substructure - the optimal solution to the problem can be constructed from the optimal solutions of its subproblems. ex: knapsack problem, longest common subsequence etc.
 * 
 * another way to identify is if we have a choice to make at each step and the problem can be broken down into smaller subproblems that can be solved independently.
 */
public class DPExample {
	public static void main(String[] args) {
		DPExample d = new DPExample();
		int climbWays = d.climb(10);
		System.out.println("ways to climb "+climbWays);
	}
	
	/*
	 * Input n = 5
	 * Output: 8
	 * Explanation: There are 8 ways to climb a staircase with 5 steps:
	 * fib sequence - 1:1 2:2 3:3 4:5 5:8 6:13 7:21 8:34 9:55 10:89
	 */
	
	/*
	 * This is tabulation approach. we can also solve this using memoization approach by storing the results of previously computed subproblems in a hashmap or array and retrieving them when needed.
	 */
	public int climb(int n) { 
		if(n<=2) return n;
		int[] dp = new int[n+1];
		dp[1] =1;
		dp[2]=2;
		for(int i=3;i<=n;i++) {
			dp[i] = dp[i-1]+dp[i-2];
			
		}
		return dp[n];
	}
}
