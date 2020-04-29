package com.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//www.concretepage.com/spring-security-in-memory-authentication
	//I couldn't get your way to work so i got this from this website
	@Override
	public void configure(HttpSecurity http) throws Exception{
		 http
		    .authorizeRequests()
		    //Asking for authority if this pages are selected 
		    .antMatchers("/addOrder.html","/addProduct.html","/addCustomer.html","/showProducts.html","/showCustomers.html","/showOrders.html")
		    .authenticated()
		    .and()
		    .formLogin()
		    .and()
		    //Logout details to be thrown if this link is selected
		    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	//creating the user and password
	@Autowired
	public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("user").password("user").roles("USER");
	}
}