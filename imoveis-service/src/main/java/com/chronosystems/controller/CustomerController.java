package com.chronosystems.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chronosystems.entity.Customer;
import com.chronosystems.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody
	String index() {
		return "Spring MVC " + new Date();
	}

	@RequestMapping("/index")
	public String index(final ModelMap model) {
		return "index";
	}

	@RequestMapping("/search")
	public String search(final ModelMap model) {
		final List<Customer> customers = customerService.findAll();
		model.addAttribute("customerList", customers);
		model.addAttribute("customer", new Customer());
		return "customer";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(final ModelMap model, final Customer customer) {
		customerService.save(customer);
		return search(model);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") final Long id, final ModelMap model) {
		final Customer customer = customerService.findById(id);
		customerService.delete(customer);
		return search(model);
	}
}