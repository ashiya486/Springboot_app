package com.banking.user.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.banking.user.JwtHelper.JwtUtil;
import com.banking.user.JwtHelper.RestJwt;
import com.banking.user.dto.LoanDtoVO;
import com.banking.user.dto.UserDto;
import com.banking.user.exception.RestTemplateException;
import com.banking.user.service.UserService;
@RestController
@RequestMapping("/home/user")
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	JwtUtil jwtTokenUtil;
	@Autowired
	private RestJwt restJwt;
	String endpoint = "http://bank-service/bank/";
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {

			UserDto updatedUser = this.userService.updateUser(userDto, id);
			return ResponseEntity.of(Optional.of(updatedUser));

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id) {

			UserDto fetchedUser = this.userService.getUserByUserId(id);
			return ResponseEntity.ok(fetchedUser);
	}

	@PostMapping("/loan")
	public ResponseEntity<?> createLoan(@RequestBody LoanDtoVO loanDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String baseCredential=restJwt.createToken();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+baseCredential);
		HttpEntity<LoanDtoVO> entity = new HttpEntity<>(loanDto, headers);
		try {
			return restTemplate.postForEntity(endpoint, entity, LoanDtoVO.class);}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
			 
	}
	@GetMapping("/loan/{id}")
	public ResponseEntity<?> getLoanForUser(@PathVariable Integer id) {
		HttpEntity<?> entity = restJwt.createEntity();
		try {
		ResponseEntity<List> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, List.class);
				return response;}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
	}


}
