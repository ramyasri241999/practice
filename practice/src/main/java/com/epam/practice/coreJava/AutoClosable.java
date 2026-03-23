package com.epam.practice.coreJava;

import java.io.Closeable;

import java.io.IOException;

/*
 * AutoCloseable is an interface in Java that provides a mechanism for automatic resource management.
 * 	It is used in conjunction with the try-with-resources statement, which ensures that resources are closed automatically after they are no longer needed.
 * 
 */

public class AutoClosable implements Closeable{ 

	@Override
	public void close() throws IOException {
		System.out.println("Closed");
		
	}
	
	public static void main(String[] args) {
		try(AutoClosable ac = new AutoClosable()){
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
