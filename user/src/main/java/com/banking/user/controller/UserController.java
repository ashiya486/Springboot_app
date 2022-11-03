package com.banking.user.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

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
	String endpoint = "http://localhost:8082/bank/";
	String username="userModule";
	String password="UserPassword";

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
		String encoding=Base64.getEncoder().encodeToString((username+":"+password).getBytes());
		headers.set(HttpHeaders.AUTHORIZATION, "Basic "+encoding);
		HttpEntity<LoanDtoVO> entity = new HttpEntity<>(loanDto, headers);
		try {
			return restTemplate.postForEntity(endpoint, entity, LoanDtoVO.class);}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
			 
	}
	@GetMapping("/loan/{id}")
	public ResponseEntity<?> getLoanForUser(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			 return restTemplate.getForEntity(endpoint + id, List.class);}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
		}

//@PutMapping("/loan/{id}")
//public ResponseEntity<?> updateLoanForUser(@RequestBody LoanDtoVO loanDto,@PathVariable Integer id) throws URISyntaxException{
//	HttpHeaders headers=new HttpHeaders();
//	headers.setContentType(MediaType.APPLICATION_JSON);
//	return restTemplate.postForEntity(endpoint, loanDto, LoanDtoVO.class);
//}

}
