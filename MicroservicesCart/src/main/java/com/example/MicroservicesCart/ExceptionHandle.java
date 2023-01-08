package com.example.MicroservicesCart;

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
    @ResponseBody public ExceptionReturnEntity handleException(UserDefinedException u, HttpServletRequest request, HttpServletResponse response) 
    {
    	ExceptionReturnEntity e = new ExceptionReturnEntity();
    	e.setMessage(u.getMessage());
    	e.setPath(request.getRequestURI());
    	return e;
    }
	

}
