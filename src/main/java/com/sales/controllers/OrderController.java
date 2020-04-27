package com.sales.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.services.CustomerService;
import com.sales.services.OrderService;
import com.sales.services.ProductService;

@Controller
@SessionAttributes("orders")
public class OrderController {
	@Autowired
	OrderService os;

	@Autowired
	CustomerService cs;

	@Autowired
	ProductService ps;

	private Product prod;
	private Customer cust;
	public DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public LocalDate localDate = LocalDate.now();

	@RequestMapping(value = "/showOrders.html")
	public String showOrders(Model model) {
		System.out.println("In Show Order Controller");
		ArrayList<Order> orders = os.findAll();
		model.addAttribute("orders", orders);
		return "showOrders";
	}
	
	@RequestMapping(value = "/addOrder.html", method = RequestMethod.GET)
	public String addOrdersGET(Model model) {
		ArrayList<Customer> cust = cs.findAll();
		ArrayList<Product> prod = ps.findAll();

		Map<Long, String> custNames = new LinkedHashMap<Long, String>();
		for (Customer c : cust) {
			custNames.put(c.getcId(), c.getcName());

			model.addAttribute("custNames", custNames);
		}

		Map<Long, String> prodNames = new LinkedHashMap<Long, String>();
		for (Product p : prod) {
			prodNames.put(p.getpId(), p.getpDesc());

			model.addAttribute("prodNames", prodNames);
		}

		Order o = new Order();

		model.addAttribute("orders", o);
		return "addOrder";
	}

	@RequestMapping(value = "/addOrder.html", method = RequestMethod.POST)
	public String addOrdersPOST(Model model,@Valid @ModelAttribute("orders") Order order, BindingResult br) {
		if (!br.hasErrors()) {
			Long piD = Long.parseLong(order.getProd().getpDesc());
			Long ciD = Long.parseLong(order.getCust().getcName());
			System.out.println("In Add Order " + piD);
			System.out.println("Order in Post");
			prod = ps.findOne(piD);
			cust = cs.findOne(ciD);
			order.setOrderDate(date.format(localDate));
			order.setProd(prod);
			order.setCust(cust);

			if (prod.getQtyInStock() < order.getQty()) {
				System.out.println("Quantity too large: Product stock = " + prod.getQtyInStock());
				return "errorPage";
			}
			if (prod == null || cust == null) {
				System.out.print("No such Product: " + prod.getpId());
				System.out.println("No such Customer: " + cust.getcId());
				return "errorPage2";
			}
			prod.setQtyInStock(prod.getQtyInStock() - (order.getQty()));

			os.save(order);

		} else {
			ArrayList<Customer> cust = cs.findAll();
			ArrayList<Product> prod = ps.findAll();

			Map<Long, String> custNames = new LinkedHashMap<Long, String>();
			for (Customer c : cust) {
				custNames.put(c.getcId(), c.getcName());

				model.addAttribute("custNames", custNames);
			}

			Map<Long, String> prodNames = new LinkedHashMap<Long, String>();
			for (Product p : prod) {
				prodNames.put(p.getpId(), p.getpDesc());

				model.addAttribute("prodNames", prodNames);
			}

			

			

			
			return "addOrder";
		}
		return "redirect:showOrders.html";
	}
}