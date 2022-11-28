package com.banking.user.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.banking.user.UserApplication;
import com.banking.user.entity.User;
import com.banking.user.payload.UserDto;
import com.banking.user.repository.UserRepository;

@SpringBootTest(classes = UserApplication.class)
public class ServiceTest {
	@Mock
	UserRepository userRepo;
	@InjectMocks
	UserServiceImpl userService;
	@Mock
	ModelMapper modelMapper;
	@Mock
	BCryptPasswordEncoder passEncode;

//	public UserDto userToDto(User user) {
//		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		return userDto;
//	}
//
//public User dtoToUser(UserDto userDto) {
//	User user=this.modelMapper.map(userDto,User.class);
//	return user;}
	@Test
public void test_createUser(){
UserDto userDto=new UserDto(0,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
User user=new User(0,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
User afterSaveUser=new User(3,"harry","harry321","*****","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
UserDto afterSaveUserDto=new UserDto(3,"harry","harry321","*****","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");

when(modelMapper.map(userDto, User.class)).thenReturn(user);
when(userRepo.findByUsername("harry321")).thenReturn(null);
when(userRepo.findByEmail(ArgumentMatchers.anyString())).thenReturn(null);
when(passEncode.encode(ArgumentMatchers.anyString())).thenReturn("*****");
//assertThat(user.getPassword()).isEqualTo("*****");
when(userRepo.save(ArgumentMatchers.any(User.class))).thenReturn(afterSaveUser);
when(modelMapper.map(afterSaveUser,UserDto.class)).thenReturn(afterSaveUserDto);
assertEquals(afterSaveUserDto,userService.createUser(userDto));
}
 @Test
public void test_getUserByUserId() {
UserDto userDto=new UserDto(3,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
User user=new User(3,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
//UserDto userDto=modelMapper.map(user,UserDto.class);
int userId=3;
when(this.userRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(user));
when(this.modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(userDto);
assertEquals(userDto,userService.getUserByUserId(userId));
}

@Test
public void test_updateUser() {
 User user=new User(3,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
 UserDto userDto=new UserDto(0,"aditya","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
 User afterSaveUser=new User(3,"aditya","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
 UserDto afterSaveUserDto=new UserDto(3,"aditya","harry321","*****","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");

 int user_id=3;
when(userRepo.findById(user_id)).thenReturn(Optional.of(user));
when(modelMapper.map(userDto, User.class)).thenReturn(user);
when(passEncode.encode(ArgumentMatchers.anyString())).thenReturn("*****");
when(userRepo.save(ArgumentMatchers.any(User.class))).thenReturn(afterSaveUser);
when(modelMapper.map(afterSaveUser,UserDto.class)).thenReturn(afterSaveUserDto);
assertEquals(afterSaveUserDto,userService.updateUser(userDto,user_id));
}
@Test
public void test_getAllUsers() {
	List<User>userList=new ArrayList<>();
	 User user1=new User(1,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
	 User user2=new User(2,"aditya","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
	 userList.add(user1);
	 userList.add(user2);
when(userRepo.findAll()).thenReturn(userList);

}
 }

