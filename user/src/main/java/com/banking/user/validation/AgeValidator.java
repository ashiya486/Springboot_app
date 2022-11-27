package com.banking.user.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<UserAgeValidation, String> {

	@Override
	public boolean isValid(String age, ConstraintValidatorContext context) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(age, formatter);
		int ageparam=18;
		
		return currentDate.minusYears(ageparam).isAfter(date);
	}

}
