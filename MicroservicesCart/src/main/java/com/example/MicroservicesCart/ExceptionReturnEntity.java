package com.example.MicroservicesCart;

public class ExceptionReturnEntity 
{
	private String message;
	private String path;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
