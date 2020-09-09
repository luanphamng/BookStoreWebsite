package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Customer;

class CustomerDAOTest {
	private static CustomerDAO customerDAO;
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreateCustomer() {
		Customer newCustomer = new Customer();
		newCustomer.setFullname("Liam");
		newCustomer.setEmail("liam@liam.vn");
		newCustomer.setAddress("K82 NLB");
		newCustomer.setCity("Da Nang");
		newCustomer.setCountry("Viet Nam");
		newCustomer.setPhone("0935646192");
		newCustomer.setZipcode("550000");
		newCustomer.setPassword("mysecret");
		newCustomer.setRegisterDate(new Date());
		Customer savedCustomer = customerDAO.create(newCustomer);
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

}
