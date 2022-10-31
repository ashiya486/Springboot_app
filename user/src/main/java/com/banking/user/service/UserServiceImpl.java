package com.banking.user.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

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
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		user.setPassword(passEncode.encode(user.getPassword()));
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer id)throws NoSuchElementException {
		Optional<User> optUser=this.userRepo.findById(id);
		User user = null;
		if(optUser.isPresent()){
	  user = optUser.get();
		}else {throw new NoSuchElementException("no user found with id "+id);}
		user=this.modelMapper.map(userDto, User.class);
		user.setId(id);
		user.setPassword(passEncode.encode(user.getPassword()));
		User updatedUser=this.userRepo.save(user);
		UserDto updatedUserDto=this.userToDto(updatedUser);
		return updatedUserDto;

	}

	@Override
	public UserDto getUserByUserId(Integer id) throws NoSuchElementException{
		User user=this.userRepo.findById(id).orElseThrow(()-> new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = " + id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() throws NoSuchElementException{
		List<User> users=this.userRepo.findAll();
		if(users==null) {
			throw new NoSuchElementException("no users found");
		}
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());	
		return userDtos;
	}
//
//	@Override
//	public void deleteUser(Integer id) {
//		User user=this.userRepo.findById(id).orElse(null);
//		this.userRepo.delete(user);
//
//	}
	public UserDto userToDto(User user) {
UserDto userDto=this.modelMapper.map( user, UserDto.class);
return userDto;}
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		return user;
	}

}
