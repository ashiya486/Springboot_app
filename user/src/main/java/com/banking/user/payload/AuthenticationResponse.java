package com.banking.user.payload;

public class AuthenticationResponse {
private String jwt;

public String getJwt() {
	return jwt;
}

public AuthenticationResponse(String jwt) {
	this.jwt = jwt;
}

public void setJwt(String jwt) {
	this.jwt = jwt;
}
}
