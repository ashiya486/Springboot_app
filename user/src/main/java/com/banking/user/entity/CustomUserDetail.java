package com.banking.user.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
private User user;
	public CustomUserDetail(User user2) {
	this.user = user2;
}
	public CustomUserDetail(String dummy_username,String dummy_password) {
		User dummyUser=new User(999,"dummy","dumyUsername","Dummy@password123","dummyAddress","dummyAddress","dummyCountry","dummy@email","11111aaaaa","1111111111","11/11/2000","dummyAccountType","Role_Dummy");
	this.user=dummyUser;
	this.user.setPassword(dummy_password);
	this.user.setUsername(dummy_username);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();

	}

	@Override
	public String getUsername() {
		return this.user.getUsername();

	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
