package com.bookstore.services;

import java.io.IOException;
import java.net.HttpRetryException;
import java.rmi.ServerException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private UserDAO userDAO;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);
	}

	public void listUsers(String message) throws ServletException, IOException {

		List<Users> listUsers = (List<Users>) userDAO.listAll();

		request.setAttribute("listUsers", listUsers);
		if (message != null)
			request.setAttribute("message", message);
		String userListPage = "user_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(userListPage);
		dispatcher.forward(request, response);
	}

	public void listUsers() throws ServletException, IOException {

		List<Users> listUsers = (List<Users>) userDAO.listAll();

		request.setAttribute("listUsers", listUsers);
		String userListPage = "user_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(userListPage);
		dispatcher.forward(request, response);
	}

	public void createUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		if (userDAO.checkEmailExist(email)) {
			String message = "Could not create user because this email is already taken!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			response.getWriter().println("Email: " + email);
			response.getWriter().println("FullName: " + fullName);
			response.getWriter().println("Password: " + password);
			
			Users newUser = new Users(email, fullName, password);
			userDAO.create(newUser);			
		}
	}
}
