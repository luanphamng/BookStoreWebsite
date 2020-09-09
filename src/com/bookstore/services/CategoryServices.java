package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CategoryDAO categoryDAO;

	public CategoryServices() {
	}

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		categoryDAO = new CategoryDAO(entityManager);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategories = (List<Category>) categoryDAO.listAll();
		String listCategoryPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listCategoryPage);
		request.setAttribute("listCategory", listCategories);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public void create() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category categoryNew = new Category(name);
		categoryDAO.create(categoryNew);
		listCategory("Category was created successfull!");
	}

	public void editCategory() throws ServletException, IOException {
		Category category = load_category();
		request.setAttribute("category", category);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("category_form.jsp");
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		Category cateUpdate = load_category();
		String name = request.getParameter("name");
		cateUpdate.setName(name);
		categoryDAO.update(cateUpdate);
		listCategory("Update successfully!");
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category cateDetele = categoryDAO.get(categoryId);
		if (cateDetele == null) {
			redirect_to("message.jsp", "Can not load category id = " + categoryId);
			return;
		}
		categoryDAO.delete(cateDetele);
		listCategory("Delete successfully!");
	}

	public void redirect_to(String messagePage, String message) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public Category load_category() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Category category = categoryDAO.get(categoryId);
		if (category == null) {
			redirect_to("message.jsp", "Can not load category id = " + categoryId);
			return null;
		} else {
			return category;
		}
	}

	public List<Category> listAllCategory() {
		List<Category> listCategories = (List<Category>) categoryDAO.listAll();
		return listCategories;
	}
}
