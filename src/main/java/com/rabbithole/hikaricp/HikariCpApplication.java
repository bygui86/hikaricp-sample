package com.rabbithole.hikaricp;

import static java.lang.System.exit;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbithole.hikaricp.daos.CustomerRepository;
import com.rabbithole.hikaricp.pojos.Customer;
import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;

/**
 * Spring Boot Console (or CommandLineRunner) Application
 *
 * The run method accepts arguments to perform either “display” or “insert” function.
 */
@SpringBootApplication
public class HikariCpApplication implements CommandLineRunner {

	private final static Logger LOG = LoggerFactory.getLogger(HikariCpApplication.class);

	@Autowired
	@Getter
	private DataSource dataSource;
	
	@Autowired
	@Getter
	private CustomerRepository customerRepository;
	
	public static void main(final String[] args) throws Exception {

		SpringApplication.run(HikariCpApplication.class, args);
	}
	
	@Override
	public void run(final String... args) throws Exception {

		checkArgs(args);

		checkHikariCpUsage();

		switch (args[0]) {

			case "insert":
				addNewCustomer(args);
				exit(0);
				
			case "display":
				showAllCustomers();
				exit(0);

			default:
				printAppUsage(args);
				exit(-1);
		}
	}
	
	private void addNewCustomer(final String[] args) {

		final String name = args[1];
		final String email = args[2];
		LOG.info("Adding new customer [" + name + ", " + email + "]...");
		final int rowsAffected = getCustomerRepository().addCustomer(name, email);
		if (rowsAffected == 1) {
			LOG.info("Customer [" + name + ", " + email + "] added");
		} else {
			System.err.println("Something went wrong: Customer [" + name + ", " + email + "] not added");
		}
		LOG.info("");
		
		LOG.info("Exiting the application...");
		LOG.info("");
	}
	
	private void showAllCustomers() {
		
		LOG.info("Display all customers...");
		final List<Customer> list = customerRepository.findAllCustomers();
		list.forEach(
				x -> LOG.info(x.toString()));
		LOG.info("");

		LOG.info("Exiting the application...");
		LOG.info("");
	}
	
	private void checkHikariCpUsage() {

		if (getDataSource() instanceof HikariDataSource) {
			// Using HikariCP
			final HikariDataSource hikariDs = (HikariDataSource) getDataSource();
			LOG.info("Data source: " + hikariDs);
			LOG.info("Connection timeout: " + hikariDs.getConnectionTimeout());
			LOG.info("Maximum pool size: " + hikariDs.getMaximumPoolSize());
			LOG.info("");
		} else {
			// Not using HikariCP, Something went wrong...
			LOG.error("Something went wrong: not using HikariCP as Data source");
			LOG.error("Current Data source: " + getDataSource());
			LOG.error("");
			exit(-1);
		}
	}

	private void checkArgs(final String[] args) {

		if (args.length <= 0) {
			printAppUsage(args);
			exit(-1);
		}
	}
	
	private void printAppUsage(final String[] args) {
		
		LOG.error("");
		LOG.error("[RabbitHole.HikariCP.Usage] RabbitHole-HikariCP usage:");
		LOG.error("    . display             >  Show all customers");
		LOG.error("    . insert name email   >  Insert new customer with name and email");
		LOG.error("");
	}
	
}
