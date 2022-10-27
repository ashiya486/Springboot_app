package com.banking.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.banking.user.service.CustomUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
private CustomUserDetailService myUserDetailService;
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf() 
		.disable()
		.authorizeRequests()
		.antMatchers("/home/admin/**").hasRole("ADMIN")
		.antMatchers("/home/user/**").hasRole("USER")
		.antMatchers("/home/auth")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(myUserDetailService);
	
	}

//	@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.inMemoryAuthentication()
//	.withUser("harry").password(passwordEncoder().encode("pass_h")).roles("ADMIN").and()
//	.withUser("rachel").password(passwordEncoder().encode("pass_r")).roles("USER");
//	
//	}
//@Bean
//public PasswordEncoder passwordEncoder() {
//	return NoOpPasswordEncoder.getInstance();
//	}
@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

@Override
@Bean
public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
}


}
