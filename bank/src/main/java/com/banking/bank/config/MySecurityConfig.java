package com.banking.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class MySecurityConfig{
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.csrf() 
		.disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

	}
//@Bean
//public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
//	return configuration.getAuthenticationManager();
//}



}
