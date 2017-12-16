package com.rabbithole.hikaricp.daos;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.rabbithole.hikaricp.pojos.Customer;

public interface CustomerRepository {

	/**
	 * Find all customers
	 *
	 * @return list of {@link Customer}
	 */
	List<Customer> findAllCustomers();
	
	/**
	 * Add new customer
	 *
	 * @param name
	 * @param email
	 *
	 * @return number of rows affected
	 */
	int addCustomer(String name, String email) throws DataAccessException;
	
}
