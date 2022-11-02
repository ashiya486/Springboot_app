package com.banking.user.controller;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.banking.user.dto.LoanDtoVO;
import com.banking.user.dto.UserDto;
import com.banking.user.service.UserService;

@RestController
@RequestMapping("/home/user")
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserService userService;
	String endpoint = "http://BANK-SERVICE/bank/";

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
		try {
			UserDto updatedUser = this.userService.updateUser(userDto, id);
			return ResponseEntity.of(Optional.of(updatedUser));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {

		try {
			UserDto fetchedUser = this.userService.getUserByUserId(id);
			return ResponseEntity.ok(fetchedUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/loan")
	public ResponseEntity<?> createLoan(@RequestBody LoanDtoVO loanDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<LoanDtoVO> entity = new HttpEntity<>(loanDto, headers);
		try {
			return restTemplate.postForEntity(endpoint, entity, LoanDtoVO.class);
		} catch (RestClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).build();
		}

	}

	@GetMapping("/loan/{id}")
	public ResponseEntity<?> getLoanForUser(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint + id, LoanDtoVO.class);
		} catch (RestClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}
//@PutMapping("/loan/{id}")
//public ResponseEntity<?> updateLoanForUser(@RequestBody LoanDtoVO loanDto,@PathVariable Integer id) throws URISyntaxException{
//	HttpHeaders headers=new HttpHeaders();
//	headers.setContentType(MediaType.APPLICATION_JSON);
//	return restTemplate.postForEntity(endpoint, loanDto, LoanDtoVO.class);
//}

}
