package com.bookstore.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		bookDAO = new BookDAO(entityManager);
		categoryDAO = new CategoryDAO(entityManager);
	}

	public void listBooks() throws IOException, ServletException {
		List<Book> listAllBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listAllBooks);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_list.jsp");
		requestDispatcher.forward(request, response);
	}

	public void listBooks(String message) throws IOException, ServletException {
		List<Book> listAllBooks = bookDAO.listAll();
		if (!message.isEmpty()) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listBooks", listAllBooks);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_list.jsp");
		requestDispatcher.forward(request, response);
	}
	public void newBookForm() throws ServletException, IOException {
		String page = "book_form.jsp";
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);

	}

	public void requestBookParameter(Book b) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryId);

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		System.out.println("title, author = " + title + author);
		String isbn = request.getParameter("isbn");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;

		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("Error parsing publish date");
		}
		b.setCategory(category);
		b.setTitle(title);
		b.setAuthor(author);
		b.setIsbn(isbn);
		b.setPublishDate(publishDate);
		b.setDescription(description);
		b.setPrice(price);

		// Get image
		Part part = request.getPart("bookImage");

		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			b.setImage(imageBytes);
		}
	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title");
		Book existBook = bookDAO.findByTitle(title);

		if (existBook != null) {
			String message = "Can not create. This book is alreay exists.";
			listBooks(message);
			return;
		}

		Book newBook = new Book();
		requestBookParameter(newBook);

		Book createBook = bookDAO.create(newBook);

		if (createBook.getBookId() > 0) {
			String message = "Create book successfully!";
			listBooks(message);
		}
	}

	public void editBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		String page = "book_form.jsp";
		Book bookNeedEdit = bookDAO.get(bookId);
		if (bookNeedEdit != null) {
			request.setAttribute("book", bookNeedEdit);
			List<Category> listCategory = categoryDAO.listAll();
			request.setAttribute("listCategory", listCategory);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
			requestDispatcher.forward(request, response);
		} else {

		}
	}

	public void updateBook() throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book bookNeedUpdate = bookDAO.get(bookId);
		if (bookNeedUpdate != null) {
			requestBookParameter(bookNeedUpdate);
			Book bookTocheckTitle = bookDAO.findByTitle(bookNeedUpdate.getTitle());
			if (bookTocheckTitle != null) {
				if (bookTocheckTitle.getBookId() == bookNeedUpdate.getBookId()) {
					bookDAO.update(bookNeedUpdate);
					String message = "Your book with Title: " + bookNeedUpdate.getTitle() + " was updated successfully";
					listBooks(message);
					return;
				} else {
					String message = "A book with Title: " + bookNeedUpdate.getTitle() + " exsited, can not udpate.";
					listBooks(message);
					return;
				}
			}
			bookDAO.update(bookNeedUpdate);
			String message = "Your book with Title: " + bookNeedUpdate.getTitle() + " was updated successfully";
			bookDAO.update(bookNeedUpdate);
			listBooks(message);
		} else {
			String message = "Your book with ID: " + bookId + " request can not be found";
			listBooks(message);
		}
	}
	
	public void deleteBook() throws IOException, ServletException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(bookId);
		String message = "Delete book successfully!";
		listBooks(message);
	}
}
