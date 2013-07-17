package com.chronosystems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chronosystems.dao.CustomerDAO;
import com.chronosystems.entity.Customer;
import com.chronosystems.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public void save(final Customer device) {
		customerDAO.save(device);
	}
	
	@Override
	public void delete(Customer entity) {
		customerDAO.delete(entity);
	}

	@Override
	public Customer findById(Long id) {
		return customerDAO.findById(id);
	}
	
	@Override
	public List<Customer> findAll() {
		return customerDAO.findAll();
	}
}