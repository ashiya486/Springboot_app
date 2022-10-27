package com.banking.user.service;

import java.util.List;
import java.util.Optional;
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
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		user.setPassword(passEncode.encode(user.getPassword()));
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer id) {
		Optional<User> optUser=this.userRepo.findById(id);
		User user = null;
		if(optUser.isPresent()){
	  user = optUser.get();
		}
		user=this.modelMapper.map(userDto, User.class);
		user.setId(id);
		user.setPassword(passEncode.encode(user.getPassword()));
		User updatedUser=this.userRepo.save(user);
		UserDto updatedUserDto=this.userToDto(updatedUser);
		return updatedUserDto;
//		user.setName(userDto.getName());
//		user.setUsername(userDto.getUsername());
//		user.setPassword(userDto.getPassword());
//		user.setAddress(userDto.getAddress());
//		user.setState(userDto.getState());
//		user.setCountry(userDto.getCountry());
//		user.setEmail(userDto.getEmail());
//		user.setPAN(userDto.getPAN());
//		user.setContact_no(userDto.getContact_no());
//		user.setDOB(userDto.getDOB());
//		user.setAccount_type(userDto.getAccount_type());
//		User updatedUser=this.userRepo.save(user);
//		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserByUserId(Integer id) {
		User user=this.userRepo.findById(id).orElse(null);
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());	
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepo.findById(id).orElse(null);
		this.userRepo.delete(user);

	}
	public UserDto userToDto(User user) {
UserDto userDto=this.modelMapper.map( user, UserDto.class);
return userDto;}
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		return user;
	}

}
