package com.Orderservice.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Orderservice.OrderService.dto.TransactionRequest;
import com.Orderservice.OrderService.dto.TransactionResponse;
import com.Orderservice.OrderService.entity.Order;
import com.Orderservice.OrderService.service.OrderServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServices orderService;
	
	@PostMapping("/bookOrder")
	@CircuitBreaker(name="bookorderBreaker",fallbackMethod="bookFallback")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
		return orderService.saveOrder(request);
	}
	
	public TransactionResponse bookFallback(TransactionRequest request,Exception ex) {
		System.out.println("service is down"+ex.getMessage());
		

        
        TransactionResponse dummyResponse = new TransactionResponse();
        dummyResponse.setOrder(null);
        dummyResponse.setAmount(39.98);
        dummyResponse.setTransactionId("DUMMY_TRANSACTION_ID");
        dummyResponse.setMessage("Transaction successful");

        return dummyResponse;
		
	}
}
