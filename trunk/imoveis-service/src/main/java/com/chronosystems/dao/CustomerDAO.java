package com.chronosystems.dao;

import java.util.List;

import com.chronosystems.entity.Customer;

public interface CustomerDAO {

	void save(Customer entity);
	
	void delete(Customer entity);
	
	Customer findById(Long id);
	
	List<Customer> findAll();
}
