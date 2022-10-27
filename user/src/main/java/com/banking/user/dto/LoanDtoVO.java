package com.banking.user.dto;

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
		private String loan_type;
		private long amount;
		private String date;
		private long rate_of_interest;
		private long duration;
		public String getLoan_type() {
			return loan_type;
		}
		public void setLoan_type(String loan_type) {
			this.loan_type = loan_type;
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
		public long getRate_of_interest() {
			return rate_of_interest;
		}
		public void setRate_of_interest(long rate_of_interest) {
			this.rate_of_interest = rate_of_interest;
		}
		public long getDuration() {
			return duration;
		}
		public void setDuration(long duration) {
			this.duration = duration;
		}
}
