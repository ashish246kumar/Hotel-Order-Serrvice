package com.userDetailService.UserDetailService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userDetailService.UserDetailService.dto.AuthRequest;
import com.userDetailService.UserDetailService.entity.UserCredtional;
import com.userDetailService.UserDetailService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	public AuthService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/register")
	public String addUser(@RequestBody UserCredtional user) {
		return service.saveUser(user);
		
	}
	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
		if(authenticate.isAuthenticated()) {
			return service.generateToken(authRequest.getUserName());
		}
		else {
			throw new RuntimeException("Invalid Access");
		}
	}
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token")String token) {
		
		service.ValidateToken(token);
		return "Token is valid";
	}
	
	
}
