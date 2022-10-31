package com.banking.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String name;
	@Column(unique=true,nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	public User(int id, String name, String username, String password, String address, String state, String country,
			String email, String pan, String contactNo, String dob, String accountType, String role) {
		this.id = id;
		this.name = name;
		this.username = username;
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
	public User() {}

	@Column(nullable=true)
	private String address;
	@Column(nullable=true)
	private String state;
	@Column(nullable=true)
	private String country;
	@Column(nullable=true,unique=true)
	private String email;
	@Column(nullable=true)
	private String pan;
	@Column(nullable=true)
	private String contactNo;
	@Column(nullable=true)
	private String dob;
	@Column(nullable=true)
	private String accountType;
	@Column(nullable=true)
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
