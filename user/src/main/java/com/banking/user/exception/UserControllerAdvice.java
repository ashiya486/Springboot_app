package com.banking.user.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {
@ExceptionHandler(NotfoundException.class)
	public ResponseEntity<Object>notFound(NotfoundException ex,WebRequest request){
		return new ResponseEntity<Object>(new ErrorDetails(ex.getMessage(),LocalDateTime.now()),HttpStatus.NOT_FOUND);
	}
@ExceptionHandler(BadRequestException.class)
public ResponseEntity<Object>badRequest(BadRequestException ex,WebRequest request){
	return new ResponseEntity<Object>(new ErrorDetails(ex.getMessage(),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(RestTemplateException.class)
public ResponseEntity<Object>restTempExcept(RestTemplateException ex,WebRequest request){
	return new ResponseEntity<Object>(new ErrorDetails(ex.getMessage(),LocalDateTime.now()),ex.getStatusCode());
}

@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
		HttpStatus status, WebRequest request) {
	List<ErrorModel> errorMessages = ex.getBindingResult().getFieldErrors().stream()
			.map(
					err ->new ErrorModel(err.getField(),err.getRejectedValue(),err.getDefaultMessage()))
			.distinct()
			.collect(Collectors.toList());
			return new ResponseEntity<Object>(errorMessages,HttpStatus.BAD_REQUEST);
}

}
