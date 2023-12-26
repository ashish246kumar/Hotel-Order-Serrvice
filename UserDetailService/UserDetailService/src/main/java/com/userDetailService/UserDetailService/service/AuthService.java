package com.userDetailService.UserDetailService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userDetailService.UserDetailService.entity.UserCredtional;
import com.userDetailService.UserDetailService.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	
	
	public String saveUser(UserCredtional userCredtional) {
		userCredtional.setPassword(passwordEncoder.encode(userCredtional.getPassword()));
		userRepository.save(userCredtional);
		return "user added to the system";
	}

	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}

	public void ValidateToken(String token) {
		
		jwtService.validateToken(token);
	}
}
