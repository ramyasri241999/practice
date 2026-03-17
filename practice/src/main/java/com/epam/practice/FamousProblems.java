package com.epam.practice;

import java.util.Arrays;
public class FamousProblems {

	/*
	 * Activity Selection Problem: Given start and finish times of activities, 
	 * select the maximum number of activities that can be performed by a single person, 
	 * assuming that a person can only work on a single activity at a time.
	 * input: start = [1, 3, 0, 5, 8, 5], finish = [2, 4, 6, 7, 9, 9]
	 * output: 4 (activities at indices 0, 1, 3, and 4 can be selected)
	 */
	public  int activitySelection(int[] start, int[] finish) {

	        int n = start.length;

	        int[][] activities = new int[n][3];  //take a 2D array to store index, start and finish time of activities

	        for(int i = 0; i < n; i++){    
	            activities[i][0] = i;      // index
	            activities[i][1] = start[i];   // start time
	            activities[i][2] = finish[i];  // finish time
	        }

	        Arrays.sort(activities, (a,b) -> a[2] - b[2]); //sort activities based on finish time

	        int count = 1;  //count of activities that can be performed. we can always perform the first activity after sorting based on finish time
	        int lastFinish = activities[0][2];  //lastFinish variable to keep track of the finish time of the last selected activity. we will compare the start time of the next activity with lastFinish to check if it can be selected or not.

	        for(int i = 1; i < n; i++){
	            if(activities[i][1] > lastFinish){ //if the start time of the current activity is greater than the finish time of the last selected activity, then we can select the current activity
	                count++; //update the count of selected activities
	                lastFinish = activities[i][2]; //update the lastFinish variable to the finish time of the current activity
	            }
	        }

	        return count; //return the count of selected activities
	    }
	}


