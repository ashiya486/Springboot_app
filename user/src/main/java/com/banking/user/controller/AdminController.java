package com.banking.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.banking.user.JwtHelper.JwtUtil;
import com.banking.user.dto.UserDto;
import com.banking.user.entity.CustomUserDetail;
import com.banking.user.exception.NotfoundException;
import com.banking.user.exception.RestTemplateException;
import com.banking.user.service.UserService;

@RestController
@RequestMapping("/home/admin")
public class AdminController {
	@Autowired
	private RestTemplate restTemplate;
	String endpoint = "http://bank-service/bank/";
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> fetchedUsers = this.userService.getAllUsers();
			return ResponseEntity.of(Optional.of(fetchedUsers));

	}

	@GetMapping("/loan")
	public ResponseEntity<?> getallloans(){
		HttpEntity<?> entity = createEntity();
		try {
		ResponseEntity<List> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, List.class);
				return response;}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
	}
	
	@PutMapping("/loan/approve/{id}")
	public ResponseEntity<?> approveloan(@PathVariable Integer id) {
		HttpEntity<?> entity = createEntity();
		try {
		ResponseEntity<String> response = restTemplate.exchange(endpoint+"approve/"+id, HttpMethod.PUT, entity, String.class);
				return response;}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
	}

	@PutMapping("/loan/reject/{id}")
	public ResponseEntity<?> rejectloan(@PathVariable Integer id) {
		
		HttpEntity<?> entity = createEntity();
		try {
		ResponseEntity<String> response = restTemplate.exchange(endpoint+"reject/"+id, HttpMethod.PUT, entity, String.class);
				return response;}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
	}

	@GetMapping("/loan/filter/{id}")
	public ResponseEntity<?> filterloan(@PathVariable("id") String id){
		HttpEntity<?> entity = createEntity();
		try {
		ResponseEntity<String> response = restTemplate.exchange(endpoint+"filter/"+id, HttpMethod.GET, entity, String.class);
				return response;}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
	}
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
//@DeleteMapping("/del/{id}")
//public void deleteUser(@PathVariable Integer id) {
//	this.userService.deleteUser(id);
//}
//public String createCredential() {
//String username="username";
//String password="password";
//String auth = username + ":" + password;
//byte [] authentication = auth.getBytes();
//byte[] base64Authentication = Base64Utils.encode(authentication);
//String baseCredential = new String(base64Authentication);
//return baseCredential;
//}
