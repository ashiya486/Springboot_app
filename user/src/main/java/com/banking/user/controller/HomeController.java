package com.banking.user.controller;


import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.user.config.JwtUtil;

import com.banking.user.exception.BadRequestException;
import com.banking.user.payload.AuthenticationRequest;
import com.banking.user.payload.AuthenticationResponse;
import com.banking.user.payload.UserDto;
import com.banking.user.service.CustomUserDetailService;
import com.banking.user.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailService userDetailService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@PostMapping("/auth")
	public  ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
		try{
			authenticationManager.
			authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch(Exception e){throw new BadRequestException("invalid credentials");}
		final UserDetails userDetails=userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid@RequestBody UserDto userDto) {
		UserDto createdUser=this.userService.createUser(userDto);
		return ResponseEntity.of(Optional.of(createdUser));
	}


}