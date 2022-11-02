package com.banking.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bank.dto.LoanDto;
import com.banking.bank.exception.NotfoundException;
import com.banking.bank.service.LoanService;

@RestController
@RequestMapping("/bank")
public class LoanController {
	@Autowired
	private LoanService loanService;
	@PostMapping("/")
	public ResponseEntity<LoanDto> createLoan(@Valid @RequestBody LoanDto loanDto) {
	LoanDto loan = this.loanService.createLoan(loanDto);
		return ResponseEntity.of(Optional.of(loan));
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<LoanDto>> getLoanByUserId(@PathVariable Integer id) {

		List<LoanDto> fetchedLoan = this.loanService.getLoanByUserId(id);
		return ResponseEntity.of(Optional.of(fetchedLoan));
	}

	@GetMapping("/approve/{id}")
	public ResponseEntity<String> approveLoan(@PathVariable Integer id) {
			this.loanService.approveLoan(id);
			return ResponseEntity.ok("approved loan for loan id " + id);
	}

	@GetMapping("/reject/{id}")
	public ResponseEntity<String> rejectLoan(@PathVariable Integer id) {
	
			this.loanService.rejectLoan(id);
			return ResponseEntity.ok("rejected loan for loan id " + id);

	}

	@GetMapping("/")
	public ResponseEntity<List<LoanDto>> getAllLoan() {
		List<LoanDto> loans = this.loanService.getAllLoan();
			return ResponseEntity.of(Optional.of(loans));
	}

	@GetMapping("/filter/{id}")
	public ResponseEntity<?> loanFilter(@PathVariable("id") String filter) {
		List<LoanDto> fetchedLoan = this.loanService.filterStatus(filter);
			return ResponseEntity.of(Optional.of(fetchedLoan));

	}
//@GetMapping("/test/{id}") //get loan by loan id
//public ResponseEntity<LoanDto> getLoanByLoanID(@PathVariable Integer id){
//	LoanDto fetchedLoan=this.loanService.getLoanById(id);
//	return ResponseEntity.of(Optional.of(fetchedLoan));
//}
//@DeleteMapping("/{id}")
//public ResponseEntity<String> deleteLoan(@PathVariable Integer id){
//	this.loanService.deleteLoan(id);
//	return new ResponseEntity<>("deleted with id "+id,HttpStatus.OK);
//
//@PutMapping("/")
//public ResponseEntity<LoanDto> updateLoan(@RequestBody LoanDto loanDto,@PathVariable Integer id){
//	LoanDto updatedLoan=this.loanService.updateLoan(loanDto, id);
//	return ResponseEntity.of(Optional.of(updatedLoan));
//}}
}