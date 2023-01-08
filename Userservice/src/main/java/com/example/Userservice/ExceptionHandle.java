package com.example.Userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


//prefined class HTTPStatus is used to set the return status,//predefined cladd HTtp status accesses its statis data mamber not_Found.
@ControllerAdvice
public class ExceptionHandle 
{

	
	@ExceptionHandler(UserDefinedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionReturnEntity handleException(UserDefinedException u, HttpServletRequest request, HttpServletResponse response)
	{
		ExceptionReturnEntity entity = new ExceptionReturnEntity();
		entity.setMessage(u.getMessage());
		entity.setPath(request.getRequestURL().toString());
		return entity;
		
	}
	
	
}
