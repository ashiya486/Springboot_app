package com.banking.bank.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import com.banking.bank.dto.LoanDto;
import com.banking.bank.entity.Loan;
import com.banking.bank.repository.LoanRepository;

@SpringBootTest
public class serviceTest {
	@Mock
	private LoanRepository loanRepo;
	@InjectMocks
	LoanServiceImpl loanService;
	@Mock
	private ModelMapper modelMapper;
	
	public Loan dtoToLoan(LoanDto loanDto) {
		return this.modelMapper.map(loanDto, Loan.class);
	}
	public LoanDto loanToDto(Loan loan) {
		return this.modelMapper.map(loan, LoanDto.class);
	}
@Test
public void test_create_loan() {
	LoanDto inputLoanDto=new LoanDto(1,1,"personal",5000L,"01/10/2012",7L,56L,null);
	Loan inputLoan=new Loan(1,1,"personal",5000L,"01/10/2012",7L,56L,null);
	Loan createdLoan=new Loan(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	LoanDto createLoanDto=new LoanDto(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	when(modelMapper.map(inputLoanDto,Loan.class)).thenReturn(inputLoan);
	when(loanRepo.findAllByUserId(ArgumentMatchers.anyInt())).thenReturn(Collections.EMPTY_LIST);
	when(this.loanRepo.save(ArgumentMatchers.any(Loan.class))).thenReturn(createdLoan);
	when(modelMapper.map(createdLoan,LoanDto.class)).thenReturn(createLoanDto);
	assertEquals(createLoanDto, this.loanService.createLoan(inputLoanDto));
}
@Test
public void test_getAllLoan() {
	List<Loan> loanList=new ArrayList();
	Loan loan1=new Loan(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	Loan loan2=new Loan(2,1,"business",20000L,"02/10/2012",7L,32L,"pending");
	LoanDto loanDto1=new LoanDto(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	LoanDto loanDto2=new LoanDto(2,1,"business",20000L,"02/10/2012",7L,32L,"pending");
	loanList.add(loan1);
	loanList.add(loan2);
	when(this.loanRepo.findAll()).thenReturn(loanList);
	when(modelMapper.map(loan1,LoanDto.class)).thenReturn(loanDto1);
	when(modelMapper.map(loan2,LoanDto.class)).thenReturn(loanDto2);
   assertThat(this.loanService.getAllLoan().size()==2);//check list length 2
	}
@Test
public void test_getLoanByUserId() {
	List<Loan> loanList=new ArrayList<Loan>();
	Loan loan1=new Loan(1,2,"personal",5000L,"01/10/2012",7L,56L,"pending");
	Loan loan2=new Loan(2,2,"business",20000L,"02/10/2012",7L,32L,"pending");
	LoanDto loanDto1=new LoanDto(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	LoanDto loanDto2=new LoanDto(2,1,"business",20000L,"02/10/2012",7L,32L,"pending");
	loanList.add(loan1);
	loanList.add(loan2);
	when(this.loanRepo.findAllByUserId(ArgumentMatchers.anyInt())).thenReturn(loanList);
	when(modelMapper.map(loan1,LoanDto.class)).thenReturn(loanDto1);
	when(modelMapper.map(loan2,LoanDto.class)).thenReturn(loanDto2);
	 assertThat(this.loanService.getLoanByUserId(2).size()==2);
}
@Test
public void test_approveLoan() {
	Loan loan1=new Loan(1,2,"personal",5000L,"01/10/2012",7L,56L,"pending");
	int approveId=2;
when(this.loanRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(loan1));
this.loanService.approveLoan(approveId);
verify(loanRepo,times(1)).save(ArgumentMatchers.any(Loan.class));
}
@Test
public void test_rejectLoan() {
	Loan loan1=new Loan(1,2,"personal",5000L,"01/10/2012",7L,56L,"pending");
	int rejectId=2;
when(this.loanRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(loan1));
this.loanService.rejectLoan(rejectId);
verify(loanRepo,times(1)).save(ArgumentMatchers.any(Loan.class));
}
@Test
public void test_filterStatus() {
	List<Loan> loanList=new ArrayList<Loan>();
	Loan loan1=new Loan(1,2,"personal",5000L,"01/10/2012",7L,56L,"pending");
	Loan loan2=new Loan(2,2,"business",20000L,"02/10/2012",7L,32L,"pending");
	LoanDto loanDto1=new LoanDto(1,1,"personal",5000L,"01/10/2012",7L,56L,"pending");
	LoanDto loanDto2=new LoanDto(2,1,"business",20000L,"02/10/2012",7L,32L,"pending");
	loanList.add(loan1);
	loanList.add(loan2);
	String filter="pending";
	when(this.loanRepo.findAllByStatus(ArgumentMatchers.anyString())).thenReturn(loanList);
	when(modelMapper.map(loan1,LoanDto.class)).thenReturn(loanDto1);
	when(modelMapper.map(loan2,LoanDto.class)).thenReturn(loanDto2);
	 assertThat(this.loanService.filterStatus(filter).size()==2);}
}
