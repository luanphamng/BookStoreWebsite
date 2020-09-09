package com.bookstore.controller.admin.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Customer;
import com.bookstore.services.CustomerServices;

@WebServlet("/admin/edit_customer")
public class EditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerServices customerServices = new CustomerServices(request, response);
		Customer customer = customerServices.getRequestedCustomer();

		String page = "customer_form.jsp";
		request.setAttribute("customer", customer);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}
}
