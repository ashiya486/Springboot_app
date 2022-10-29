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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority(user.getRole());
//		return Arrays.asList(new SimpleGrantedAuthority("Role_User"));
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
