package com.banking.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.banking.user.entity.User;
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepo;

	@Test
	@Order(1)
	public void saveUserTest() {
		User user=new User(1,"harry","harry321","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
	userRepo.save(user);
	assertThat(user.getId()).isGreaterThan(0);
	}
	@Test
	@Order(2)
	public void findByEmailTest() {
		String ControlEmail="harryt46@google.com";
		User fetchedUser=userRepo.findByEmail(ControlEmail);
		assertThat(fetchedUser.getName()=="harry");
	}
	@Test
	@Order(3)
	public void findByIdTest() {
		int ControlId=1;
		Optional<User> fetchedUser=userRepo.findById(ControlId);
		assertThat(!fetchedUser.isEmpty());
	}
	@Test
	@Order(4)
	public void findByUsernameTest() {
		String ControlUsername="harry321";
		User fetchedUser=userRepo.findByUsername(ControlUsername);
		assertThat(fetchedUser.getUsername()==ControlUsername);
	}
	@Test
	@Order(5)
	public void findAllTest() {
		User user2=new User(2,"harry2","harry123","Harry@67890","newyork","california","USA","harryt46@google.com","5d9hru875r","8473026493","06/08/2000","savings","ROLE_USER");
		userRepo.save(user2);
		List<User> userList=userRepo.findAll();
		assertThat(userList.size()!=0);
	}
	
	//check order maintain check transactional
}
