package com.banking.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.banking.user.dto.UserDto;
import com.banking.user.exception.NotfoundException;
import com.banking.user.exception.RestTemplateException;
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
		List<UserDto> fetchedUsers = this.userService.getAllUsers();
			return ResponseEntity.of(Optional.of(fetchedUsers));

	}

	@GetMapping("/loan")
	public ResponseEntity<?> getallloans(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return	restTemplate.getForEntity(endpoint, List.class);}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
		
	}
	
	@PutMapping("/loan/approve/{id}")
	public ResponseEntity<?> approveloan(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint + "approve/" + id, String.class);}
		catch(HttpClientErrorException e) {throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
	}

	@PutMapping("/loan/reject/{id}")
	public ResponseEntity<?> rejectloan(@PathVariable Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			return restTemplate.getForEntity(endpoint + "reject/" + id, String.class);}
		catch(HttpClientErrorException e){throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}

	}

	@GetMapping("/loan/filter/{id}")
	public ResponseEntity<?> filterloan(@PathVariable("id") String id){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
try { return restTemplate.getForEntity(endpoint + "filter/" + id, String.class);}
catch(HttpClientErrorException e){throw new RestTemplateException(e.getStatusCode(),e.getResponseBodyAsString());}
}
@GetMapping("test/")
public ResponseEntity<?>test(){
	throw new NotfoundException("test method");
}
//	@DeleteMapping("/del/{id}")
//	public void deleteUser(@PathVariable Integer id) {
//		this.userService.deleteUser(id);
//	}

}
