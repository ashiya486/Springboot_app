package com.banking.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.bank.entity.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	public List<Loan> findAllByStatus(String status);
	public List<Loan> findAllByUserId(Integer id);
	
}
