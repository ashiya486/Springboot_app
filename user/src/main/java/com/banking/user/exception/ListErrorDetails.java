package com.banking.user.exception;

import java.util.List;

public class ListErrorDetails {
private List<ErrorModel> errorMessage;
private String message;

public ListErrorDetails(List<ErrorModel> errorMessage,String message) {
	this.errorMessage = errorMessage;
	this.message=message;
}
public ListErrorDetails() {};
}
