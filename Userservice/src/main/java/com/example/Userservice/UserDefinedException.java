package com.example.Userservice;

public class UserDefinedException extends Exception {
	
	private String test;
	
	public UserDefinedException() {

		
		//Exception super = Exception() (Implicitly created object of the parent class.)
	}

	public UserDefinedException(String message) {
		super(message); // the implicitly formed object of the parent class(predefined type of exception
						// super class Exception() will call the parameterized constructor of its class
						// Exception to assigned the value to the ciopies of instance variable assigned
						// to ir, which it share with the object of the child class which is the user
						// defined type of exception class UserDefinedClass inheriting or extending
	                   // predefined type of exception super class Exception.
	

	}

}
