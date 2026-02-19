package com.epam.practice.coreJava;

public class MyException {
	
	public static void main(String[] args) {
		int a = 5, b=0;
		String s =null;
		try {
			//int c = a/b; // Arthimetic Exception
			System.out.println("string value :: "+s);
		}
		catch(ArithmeticException e) {
			System.out.println("Exception :: "+e.getMessage() +" cause ::"+e.getCause());
		}
	}

}
