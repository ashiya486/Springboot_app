package com.banking.bank.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.banking.bank.entity.Loan;


@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRepositoryTest {
	@Autowired
	private LoanRepository loanRepo;

	@Test
	@Order(1)
	public void saveUserTest() {
		Loan loan=new Loan(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	loanRepo.save(loan);
	assertThat(loan.getId()).isGreaterThan(0);
	}
	@Test
	@Order(2)
	public void findAllByUserIdTest() {
		int ControlUserId=1;
		List<Loan> fetchedLoan=loanRepo.findAllByUserId(ControlUserId);
		assertThat(fetchedLoan.size()!=0);
	}
	@Test
	@Order(3)
	public void findAllTest() {
		List<Loan> fetchedLoan=loanRepo.findAll();
		assertThat(fetchedLoan.size()!=0);
	}
	@Test
	@Order(4)
	public void findById() {
		int ControlId=1;
		Optional<Loan> fetchedLoan=loanRepo.findById(ControlId);
		assertThat(!fetchedLoan.isEmpty());
	}
	@Test
	@Order(5)
	public void findAllByStatusTest() {
		String ControlStatus="pending";
		List<Loan> fetchedLoan=loanRepo.findAllByStatus(ControlStatus);
		assertThat(fetchedLoan.size()!=0);
	}
	//check db persistance
	
}
