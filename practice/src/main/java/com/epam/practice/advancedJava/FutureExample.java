package com.epam.practice.advancedJava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;

public class FutureExample {
	//private static final Logger logger = Logger.getLogger(FutureExample.class.getName());
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		    try {
		        Thread.sleep(2000); // Simulate a long-running task
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    return "Hello, Future!";
		});
		//String result = future.get(); // This will block until the result is avail//future.thenAccept(result -> System.out.println("Result: " + result));
		System.out.println("Result: " + future.join());
	
	CompletableFuture<Integer> futureNext = future.thenApply(result -> {
	    System.out.println("Processing result: " + result);
	    return result.length();
	
	});
	System.out.println("Length of the result: " + futureNext.join());
	
	
	
	CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
	CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

	CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 + " " + result2);

	System.out.println(combinedFuture.join());
	
	
	CompletableFuture<String> futureException = CompletableFuture.supplyAsync(() -> {
	    if (Math.random() > 0.5) {
	        throw new RuntimeException("Something went wrong!");
	    }
	    return "Success!";
	}).exceptionally(ex -> {
	    System.out.println("Caught exception: " + ex.getMessage());
	    return "Default Value";
	});
	
	
	CompletableFuture<Void> allFutures = CompletableFuture.allOf(
		    CompletableFuture.runAsync(() -> System.out.println("Task 1")),
		    CompletableFuture.runAsync(() -> System.out.println("Task 2"))
		);

		allFutures.join(); 


}
}

