package com.banking.bank;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
	@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
}
	@Bean
	public InMemoryUserDetailsManager userDetailsService(){
		UserDetails user=User.withDefaultPasswordEncoder()
		.username("username")
		.password("password")
		.roles("ADMIN")
		.build();
	return new InMemoryUserDetailsManager(user);
		}
}
