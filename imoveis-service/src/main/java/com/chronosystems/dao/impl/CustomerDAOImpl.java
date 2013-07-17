package com.chronosystems.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chronosystems.dao.CustomerDAO;
import com.chronosystems.entity.Customer;
import com.chronosystems.util.DaoSupport;

@Repository
public class CustomerDAOImpl extends DaoSupport implements CustomerDAO {

	@Override
	public void save(final Customer entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Customer entity) {
		getCurrentSession().delete(entity);
	}
	
	@Override
	public Customer findById(Long id) {
		return (Customer) getCurrentSession().get(Customer.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		return getCurrentSession().createQuery("from Customer").list();
	}
	
}
