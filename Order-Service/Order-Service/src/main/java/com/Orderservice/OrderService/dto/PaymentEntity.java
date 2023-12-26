package com.Orderservice.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

	private int paymentId;
	private String paymentStatus;
	private String transactionId;
	private int orderId;
	private double amount;
}
