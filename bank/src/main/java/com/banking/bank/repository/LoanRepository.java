package com.banking.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.bank.entity.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	public Loan findByUserId(Integer id);
}
