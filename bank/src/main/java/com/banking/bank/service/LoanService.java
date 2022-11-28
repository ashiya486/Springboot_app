package com.banking.bank.service;

import java.util.List;

import com.banking.bank.payload.LoanDto;

public interface LoanService {
	
	LoanDto createLoan(LoanDto loanDto);
//	LoanDto getLoanById(Integer id);
//	LoanDto updateLoan(LoanDto loanDto,Integer id);
	List<LoanDto> getAllLoan();
//	void deleteLoan(Integer id);
	List<LoanDto> getLoanByUserId(Integer id);
	void approveLoan(Integer id);
	void rejectLoan(Integer id);
	List<LoanDto> filterStatus(String status);

}
