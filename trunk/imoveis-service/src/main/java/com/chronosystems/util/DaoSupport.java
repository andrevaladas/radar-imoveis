package com.chronosystems.util;

import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Andre Valadas
 */
public abstract class DaoSupport {
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;

	static {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected void flush() {
		getCurrentSession().flush();
	}
}