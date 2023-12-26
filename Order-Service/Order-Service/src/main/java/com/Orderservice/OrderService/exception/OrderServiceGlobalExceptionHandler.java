package com.Orderservice.OrderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Orderservice.OrderService.dto.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class OrderServiceGlobalExceptionHandler {

	@ExceptionHandler(OrderServiceException.class)
	public ResponseEntity<?>handleOrderServiceException(OrderServiceException e)throws JsonProcessingException{
		ErrorResponse errorResponse=new ObjectMapper().readValue(e.getMessage(),ErrorResponse.class);
		return ResponseEntity.internalServerError().body(errorResponse);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?>handleAllError(Exception ex){
		ErrorResponse errorResponse=ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorCode("2").errorMessage(ex.getMessage()).build();
			
	    return ResponseEntity.internalServerError().body(errorResponse);
	}
}
