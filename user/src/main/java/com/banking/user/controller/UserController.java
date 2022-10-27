package com.banking.user.controller;



import java.net.URISyntaxException;
import java.util.Optional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	private BCryptPasswordEncoder pencode;
	@Autowired
	private UserService userService;
String endpoint="http://localhost:8082/bank/";
@PostMapping("/")
public ResponseEntity<UserDto> createUser(@Valid@RequestBody UserDto userDto) {
	UserDto createdUser=this.userService.createUser(userDto);
	return ResponseEntity.of(Optional.of(createdUser));
}
@PutMapping("/{id}")
public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto userDto,@PathVariable Integer id){
	UserDto updatedUser=this.userService.updateUser(userDto, id);
	return ResponseEntity.of(Optional.of(updatedUser)) ;
}

@GetMapping("/{id}")
public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
	UserDto fetchedUser= this.userService.getUserByUserId(id);
	return ResponseEntity.of(Optional.of(fetchedUser));
}
@PostMapping("/loan")
public ResponseEntity<?> createLoan(@Valid@RequestBody LoanDtoVO loanDto ) throws URISyntaxException{
	HttpHeaders headers=new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<LoanDtoVO> entity =new HttpEntity<>(loanDto,headers);
	return restTemplate.postForEntity(endpoint, entity,LoanDtoVO.class );

//	RequestEntity request = RequestEntity
//		     .post(new URI("http://localhost:8082/bank/"))
//		     .accept(MediaType.APPLICATION_JSON)
//		     .body(loanDto);
//	return restTemplate.exchange(entity, LoanDtoVO.class);
//	return ResponseEntity.of(Optional.of(entity));
	}
@GetMapping("/loan/{id}")
public ResponseEntity<?> getLoanForUser(@PathVariable Integer id) throws URISyntaxException{
	HttpHeaders headers=new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	return restTemplate.getForEntity(endpoint+id, LoanDtoVO.class);
	}
//@PutMapping("/loan/{id}")
//public ResponseEntity<?> updateLoanForUser(@RequestBody LoanDtoVO loanDto,@PathVariable Integer id) throws URISyntaxException{
//	HttpHeaders headers=new HttpHeaders();
//	headers.setContentType(MediaType.APPLICATION_JSON);
//	return restTemplate.postForEntity(endpoint, loanDto, LoanDtoVO.class);
//}

}
