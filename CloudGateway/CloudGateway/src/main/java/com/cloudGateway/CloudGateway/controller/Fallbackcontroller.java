package com.cloudGateway.CloudGateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class Fallbackcontroller {

	@RequestMapping("/orderFallBack")
	public Mono<String>orderServiceFallback(){
		return Mono.just("order service is taking too long to respond or is down. please try again later");
	}
	@RequestMapping("/paymentFallBack")
	public Mono<String>paymentServiceFallback(){
		return Mono.just("payment service is taking too long to respond or is down. please try again later");
	}
	
}
