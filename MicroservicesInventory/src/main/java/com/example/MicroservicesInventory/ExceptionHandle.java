package com.example.MicroservicesInventory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandle 
{
	
	
	@ExceptionHandler(UserDefinedException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody public ExceptionreturnEntity exceptionHandle(HttpServletRequest request, HttpServletResponse response, UserDefinedException e) 
	{
		ExceptionreturnEntity er = new ExceptionreturnEntity();
		er.setPath(request.getRequestURI());
		er.setMessage(e.getMessage());
		return er;
	}
	
	
	

}
