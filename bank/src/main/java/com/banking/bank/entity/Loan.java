package com.banking.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;
@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	@NotNull
private int userId;
public Loan() {

	}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}@NotNull
private String loanType;
public Loan(int id, int userId, String loanType, long amount, String date, long rateOfInterest, long duration,
		String status) {
	super();
	this.id = id;
	this.userId = userId;
	this.loanType = loanType;
	this.amount = amount;
	this.date = date;
	this.rateOfInterest = rateOfInterest;
	this.duration = duration;
	this.status = status;
}
@NotNull
private long amount;
@NotNull
private String date;
@NotNull
private long rateOfInterest;
@NotNull
private long duration;
@NotNull
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLoanType() {
	return loanType;
}
public void setLoanType(String loanType) {
	this.loanType = loanType;
}
public long getAmount() {
	return amount;
}
public void setAmount(long amount) {
	this.amount = amount;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public long getRateOfInterest() {
	return rateOfInterest;
}
public void setRateOfInterest(long rateOfInterest) {
	this.rateOfInterest = rateOfInterest;
}
public long getDuration() {
	return duration;
}
public void setDuration(long duration) {
	this.duration = duration;
}
}
