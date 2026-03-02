package com.epam.practice.customException;


public class NoSuchUserFoundException extends RuntimeException{

	public NoSuchUserFoundException(String message) {
		super(message);
	}
}
