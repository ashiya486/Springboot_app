package com.banking.user.payload;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.banking.user.validation.UserAgeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDto {
	@Autowired
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



public UserDto(int id,
			@NotEmpty(message = "name can't be empty") @Size(max = 50, message = "name should not exceed 50 chars") String name,
			@NotEmpty(message = "Username can't be empty") @Size(min = 8, max = 20, message = "username should have at least 8 characters and at most 20 charcters") @Pattern(regexp = "^(?!.*?[@#$%^&+=()]).{8,20}$", message = "username should not contain any special characters aprt from \"-\"") String username,
			@NotEmpty(message = "password can't be empty") @Size(min = 8, max = 20, message = "password should be between 8 and 20 characters long") @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,20}$", message = "password must have  atleast have one capital letter , one small letter, one special character and one number") String password,
			@NotEmpty(message = "address can't be empty") @Size(max = 200, message = "address can't be more than 200 charcters long") String address,
			@NotEmpty(message = "state can't be empty") @Size(max = 50, message = "state can't be more than 50 characters long") String state,
			@NotEmpty(message = "country can't be empty") @Size(max = 100, message = "country can't e more than 100 characters long") String country,
			@NotEmpty(message = "email can't be empty") @Email(message = "email address not valid") String email,
			@NotEmpty(message = "PAN can't be empty") @Size(min = 10, max = 10, message = "inavlid PAN should be 10 characters long") String pan,
			@NotEmpty(message = "contactNo can't be empty") @Size(min = 10, max = 10, message = "invalid contact number should be 10 digits ") String contactNo,
			@NotEmpty(message = "DOB can't be empty") String dob,
			@NotEmpty(message = "accountType can't be empty") @Size(max = 50, message = "can't be more than 50 characters long") String accountType,
			@NotEmpty(message = "role can't be empty") String role) {
		super();
		this.id = id;
		this.name = name;
		Username = username;
		this.password = password;
		this.address = address;
		this.state = state;
		this.country = country;
		this.email = email;
		this.pan = pan;
		this.contactNo = contactNo;
		this.dob = dob;
		this.accountType = accountType;
		this.role = role;
	}

public UserDto(){}
@NotEmpty(message="name can't be empty")
@Size(max=50,message="name should not exceed 50 chars")
	private String name;
@NotEmpty(message="Username can't be empty")
@Size(min=8,max=20,message="username should have at least 8 characters and at most 20 charcters")
@Pattern(regexp = "^(?!.*?[@#$%^&+=()]).{8,20}$",message="username should not contain any special characters aprt from - ")
	private String Username;
@NotEmpty(message="password can't be empty")
@Size(min=8,max=20 ,message="password should be between 8 and 20 characters long")
@Pattern(regexp ="^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,20}$",message="password must have  atleast have one capital letter , one small letter, one special character and one number")
	private String password;
@NotEmpty(message="address can't be empty")
@Size(max=200,message="address can't be more than 200 charcters long")
	private String address;
@NotEmpty(message="state can't be empty")
@Size(max=50,message="state can't be more than 50 characters long")
	private String state;
@NotEmpty(message="country can't be empty")
@Size(max=100,message="country can't e more than 100 characters long")
	private String country;
@NotEmpty(message="email can't be empty")
@Email(message="email address not valid")
	private String email;
@NotEmpty(message="PAN can't be empty")
@Size(min=10,max=10,message="inavlid PAN should be 10 characters long")
	private String pan;
@NotEmpty(message="contactNo can't be empty")
@Size(min=10,max=10,message="invalid contact number should be 10 digits ")
	private String contactNo;
@NotEmpty(message="DOB can't be empty")
@JsonFormat( pattern = "dd/MM/yyyy")
@UserAgeValidation
private String dob;
@NotEmpty(message="accountType can't be empty")
@Size(max=50,message="can't be more than 50 characters long")
	private String accountType;
@NotEmpty(message="role can't be empty")
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


	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int calculateAge(
			  LocalDate birthDate,
			  LocalDate currentDate) {
			    // validate inputs ...
			    return Period.between(birthDate, currentDate).getYears();
			}
}
