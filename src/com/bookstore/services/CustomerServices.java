package com.bookstore.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDAO;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();

		customerDAO = new CustomerDAO(entityManager);
	}

	public void listCustomer() throws ServletException, IOException {
		List<Customer> listCustomers = customerDAO.listAll();
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_list.jsp");
		request.setAttribute("listCustomers", listCustomers);
		dispatcher.forward(request, response);
	}

	public void listCustomer(String message) throws ServletException, IOException {
		List<Customer> listCustomers = customerDAO.listAll();
		String page = "customer_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomers", listCustomers);
		dispatcher.forward(request, response);
	}

	public Customer getRequestedCustomer() throws ServletException, IOException {
		String returnMessage;
		int customerId = Integer.parseInt(request.getParameter("id"));
		Customer reqCustomer = customerDAO.get(customerId);

		if (reqCustomer == null) {
			returnMessage = "Can not find customer with id: " + customerId;
			listCustomer(returnMessage);
			return null;
		}
		return reqCustomer;
	}

	public void getCustomerInfo(Customer customer) {

		String email = (String) request.getParameter("email");
		customer.setEmail(email);

		String fullname = (String) request.getParameter("fullname");
		customer.setFullname(fullname);

		String address = (String) request.getParameter("address");
		customer.setAddress(address);

		String city = (String) request.getParameter("city");
		customer.setCity(city);

		String country = (String) request.getParameter("country");
		customer.setCountry(country);

		String phone = (String) request.getParameter("phone");
		customer.setPhone(phone);

		String zipcode = (String) request.getParameter("zipcode");
		customer.setZipcode(zipcode);

		String password = (String) request.getParameter("password");
		customer.setPassword(password);

	}

	public void create() throws ServletException, IOException {
		Customer newCustomer = new Customer();
		getCustomerInfo(newCustomer);
		newCustomer.setRegisterDate(new Date());

		String returnMessage;

		List<Customer> emailFound = customerDAO.findByEmail(newCustomer.getEmail());
		if (emailFound.isEmpty()) {
			Customer savedCustomer = customerDAO.create(newCustomer);
			if (savedCustomer.getCustomerId() > 0) {
				returnMessage = "Create customer successfull";
			} else {
				returnMessage = "Can not create customer";
			}

		} else {
			returnMessage = "The email exist, please try again with different email.";
		}
		listCustomer(returnMessage);
	}

	public void update() throws ServletException, IOException {
		String returnMessage;
		Customer requestedCustomer = getRequestedCustomer();
		getCustomerInfo(requestedCustomer);

		String requestedEmail = requestedCustomer.getEmail();
		int requestedId = requestedCustomer.getCustomerId();

		// If email not exist on database
		if (customerDAO.checkEmailExist(requestedEmail)) {
			returnMessage = "You entered an email that exits, please try again with different email!";
			listCustomer(returnMessage);
			return;
		}

		customerDAO.update(requestedCustomer);
		returnMessage = "Update customer successfull!";
		listCustomer(returnMessage);

	}

	public void delete() throws ServletException, IOException {
		String returnMessage;
		Customer requestedCustomer = getRequestedCustomer();
		int customerId = requestedCustomer.getCustomerId();
		customerDAO.delete(customerId);
		returnMessage = "Delete customerId: " + customerId + " successfull!";
		listCustomer(returnMessage);
	}
}