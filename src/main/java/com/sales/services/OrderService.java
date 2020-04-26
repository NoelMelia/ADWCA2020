package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.repositories.CustomerRepository;
import com.sales.repositories.OrderRepository;
import com.sales.repositories.ProductRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository or;
	@Autowired
	ProductRepository pr;
	@Autowired
	CustomerRepository cr;
	private Product product;
	private Customer customer;

	public ArrayList<Order> findAll() {
		return (ArrayList<Order>) or.findAll();
	}

	public void save(Order order) {

		
		or.save(order);
	}// if else if
}// save
