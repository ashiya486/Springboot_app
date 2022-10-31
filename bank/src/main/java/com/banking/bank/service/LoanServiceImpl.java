package com.banking.bank.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bank.dto.LoanDto;
import com.banking.bank.entity.Loan;
import com.banking.bank.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LoanDto createLoan(LoanDto loanDto) {
		Loan loan = this.dtoToLoan(loanDto);
		LoanDto createdLoan = this.loanToDto(this.loanRepo.save(loan));
		return createdLoan;
	}

	@Override
	public LoanDto getLoanById(Integer id) throws NoSuchElementException {
		Loan loan = this.loanRepo.findById(id).orElse(null);
		if (loan == null) {
			throw new NoSuchElementException("no loan found with id " + id);
		}
		return this.loanToDto(loan);
	}

	@Override
	public List<LoanDto> getAllLoan() throws NoSuchElementException {
		List<Loan> loanList = this.loanRepo.findAll();
		if (loanList == null) {
			throw new NoSuchElementException("no loans found");
		}
		return loanList.stream().map(loan -> this.loanToDto(loan)).collect(Collectors.toList());

	}

	@Override
	public LoanDto getLoanByUserId(Integer id) throws NoSuchElementException {
		Loan loan = this.loanRepo.findByUserId(id);
		if (loan == null) {
			throw new NoSuchElementException("no loan found registered under user id " + id);
		}
		else {return this.loanToDto(loan);}
		}

	@Override
	public void approveLoan(Integer id) throws NoSuchElementException {

		Loan loan = this.loanRepo.findById(id).orElse(null);
		loan.setStatus("approved");
		this.loanRepo.save(loan);
	}

	@Override
	public void rejectLoan(Integer id) throws NoSuchElementException {
		Loan loan = this.loanRepo.findById(id).orElse(null);
		loan.setStatus("rejected");
		this.loanRepo.save(loan);
	}

	@Override
	public List<LoanDto> filterStatus(String status) throws NoSuchElementException {
		List<Loan> filteredLoan = this.loanRepo.findAllByStatus(status);
return filteredLoan.stream().map(loan -> this.loanToDto(loan)).collect(Collectors.toList());
}

	public LoanDto loanToDto(Loan loan) {
		return this.modelMapper.map(loan, LoanDto.class);
	}

	public Loan dtoToLoan(LoanDto loanDto) {
		return this.modelMapper.map(loanDto, Loan.class);
	}

}
//@Override
//public LoanDto updateLoan(LoanDto loanDto, Integer id) {
//	Optional<Loan> optLoan=this.loanRepo.findById(id);
//	Loan loan = null;
//	if(optLoan.isPresent()){
//    loan = optLoan.get();
//	loan=this.dtoToLoan(loanDto);
//	loan.setId(id);
//	Loan updatedLoan=this.loanRepo.save(loan);
//	LoanDto updatedLoanDto=this.loanToDto(updatedLoan);
//	return updatedLoanDto;
//	}
//	return null; // no such user 
//}
//
//@Override
//public void deleteLoan(Integer id) {
//	Loan loan=this.loanRepo.findById(id).orElse(null);//exception
//	this.loanRepo.delete(loan);
//}