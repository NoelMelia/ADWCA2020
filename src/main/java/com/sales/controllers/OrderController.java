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

	private Product product;
	private Customer customer;
	// How i got the date and store it
	public DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public LocalDate localDate = LocalDate.now();

	// Method to show all details. Same as customer page but gets the order details
	// from database
	@RequestMapping(value = "/showOrders.html")
	public String showOrders(Model model) {
		System.out.println("In Show Order Controller");
		ArrayList<Order> orders = os.findAll();
		model.addAttribute("orders", orders);
		return "showOrders";
	}// Then returns the showOrder page

	// Add order method to get all the details for the page
	@RequestMapping(value = "/addOrder.html", method = RequestMethod.GET)
	public String addOrdersGET(Model model) {
		// Putting all the details to 2 different lists to store
		ArrayList<Customer> cust = cs.findAll();
		ArrayList<Product> prod = ps.findAll();

		// Mapping the details of the customer to a new map to make a drop down list
		Map<Long, String> custNames = new LinkedHashMap<Long, String>();
		for (Customer c : cust) {
			custNames.put(c.getcId(), c.getcName());

			model.addAttribute("custNames", custNames);
		}

		// Same for the product
		Map<Long, String> prodNames = new LinkedHashMap<Long, String>();
		for (Product p : prod) {
			prodNames.put(p.getpId(), p.getpDesc());

			model.addAttribute("prodNames", prodNames);
		}

		Order o = new Order();
		// Making a new order and mapping to a new map
		model.addAttribute("orders", o);
		return "addOrder";
	}// Returning the same page to check

	// Posting the details of order
	@RequestMapping(value = "/addOrder.html", method = RequestMethod.POST)
	public String addOrdersPOST(Model model, @Valid @ModelAttribute("orders") Order order, BindingResult br) {
		// Checking to see if the errors dont affect the entry. If not the proceed
		ArrayList<Customer> cust = cs.findAll();
		ArrayList<Product> prod = ps.findAll();
		if (br.hasErrors()) {
			// If there is nothing in the entry field of the qty
			// Getting all the details of the drop down box

			// This worked for me to re find all the details of the 2
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

			return "addOrder";// Return to the same page with all the details
		}
		// Getting the Order details of the Customer and product and converting to a
		// Long entry
		Long piD = Long.parseLong(order.getProd().getpDesc());
		Long ciD = Long.parseLong(order.getCust().getcName());
		System.out.println("In Add Order " + piD);
		System.out.println("Order in Post");
		// Finding the details of producta and customer of one
		product = ps.findOne(piD);
		customer = cs.findOne(ciD);

		// Setting all the details to put in database
		order.setOrderDate(date.format(localDate));
		order.setProd(product);
		order.setCust(customer);

		// error handling to make sure thatm the qty is not less then in stock
		if (product.getQtyInStock() < order.getQty()) {
			System.out.println("Quantity too large: Product stock = " + product.getQtyInStock());
			return "errorPage";
		}
		// Making sure the customer or product exist
		if (product == null) {
			System.out.print("No such Product: " + product.getpId());

			return "errorPage2";
		}
		if (cust == null) {
			System.out.println("No such Customer: " + customer.getcId());
			return "errorPage2";
		}
		// Taking away the qty from in stock
		product.setQtyInStock(product.getQtyInStock() - (order.getQty()));
		// Saving the deatils to new order
		try {
			// Block of code to try
			os.save(order);
		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");

			// Block of code to handle errors
			return "errorPage2";
		}

		return "redirect:showOrders.html";
		// If there is no errors in page then store in order
	}
}