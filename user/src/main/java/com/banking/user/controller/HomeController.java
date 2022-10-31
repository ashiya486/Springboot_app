package com.banking.user.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.user.JwtHelper.JwtUtil;
import com.banking.user.dto.UserDto;
import com.banking.user.entity.AuthenticationRequest;
import com.banking.user.entity.AuthenticationResponse;
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
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
		);
		}
		catch(BadCredentialsException e) {throw new Exception("Incorrect username or password",e);}
		final UserDetails userDetails=userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt=jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@Valid@RequestBody UserDto userDto) {
		UserDto createdUser=this.userService.createUser(userDto);
		return ResponseEntity.of(Optional.of(createdUser));
	}
	@PostMapping("/logout")
	public ResponseEntity<String> Logout() {
return ResponseEntity.of(Optional.of("logout successfully"));
	}
	@RequestMapping("/test")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		try{List<UserDto> fethcedUsers=this.userService.getAllUsers();
		return ResponseEntity.of(Optional.of(fethcedUsers));
		}
		catch(Exception e){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		}

}