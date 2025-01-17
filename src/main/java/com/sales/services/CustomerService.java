package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository cr;

	//Same as the product service page
	public ArrayList<Customer> findAll() {
		return (ArrayList<Customer>) cr.findAll();
	}
	public void save(Customer c) {
		cr.save(c);
		
	}
	public Customer findOne(Long cId){
		return  cr.findOne(cId);
	}
}
