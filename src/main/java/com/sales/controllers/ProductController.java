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

import com.sales.models.Product;
import com.sales.services.ProductService;

@Controller
@SessionAttributes("product")
public class ProductController {
	@Autowired
	ProductService ps;

	//Similar to the Customer but its the Product Controller
	@RequestMapping(value = "/showProducts.html")
	public String showProducts(Model model) {
		System.out.println("In Controller");
		ArrayList<Product> product = ps.findAll();
		model.addAttribute("product", product);
		return "showProducts";
	}

	//Adding the product. Getting the details for the page
	@RequestMapping(value = "/addProduct.html", method = RequestMethod.GET)
	public String addProductGET(Model model) {
		
		ArrayList<Product> prod = ps.findAll();

		Map<Long, String> prodNames = new LinkedHashMap<Long, String>();
		for (Product p : prod) {
			prodNames.put(p.getpId(), p.getpDesc());
		}
		model.addAttribute("prodNames", prodNames);
		Product c = new Product();
		model.addAttribute("product", c);
		return "addProduct";
	}

	//Checking to see if the page has no errors and then posting the new product 
	@RequestMapping(value="/addProduct.html",method=RequestMethod.POST)
	public String addCoursePOST(@Valid @ModelAttribute("product") Product p, BindingResult result) {
		if(result.hasErrors())
		{
			return "addProduct";
		}
		ps.save(p);
		return "redirect:showProducts.html";
	}

}
