package com.banking.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.banking.bank.dto.LoanDto;

public interface LoanService {
	
	LoanDto createLoan(LoanDto loanDto);
	LoanDto getLoanById(Integer id);
	LoanDto updateLoan(LoanDto loanDto,Integer id);
	List<LoanDto> getAllLoan();
	void deleteLoan(Integer id);
	LoanDto getLoanByUserId(Integer id);
	String nothing();
	void approveLoan(Integer id);
	void rejectLoan(Integer id);

}
