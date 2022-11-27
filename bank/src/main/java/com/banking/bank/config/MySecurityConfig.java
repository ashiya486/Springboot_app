package com.banking.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@EnableWebSecurity
public class MySecurityConfig{
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 final String[] PUBLIC_URLS= {
				 "/v3/api-docs","/v2/api-docs","/swagger-resources/**",
					"/swagger-ui/**","/web-jars/**"};
		http
		.csrf() 
		.disable()
		.authorizeRequests()
		.antMatchers(PUBLIC_URLS)
		.permitAll()
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
