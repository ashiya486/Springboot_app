package com.banking.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
@NotEmpty
@Size(max=50,message="name should not exceed 50 chars")
	private String name;
@NotEmpty
@Size(min=8,max=20)
@Pattern(regexp = "(?!.*[@#$%^&+=()]")
	private String Username;
@NotEmpty
@Size(min=8,max=20)
@Pattern(regexp= "^(?=.*[A-Z]) (?=.*[a-z]) (?=.*[0-9])(?=.*[@#$%^&-+=()]$")
	private String password;
@NotEmpty
@Size(max=200)
	private String address;
@NotEmpty
@Size(max=50)
	private String state;
@NotEmpty
@Size(max=100)
	private String country;
@NotEmpty
@Email(message="email address not valid")
	private String email;
@NotEmpty
@Size(min=10,max=10)
	private String pan;
@NotEmpty
@Size(min=10,max=10)
	private String contact_no;
@NotEmpty
@JsonFormat( pattern = "MM/dd/yyyy")
	private String dob;
@NotEmpty
@Size(max=50)
	private String account_type;
@NotEmpty
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}


	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

}
