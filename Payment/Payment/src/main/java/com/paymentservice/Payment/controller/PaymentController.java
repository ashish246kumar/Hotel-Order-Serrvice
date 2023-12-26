package com.paymentservice.Payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.Payment.entity.PaymentEntity;
import com.paymentservice.Payment.service.PaymentServices;
import com.paymentservice.Payment.exception.PaymentError;
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentServices service;
	
	@PostMapping("/doPayment")
	public PaymentEntity doPayment(@RequestBody PaymentEntity payment) {
//		System.out.println("controllerPayment"+payment.getAmount());
		
		PaymentEntity paymentEntity=null;
		
			 paymentEntity=service.doPayment(payment);
				
		
		
		return paymentEntity;
	}
	
	@GetMapping("/{orderId}")
	public PaymentEntity findPaymentHistoryByOrderId(@PathVariable int orderId) throws PaymentError {
		PaymentEntity paymentEntity=null;
		
			paymentEntity=  service.findPaymentByOrderId(orderId);
		
		return paymentEntity;
	}
}
