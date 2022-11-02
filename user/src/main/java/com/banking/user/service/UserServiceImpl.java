package com.banking.user.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.user.dto.UserDto;
import com.banking.user.entity.User;
import com.banking.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder passEncode;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) throws RuntimeException {
		User user = this.dtoToUser(userDto);
		if (userRepo.findByUsername(user.getUsername()) != null) {
			throw new RuntimeException("username already taken");
		} else if (userRepo.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("usern already exists with" + user.getEmail() + "email id");
		} else {
			user.setPassword(passEncode.encode(user.getPassword()));
			User savedUser = this.userRepo.save(user);
			return this.userToDto(savedUser);
		}
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User oldUser = this.userRepo.findById(id).orElse(null);
		if (oldUser != null) {
			User user = this.modelMapper.map(userDto, User.class);
			user.setId(id);
			user.setPassword(passEncode.encode(user.getPassword()));
			User updatedUser = this.userRepo.save(user);
			UserDto updatedUserDto = this.userToDto(updatedUser);
			return updatedUserDto;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public UserDto getUserByUserId(Integer id) {
		User user = (this.userRepo.findById(id)).get();
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

}
//
//@Override
//public void deleteUser(Integer id) {
//	User user=this.userRepo.findById(id).orElse(null);
//	this.userRepo.delete(user);
//
//}