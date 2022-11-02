package com.banking.bank.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanDto {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotNull
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@NotEmpty
	@Size(max=50)
	private String loanType;
	@NotNull
	@Min(0)
	private long amount;
	@JsonFormat( pattern = "MM/dd/yyyy")
	private String date;
	@NotNull
	@Max(100)
	private long rateOfInterest;
	@NotNull
	@Min(0)
	private long duration;
	@NotEmpty
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate futureDate = LocalDate.parse(date, formatter);
		if(checkfuture(futureDate, currentDate)){throw new RuntimeException("date cannot be in future");
		}
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
	public boolean checkfuture(LocalDate futureDate,LocalDate currentDate) {
			   return futureDate.isAfter(currentDate);
			}
}
