package com.studentapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvice {

	@ExceptionHandler
	public ResponseEntity<Error>handleValidationErrors(MethodArgumentNotValidException me){
		Error error = new Error();
		error.setErrorCount(me.getBindingResult().getAllErrors().size());
		for(ObjectError e : me.getBindingResult().getAllErrors()){
			error.getMessages().add(e.getDefaultMessage());
		}
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<Error>handleNotFoundException(NotFoundException ne){
		Error error = new Error();
		error.setErrorCount(1);
		error.getMessages().add(ne.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<Error>handleException(Exception e){
		Error error = new Error();
		error.setErrorCount(1);
		error.getMessages().add(e.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
