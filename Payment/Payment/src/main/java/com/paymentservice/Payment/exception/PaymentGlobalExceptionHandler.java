package com.paymentservice.Payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paymentservice.Payment.dto.ErrorResponse;

@RestControllerAdvice
public class PaymentGlobalExceptionHandler {

	@ExceptionHandler(PaymentError.class)
	public ResponseEntity<?>handlePaymentError(PaymentError e){
		ErrorResponse errorResponse=ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errorMessage(e.getMessage()).errorCode("20").build();
		System.out.println("**************************************"+errorResponse);
	     return ResponseEntity.internalServerError().body(errorResponse);
	}
}
