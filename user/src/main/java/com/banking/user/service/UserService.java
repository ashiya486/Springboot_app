package com.banking.user.service;

import java.util.List;

import com.banking.user.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer id);	
	UserDto getUserByUserId(Integer id);
	List<UserDto> getAllUsers();
	void deleteUser(Integer id);
	
}