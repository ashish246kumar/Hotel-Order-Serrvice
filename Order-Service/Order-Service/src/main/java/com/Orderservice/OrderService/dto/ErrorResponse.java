package com.Orderservice.OrderService.dto;



import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

	private HttpStatus status;
	private String errorMessage;
	private String errorCode;
}
