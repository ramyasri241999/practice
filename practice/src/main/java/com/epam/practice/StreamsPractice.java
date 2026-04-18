package com.epam.practice;

import java.util.*;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsPractice {
	
	public static void main(String[] args) {
		
		  int[] arr = {3, 1, 4, 1, 5, 9,2,6};
		  IntStream intstream = Arrays.stream(arr);
		 // int sum = Arrays.stream(arr).sum();
		  // int sum = Arrays.stream(arr).reduce(0,(a,b)-> a+b);
		    int sum = Arrays.stream(arr).reduce(0, Integer::sum);
		  System.out.println("Sum "+ sum);
		  // we have (Intstream)filter, map, sum (returns int) , (optionalInt)min , max, (OptionalDouble)avg , distinct, sorted, limit, skip, 
		  // (long )count , findFirst all others    , (Stream<Integer> )boxed()
		  OptionalInt max = Arrays.stream(arr).max();
		  System.out.println("Max "+ max);
		  
		  long count = Arrays.stream(arr).count();
		  System.out.println("count  "+ count);
		  
//		  Predicate<Integer> isEven = ar -> ar%2 ==0;
//		  long evenCount = Arrays.stream(arr).boxed().filter(isEven).count();
		  
		  IntPredicate isEven = ar -> ar%2 ==0;
		  long evenCount = Arrays.stream(arr).filter(isEven).count();
		 System.out.println("isEven "+ evenCount);
		   
		  int [] arrback = Arrays.stream(arr).filter(isEven).toArray();
		  System.out.println("arrback "+ Arrays.toString(arrback));
		  
		  List<Integer> removeDupes = Arrays.stream(arr).distinct().sorted().boxed().toList();
			System.out.println("removeDupes "+ removeDupes.toString());
			List<Integer> reverseorder = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).toList();
			System.out.println("reverseorder "+ reverseorder);
			List<Integer> greaterthan3 = Arrays.stream(arr).boxed().filter(e->e>3).toList();
			System.out.println("greater than 3"  + greaterthan3);
			
			int secondHighest= Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);
			System.out.println("secondhighest "+ secondHighest);
			
			  int evenSum = Arrays.stream(arr).filter(isEven).reduce(0, Integer::sum);
			  System.out.println("evenSum "+ evenSum);
			  
			  Predicate<Integer> allmatch = e-> e == 0;
			 Boolean allzeros = Arrays.stream(arr).boxed().allMatch(allmatch);
			 System.out.println("all zeros "+ allzeros);
			
			Map<Integer,Long> freq = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			
			List<Integer> duplicates = freq.entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).toList();
			
			System.out.println("duplicates "+ duplicates);
			
		List<Integer> primes= 	Arrays.stream(arr).filter(n-> n>1 && IntStream.rangeClosed(2,(int) Math.sqrt(n)).allMatch(i-> n%i != 0)).boxed().toList();
	System.out.println("primes "+ primes);
	
	}
}
