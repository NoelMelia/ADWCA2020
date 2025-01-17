package com.sales.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sales.models.Customer;
import com.sales.models.Product;
import com.sales.services.CustomerService;

@Controller
@SessionAttributes("customers")
public class CustomerController {
	//Controller to direct and decide where each page will go and end up
	@Autowired
	CustomerService cs;	
	
	//Method to display all the Cuustomer details
	//Page is diercted to the service page to find all the customer details
	@RequestMapping(value = "/showCustomers.html")
	public String showCustomers(Model model) {
		//System.out.println("In Controller");
		ArrayList<Customer> customers = cs.findAll();
		model.addAttribute("customers", customers);
		return "showCustomers";
	}//Maps the details to a map and retuens the showcustomer page
	
	//Method to add a new Customer
	@RequestMapping(value = "/addCustomer.html")
	public String addCustomerGET(Model model) {
		ArrayList<Customer> cust = cs.findAll();
		//Firstly finds all the customers
		Map<Long, String> custNames = new LinkedHashMap<Long, String>();
		for (Customer c : cust) {
			custNames.put(c.getcId(), c.getcName());
		}
		// then maps them
		model.addAttribute("custNames", custNames);
		Customer c = new Customer();
		//then makes a new Customer to add
		model.addAttribute("customers", c);
		return "addCustomer";
		//Returns the addCustomer page
	}
	
	//Method tto post the details to the showCustomer page and store details in database
	@RequestMapping(value="/addCustomer.html",method=RequestMethod.POST)
	public String addCustomerPOST(@Valid @ModelAttribute("customers") Customer c, BindingResult result) {
		if(result.hasErrors())
		{//Make sure the entry field is not empty
			return "addCustomer";
		}
		cs.save(c);//Save the details
		return "redirect:showCustomers.html";//Redirect to the show customer page
	}
}
