package com.banking.user.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.banking.user.dto.UserDto;
import com.banking.user.service.UserService;

@RestController
@RequestMapping("/home/admin")
public class AdminController {
	@Autowired
	private RestTemplate restTemplate;
	String endpoint = "http://BANK-SERVICE/bank/";
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		try {
			List<UserDto> fethcedUsers = this.userService.getAllUsers();
			return ResponseEntity.of(Optional.of(fethcedUsers));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/loan")
	public ResponseEntity<?> getallloans() throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint, List.class);
		} catch (RestClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).build();
		}

	}

	@PutMapping("/loan/approve/{id}")
	public ResponseEntity<?> approveloan(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint + "approve/" + id, String.class);
		} 
		catch (RestClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).build();
		}
	}

	@PutMapping("/loan/reject/{id}")
	public ResponseEntity<?> rejectloan(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint + "reject/" + id, String.class);
		} catch (RestClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).build();
		}

	}

	@GetMapping("/loan/filter/{id}")
	public ResponseEntity<?> filterloan(@PathVariable("id") String id) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint + "filter/" + id, String.class);
		} 
		catch (RestClientResponseException e) {
			return ResponseEntity.status(e.getRawStatusCode()).build();
		}

	}
//	@DeleteMapping("/del/{id}")
//	public void deleteUser(@PathVariable Integer id) {
//		this.userService.deleteUser(id);
//	}

}
