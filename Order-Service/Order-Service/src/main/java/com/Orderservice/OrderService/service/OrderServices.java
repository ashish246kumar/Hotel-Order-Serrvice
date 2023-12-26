package com.Orderservice.OrderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.Orderservice.OrderService.dto.PaymentEntity;
import com.Orderservice.OrderService.dto.TransactionRequest;
import com.Orderservice.OrderService.dto.TransactionResponse;
import com.Orderservice.OrderService.entity.Order;
import com.Orderservice.OrderService.repository.OrderRepo;
import com.Orderservice.OrderService.exception.OrderServiceException;
@Service
public class OrderServices {

	@Autowired
	private OrderRepo orderRepository;
	
	@Autowired
	@Lazy
	public RestTemplate restTemplate;
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		String response="";
		Order order=request.getOrder();
		PaymentEntity payment=request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		System.out.println("orderService:"+payment.getAmount());
		PaymentEntity paymentResponse=null;
		try {
			ResponseEntity<PaymentEntity> paymentResponseEntity=restTemplate.postForEntity("http://PAYMENT-SERVICE/payment/doPayment", payment, PaymentEntity.class);
			paymentResponse = paymentResponseEntity.getBody();

			response=paymentResponse.getPaymentStatus().equals("success")?"payment processing sucessful":"there is failure in payment";
			
			orderRepository.save(order);
			
		}
		catch(HttpServerErrorException e) {
			throw new OrderServiceException(e.getResponseBodyAsString());
		}
		
		return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
		
	}
	
	
}
