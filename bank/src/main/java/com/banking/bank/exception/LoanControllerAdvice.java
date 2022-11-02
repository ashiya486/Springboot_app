package com.banking.bank.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LoanControllerAdvice extends ResponseEntityExceptionHandler {
@ExceptionHandler(NotfoundException.class)
	public ResponseEntity<Object>notFound(NotfoundException ex,WebRequest request){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
@ExceptionHandler(BadRequestException.class)
public ResponseEntity<Object>badRequest(BadRequestException ex,WebRequest request){
	return new ResponseEntity<Object>(new ErrorDetails(ex.getMessage(),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
}
}
