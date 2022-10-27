package com.banking.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
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
		Loan loan=this.dtoToLoan(loanDto);
		LoanDto createdLoan=this.loanToDto(this.loanRepo.save(loan));
		return this.loanToDto(loan);
	}

	@Override
	public LoanDto getLoanById(Integer id) {
		Loan loan =this.loanRepo.findById(id).orElse(null);//handle exception
		return this.loanToDto(loan);
	}

	@Override
	public LoanDto updateLoan(LoanDto loanDto, Integer id) {
		Optional<Loan> optLoan=this.loanRepo.findById(id);
		Loan loan = null;
		if(optLoan.isPresent()){
	    loan = optLoan.get();
		loan=this.dtoToLoan(loanDto);
		loan.setId(id);
		Loan updatedLoan=this.loanRepo.save(loan);
		LoanDto updatedLoanDto=this.loanToDto(updatedLoan);
		return updatedLoanDto;
		}
		return null; // no such user 
	}

	@Override
	public void deleteLoan(Integer id) {
		Loan loan=this.loanRepo.findById(id).orElse(null);//exception
		this.loanRepo.delete(loan);
	}

	@Override
	public List<LoanDto> getAllLoan() {
		// TODO Auto-generated method stub
		return null;
	}
	public LoanDto loanToDto(Loan loan) {
		return this.modelMapper.map(loan, LoanDto.class);
	}
public Loan dtoToLoan(LoanDto loanDto) {
	return this.modelMapper.map(loanDto, Loan.class);
}

@Override
public LoanDto getLoanByUserId(Integer id) {
	Loan loan=this.loanRepo.findByUserId(id);
	return this.loanToDto(loan);
}

@Override
public String nothing() {
return "nothing";
}

@Override
public void approveLoan(Integer id) throws NullPointerException{

	Loan loan =this.loanRepo.findById(id).orElse(null);
//	loan.setSTatus("approved");
this.loanRepo.save(loan);

	
	
}

@Override
public void rejectLoan(Integer id) throws NullPointerException {


		Loan loan =this.loanRepo.findById(id).orElse(null);
//		loan.setSTatus("rejected");
	this.loanRepo.save(loan);

		
		
	}

@Override
public List<LoanDto> filterStatus(String status) {
	List<Loan>filteredLoan=this.loanRepo.findAllByStatus(status);
		return filteredLoan.stream().map(loan->this.loanToDto(loan)).collect(Collectors.toList());
	
}
	

}
