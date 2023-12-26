package com.cloudGateway.CloudGateway.filter;

import java.io.ObjectInputFilter.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.cloudGateway.CloudGateway.util.Jwtutil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	@Autowired
	private RouteValidator  routeValidator;
	@Autowired
	private Jwtutil jwtutil;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange,chain)->{
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing Autherization Header");
				}
				String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader!=null&& authHeader.startsWith("Bearer ")) {
					authHeader=authHeader.substring(7);
				}
				try {
					jwtutil.validateToken(authHeader);
				}
				catch(Exception e) {
					throw new RuntimeException("unauthorized access to the application");
				}
			}
			return chain.filter(exchange);
		});
	}
	public static class Config{
		
	}
}
