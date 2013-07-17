package com.chronosystems.service;

import java.util.List;

import com.chronosystems.entity.Customer;

public interface CustomerService {

	void save(Customer entity);
	
	void delete(Customer entity);
	
	Customer findById(Long id);
	
	List<Customer> findAll();

}
