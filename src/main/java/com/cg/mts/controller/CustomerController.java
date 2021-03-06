package com.cg.mts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.mts.entities.Customer;
import com.cg.mts.service.ICustomerService;

/* This would signify we are in customer controller */
@RequestMapping("/customer")
/*
 * This annotation defines a class as a controller in spring boot. Rest
 * controller consists of @Controller and @ResponseBody annotation
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	/*
	 * ICustomerService is a interface, whose reference we are trying to
	 * call. @Autowired will dynamically create a object for it at run time,
	 * avoiding tight coupling
	 */
	@Autowired
	private ICustomerService customerService;

	/*
	 * This method will add a customer object to the customer table. and return the
	 * customer object added back to the server
	 */
	@PostMapping("/add")
	public Customer add(@RequestBody Customer customer) {
		customer = customerService.insertCustomer(customer);
		return customer;
	}

	/*
	 * This method will update a customer object whose id matches with the one we
	 * are passing. Once it updates, it will return the updated object back to the
	 * server
	 */
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable("id") int customerId, @RequestBody Customer newCustomer) {
		newCustomer.setCustomerId(customerId);
		newCustomer = customerService.updateCustomer(newCustomer);
		return newCustomer;
	}

	/*
	 * This method will delete a customer object from the database table, which we
	 * have passed in the method
	 */
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int customerId) {
		Customer customer = customerService.viewCustomer(customerId);
		customer = customerService.deleteCustomer(customer);
	}

	/*
	 * This method will return a customer object whose id matches with the one we
	 * have passed in the url
	 */
	@GetMapping("/retrieve/{id}")
	public Customer getCustomer(@PathVariable("id") int id) {
		Customer customer = customerService.viewCustomer(id);
		return customer;
	}

	/* This method will return list of customers available in the database */
	@GetMapping("/retrieve/allCustomers")
	public List<Customer> getAllCustomers() {
		List<Customer> listOfAllCustomer = customerService.viewCustomers();
		return listOfAllCustomer;
	}

	/*
	 * This method will return customer object whose username and password matches
	 * with the one we would pass in the url
	 */
	@GetMapping("/retrieve/validateCustomer/{username}/{password}")
	public Customer validateCustomer(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		Customer customer = customerService.validateCustomer(username, password);
		return customer;
	}
}
