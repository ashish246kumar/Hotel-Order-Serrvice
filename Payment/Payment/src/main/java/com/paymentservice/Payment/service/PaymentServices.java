package com.paymentservice.Payment.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentservice.Payment.entity.PaymentEntity;
import com.paymentservice.Payment.repository.PaymentRepository;
import com.paymentservice.Payment.exception.PaymentError;
@Service
public class PaymentServices {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentEntity doPayment(PaymentEntity payment) {
		System.out.println("payment amount"+payment.getAmount());
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepository.save(payment);
		
	}
	public String paymentProcessing() {
		return new Random().nextBoolean()?"success":"false";
	}
	public PaymentEntity findPaymentByOrderId(int orderId) throws PaymentError {
		PaymentEntity p=null;
		p= paymentRepository.findByOrderId(orderId);
		if(p==null) {
			throw new PaymentError("Internl server error");
		}
//		try {
//			p= paymentRepository.findByOrderId(orderId);
//		}
//		catch(Exception e) {
//			
//		}
		else {
			return p;
		}
		
	}
}
