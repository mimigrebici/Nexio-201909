package com.catalogs.productcatalog.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 	
	        auth.inMemoryAuthentication()
	                .withUser("user1").password("{noop}pass").roles("USER")
	                .and()
	                .withUser("user2").password("{noop}pass").roles("USER")
	                .and()
	                .withUser("user3").password("{noop}pass").roles("USER")
	                .and()
	                .withUser("user4").password("{noop}pass").roles("USER");

	    }
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		 	// BAsic authorizations
	        http.httpBasic().and().authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/**").hasRole("USER")
	                .antMatchers(HttpMethod.POST, "/**").hasRole("USER")
	                .and()
	                .csrf().disable()
	                .formLogin()
	                .and().logout();
	    }
}
