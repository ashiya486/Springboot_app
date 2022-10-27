package com.banking.user.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.banking.user.dto.LoanDtoVO;
import com.banking.user.dto.UserDto;
import com.banking.user.service.UserService;

@RestController
@RequestMapping("/home/admin")
public class AdminController {
	@Autowired
	private RestTemplate restTemplate;
	String endpoint="http://localhost:8082/bank/";
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> fethcedUsers=this.userService.getAllUsers();
		return ResponseEntity.of(Optional.of(fethcedUsers));
	}
	@DeleteMapping("/del/{id}")
	public void deleteUser(@PathVariable Integer id) {
		this.userService.deleteUser(id);
	}
	@GetMapping("/loan")
	public ResponseEntity<?> getallloans() throws URISyntaxException{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Integer> entity =new HttpEntity<>(id,headers);
		 return restTemplate.getForEntity(endpoint, List.class);
}
	@PutMapping("/loan/approve/{id}")
	public ResponseEntity<?> approveloan(@PathVariable Integer id) throws URISyntaxException{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 return restTemplate.getForEntity(endpoint+"approve/"+id, String.class);
}
	@PutMapping("/loan/reject/{id}")
	public ResponseEntity<?> rejectloan(@PathVariable Integer id) throws URISyntaxException{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		 return restTemplate.getForEntity(endpoint+"reject/"+id, String.class);
//		return ResponseEntity.of(Optional.of("comp"));
	}

}