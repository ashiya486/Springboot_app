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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.banking.user.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class MySecurityConfig {
	@Autowired
private CustomUserDetailService myUserDetailService;
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 final String[] PUBLIC_URLS= {
				"/home/auth","/home/register","/v3/api-docs","/v2/api-docs","/swagger-resources/**",
				"/swagger-ui/**","/web-jars/**"};
		http
		.csrf() 
		.disable()
		.authorizeRequests()
		.antMatchers("/home/admin/**").hasRole("ADMIN")
		.antMatchers("/home/user/**").hasRole("USER")
		.antMatchers(PUBLIC_URLS)
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
