package com.banking.bank.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.bank.dto.LoanDto;
import com.banking.bank.entity.Loan;
import com.banking.bank.exception.BadRequestException;
import com.banking.bank.exception.NotfoundException;
import com.banking.bank.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LoanDto createLoan(LoanDto loanDto)throws BadRequestException {
		Loan loanRequest = this.dtoToLoan(loanDto);
		List<Loan> listLoan = loanRepo.findAllByUserId(loanRequest.getUserId());
		if (!listLoan.isEmpty()) {
			listLoan.stream().forEach(loan -> {
				if (loan.getLoanType() == loanRequest.getLoanType()
						&& loan.getAmount() == loanRequest.getAmount()
						&& loan.getDate() == loanRequest.getDate()
						&& loan.getRateOfInterest() == loanRequest.getRateOfInterest()
						&& loan.getDuration() == loanRequest.getDuration())
					throw new BadRequestException("duplicate loan entry");

			});
		}
		loanRequest.setStatus("pending");
		LoanDto createdLoan = this.loanToDto(this.loanRepo.save(loanRequest));
		return createdLoan;
	}
//
//	@Override
//	public LoanDto getLoanById(Integer id) throws NotfoundException  {
//		Loan loan = this.loanRepo.findById(id).orElse(null);
//		if (loan == null) {
//			throw new NoSuchElementException("no loan found with id " + id);
//		}
//		return this.loanToDto(loan);
//	}

	@Override
	public List<LoanDto> getAllLoan() throws NotfoundException {
		List<Loan> loanList = this.loanRepo.findAll();
		if (loanList.isEmpty()) {
			throw new NotfoundException("no loans found");
		}
		return loanList.stream().map(loan -> this.loanToDto(loan)).collect(Collectors.toList());

	}

	@Override
	public List<LoanDto> getLoanByUserId(Integer id) throws NotfoundException {
		List<Loan> loanList = this.loanRepo.findAllByUserId(id);
		if (loanList.isEmpty()) {
			throw new NotfoundException("no loan found registered under user id " + id);
		}
		return loanList.stream().map(loan -> this.loanToDto(loan)).collect(Collectors.toList());
	}

	@Override
	public void approveLoan(Integer id) throws NotfoundException {
		Loan loan = this.loanRepo.findById(id).orElse(null);
		if(loan==null)
			throw new NotfoundException("no loan with id "+id+" found to approve");
		loan.setStatus("approved");
		this.loanRepo.save(loan);
	}

	@Override
	public void rejectLoan(Integer id) throws NotfoundException {
		Loan loan = this.loanRepo.findById(id).orElse(null);
		if(loan==null)
			throw new NotfoundException("no loan with id "+id+" found to reject");
		loan.setStatus("rejected");
		this.loanRepo.save(loan);
	}

	@Override
	public List<LoanDto> filterStatus(String status) throws NoSuchElementException {
		if(!status.equals("pending")&&!status.equals("aproved")&&!status.equals("rejected"))
			throw new BadRequestException("request "+status+" does not match any saved filter");
		List<Loan> filteredLoan = this.loanRepo.findAllByStatus(status);
		if(filteredLoan.isEmpty())
			throw new NotfoundException("Currently there are no "+status+" loans ");
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