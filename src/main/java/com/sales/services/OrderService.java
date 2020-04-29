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
	//Finding all the deatils from repo
	public ArrayList<Order> findAll() {
		return (ArrayList<Order>) or.findAll();
	}
	//Saving the details from the repo
	public void save(Order order) {
		or.save(order);
	}
	
}
