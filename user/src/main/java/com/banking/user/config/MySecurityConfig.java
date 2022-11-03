package com.banking.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.banking.user.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	@Autowired
private CustomUserDetailService myUserDetailService;
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.csrf() 
		.disable()
		.authorizeRequests()
		.antMatchers("/home/admin/**").hasRole("ADMIN")
		.antMatchers("/home/user/**").hasRole("USER")
		.antMatchers("/home/auth","/home/register","/home/test")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().logout().logoutUrl("home/Logout").permitAll();
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

@Bean
public DaoAuthenticationProvider daoAuthenticationProvider() {
DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
provider.setUserDetailsService(this.myUserDetailService);
provider.setPasswordEncoder(passwordEncoder());
return provider;
}


@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}


}
