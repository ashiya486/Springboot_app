package com.banking.user.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanDtoVO {
		private int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
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
		@NotEmpty
		@Min(0)
		private long amount;
		@NotEmpty
		@JsonFormat( pattern = "MM/dd/yyyy")
		private String date;
		@NotEmpty
		@Min(0)
		@Max(100)
		private long rateOfInterest;
		@NotEmpty
		@Min(0)
		private long duration;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		private String status;
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
