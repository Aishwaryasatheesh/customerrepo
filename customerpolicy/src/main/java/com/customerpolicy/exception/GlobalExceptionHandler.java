package com.customerpolicy.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.customerpolicy.bean.ApiResponse;



@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ApiResponse> hadlebookNotFoundException(CustomerNotFoundException ex)
	{
		ApiResponse response=new ApiResponse(ex.getMessage(), 404);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ApiResponse> hadlePurchaseNotFoundException(PolicyNotFoundException ex)
	{
		ApiResponse response=new ApiResponse(ex.getMessage(), 404);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
}
