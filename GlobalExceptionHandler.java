package com.egiftcard.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchUserException.class)
	public ResponseEntity<ExceptionResponse> handleNoSuchUserException(NoSuchUserException e)
	{
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorMessage(e.getMessage());
		response.setErrorCode("CONFLICT");
		response.setTimeStamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
		return responseEntity;
		
	}
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidUserIdException(InvalidUserIdException e)
	{
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorMessage(e.getMessage());
		response.setErrorCode("IM_USED");
		response.setTimeStamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.IM_USED);
		return responseEntity;
	}

	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<ExceptionResponse> handleNoDataException(NoDataException e)
	{
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorMessage(e.getMessage());
		response.setErrorCode("GONE");
		response.setTimeStamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.GONE);
		return responseEntity;
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(
	  MethodArgumentNotValidException ex) 
	{
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	 });
	    return new ResponseEntity<Map<String, String>>(errors,HttpStatus.NOT_ACCEPTABLE);
	}
	
	/*@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorMessage(e.getMessage());
		response.setErrorCode("Not Acceptable");
		response.setTimeStamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.NOT_ACCEPTABLE);
		return responseEntity;
	}*/
	

}
