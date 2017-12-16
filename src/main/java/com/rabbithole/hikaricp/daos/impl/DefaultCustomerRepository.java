package com.rabbithole.hikaricp.daos.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rabbithole.hikaricp.daos.CustomerRepository;
import com.rabbithole.hikaricp.pojos.Customer;

import lombok.Getter;
import lombok.Setter;

@Repository
public class DefaultCustomerRepository implements CustomerRepository {
	
	private static final String FIND_ALL_QUERY = "SELECT id, name, email, created_date FROM customers";
	private static final String ADD_NEW_QUERY = "INSERT INTO customers(name, email, created_date) VALUES (?,?,?)";
	
	/**
	 * Spring Boot will register a JdbcTemplate bean automatically, so we can injects it via @Autowired
	 */
	@Autowired(required = true)
	@Getter
	@Setter
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Customer> findAllCustomers() {

		// Thanks Java 8, you can create a custom RowMapper
		return getJdbcTemplate().query(FIND_ALL_QUERY,
				(rs, rowNum) -> Customer.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.email(rs.getString("email"))
						.date(rs.getDate("created_date"))
						.build());
	}
	
	@Override
	public int addCustomer(final String name, final String email) throws DataAccessException {

		return getJdbcTemplate().update(ADD_NEW_QUERY, name, email, new Date());
	}
	
}
