package com.epam.practice.coreJava;

import java.io.Closeable;
import java.io.IOException;

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
