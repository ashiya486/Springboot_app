package com.banking.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class RestTemplateException extends HttpClientErrorException{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public RestTemplateException(HttpStatus status,String message){
	super(status,message);
	
}
}
