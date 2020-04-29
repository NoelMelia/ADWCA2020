package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sales.models.Product;
import com.sales.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository pr;
	//Finding all the products
	public ArrayList<Product> findAll() {
		return (ArrayList<Product>) pr.findAll();
	}
	//Saving the product
	public void save(Product c) {
		pr.save(c);
		
	}
	//Finfing one product
	public Product findOne(Long long1)
	{	
		Product p = pr.findOne(long1);
		
		if(p == null)
		{
			return null;
		}
		return p;

	}
	
}
