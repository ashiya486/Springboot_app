package com.banking.user.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.banking.user.UserApplication;
import com.banking.user.dto.UserDto;
import com.banking.user.entity.User;
import com.banking.user.repository.UserRepository;

@SpringBootTest(classes = UserApplication.class)
public class ServiceTest {
	@Mock
	UserRepository userRepo;
	@InjectMocks
	UserServiceImpl userService;
	@Autowired
	User user;
	@Autowired
	UserDto userDto;
	@Autowired
	ModelMapper modelMapper;

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

public User dtoToUser(UserDto userDto) {
	User user=this.modelMapper.map(userDto,User.class);
	return user;}

@Test
public void test_getUserByUserId() {
User user=new User(3,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","savings","06/08/2000","ROLE_USER");
int userId=3;
//when(userRepo.findById(userId)).thenReturn(Optional.of(user));
//assertEquals(userDto,userService.getUserByUserId(userId));
}
}
