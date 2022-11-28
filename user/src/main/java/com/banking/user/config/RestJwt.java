package com.banking.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.banking.user.entity.CustomUserDetail;
@Service
public class RestJwt {
	@Autowired
	private JwtUtil jwtTokenUtil;
	public String createToken() {
		String username="username";
		String password="password";
		 UserDetails userDetails=new CustomUserDetail(username, password);
		 return jwtTokenUtil.generateToken(userDetails);
	}
	public HttpEntity<?>  createEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String baseCredential=createToken();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+baseCredential);
		return new HttpEntity<>( headers);
	}
}
