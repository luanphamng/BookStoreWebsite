package com.bookstore.controller.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.services.UserServices;

@WebServlet("/admin/create_user")
public class CreateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateUsersServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices userServices = new UserServices(request, response);
		userServices.createUser();
		String message = "Create user successfully!";
		userServices.listUsers(message);
	}

}
