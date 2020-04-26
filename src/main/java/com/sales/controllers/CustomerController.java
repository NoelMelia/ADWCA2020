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
	@Autowired
	CustomerService cs;	
	
	@RequestMapping(value = "/showCustomers.html")
	public String showCustomers(Model model) {
		System.out.println("In Controller");
		ArrayList<Customer> customers = cs.findAll();
		model.addAttribute("customers", customers);
		return "showCustomers";
	}
	
	@RequestMapping(value = "/addCustomer.html")
	public String addCustomerGET(Model model) {
		ArrayList<Customer> cust = cs.findAll();

		Map<Long, String> custNames = new LinkedHashMap<Long, String>();
		for (Customer c : cust) {
			custNames.put(c.getcId(), c.getcName());
		}
		model.addAttribute("custNames", custNames);
		Customer c = new Customer();
		model.addAttribute("customers", c);
		return "addCustomer";
	}

	@RequestMapping(value="/addCustomer.html",method=RequestMethod.POST)
	public String addCustomerPOST(@Valid @ModelAttribute("customers") Customer c, BindingResult result) {
		if(result.hasErrors())
		{
			return "addCustomer";
		}
		cs.save(c);
		return "redirect:showCustomers.html";
	}
}
