package com.banking.user.service; 
 
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.stereotype.Service;
  
 import com.banking.user.entity.CustomUserDetail;
 import com.banking.user.entity.User;
 import com.banking.user.repository.UserRepository;
  
 @Service 
 public class CustomUserDetailService implements UserDetailsService{
  
  @Autowired 
  private UserRepository userRepo;
  @Override 
  public UserDetails loadUserByUsername(String username) throws
  UsernameNotFoundException { 
	  User user=this.userRepo.findByUsername(username);
  return new CustomUserDetail(user); }
  
  
 public UserDetails loadUserById(Integer userId) throws
 UsernameNotFoundException { 
	  User user=this.userRepo.findById(userId).orElse(null);
 return new CustomUserDetail(user); }
 
 }
 
 