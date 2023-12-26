package com.userDetailService.UserDetailService.config;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.userDetailService.UserDetailService.repository.UserRepository;
import com.userDetailService.UserDetailService.entity.UserCredtional;
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserCredtional>credtional=repository.findByName(username);
		return credtional.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name:"+username));
	}
	
	
}
