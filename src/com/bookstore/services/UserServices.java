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

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");

		if (userDAO.checkEmailExist(email)) {
			String message = "Could not create user because this email is already taken!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {
			response.getWriter().println("Email: " + email);
			response.getWriter().println("FullName: " + fullName);
			response.getWriter().println("Password: " + password);

			Users newUser = new Users(email, fullName, password);
			userDAO.create(newUser);
		}
	}

	public void editUser() throws IOException, ServletException {

		int userID = (Integer.parseInt(request.getParameter("id")));
		Users user = new Users();
		user = userDAO.findUser(userID);
		if(user != null) {
			request.setAttribute("user", user);
			String editUserPage = "user_form.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(editUserPage);
			requestDispatcher.forward(request, response);
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			request.setAttribute("message", "Can not load this user for delete");
			requestDispatcher.forward(request, response);
		}
	}

	public void updateUser() throws IOException, ServletException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");

		Users userById = userDAO.get(userId);

		if ((userById != null) && (email != userById.getEmail())) {
			if(!userDAO.checkEmailExist(email)) {
				userById.setEmail(email);
				userById.setFullName(fullName);
				userDAO.update(userById);
				listUsers("Update user successfully!");
				return;					
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
				request.setAttribute("message", "Can not complete this action, email is taken!");
				requestDispatcher.forward(request, response);
			}

		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			request.setAttribute("message", "Can not update, please check correct user or change other email");
			requestDispatcher.forward(request, response);
		}
	}
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users userWillBeDeleted = userDAO.get(userId);
		if(userWillBeDeleted != null) {
			userDAO.delete(userWillBeDeleted.getUserId());
			listUsers("Delete user successfull");			
		}
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			request.setAttribute("message", "User can not be found!");
			requestDispatcher.forward(request, response);
		}
	}
}
