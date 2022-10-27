package com.banking.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private int userId;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
private String loanType;
private long amount;
private String date;
private long rateOfInterest;
private long duration;
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
