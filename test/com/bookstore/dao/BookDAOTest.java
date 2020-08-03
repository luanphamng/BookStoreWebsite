package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

class BookDAOTest extends BaseDAOTest {
	private static BookDAO bookDAO;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDAO = new BookDAO(entityManger);
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreateBook() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Effective Java (3rd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "/home/luan/Dropbox/CodeJava/Spring/books/Effective Java.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		System.out.println("new book title: " + newBook.getTitle());
		Book createdBook = bookDAO.create(newBook);
		System.out.println("Author = " + newBook.getAuthor());
		assertTrue(createdBook.getBookId() > 0);

	}
	
	@Test
	void testCreateBook2() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		newBook.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		newBook.setDescription("The book covers lambdas, streams, and functional-style programming.");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "/home/luan/Dropbox/CodeJava/Spring/books/Effective Java.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		System.out.println("new book title: " + newBook.getTitle());
		Book createdBook = bookDAO.create(newBook);
		System.out.println("Author = " + newBook.getAuthor());
		assertTrue(createdBook.getBookId() > 0);

	}
	
	@Test
	void testCreateBook3() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Spring in Action: Covers Spring 4");
		newBook.setAuthor("Craig Walls");
		newBook.setDescription("Spring in Action, Fourth Edition is a hands-on guide to the Spring Framework, updated for version 4. It covers the latest features, tools, and practices including Spring MVC, REST, Security, Web Flow, and more");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "/home/luan/Dropbox/CodeJava/Spring/books/Effective Java.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		System.out.println("new book title: " + newBook.getTitle());
		Book createdBook = bookDAO.create(newBook);
		System.out.println("Author = " + newBook.getAuthor());
		assertTrue(createdBook.getBookId() > 0);

	}
	
	@Test
	void testCreateBook4() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Java EE 7: The Big Picture (Programming & Web Dev - OMG)");
		newBook.setAuthor("Dr. Danny Coward");
		newBook.setDescription("The Definitive Guide to Java Platform, Enterprise Edition 7");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "/home/luan/Dropbox/CodeJava/Spring/books/Effective Java.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		System.out.println("new book title: " + newBook.getTitle());
		Book createdBook = bookDAO.create(newBook);
		System.out.println("Author = " + newBook.getAuthor());
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	void testCreateBook5() throws IOException, ParseException {
		Book newBook = new Book();

		Category category = new Category("Advanced Java");
		category.setCategoryId(12);
		newBook.setCategory(category);

		newBook.setTitle("Head First Java, 2nd Edition");
		newBook.setAuthor("Kathy Sierra, Bert Bates");
		newBook.setDescription("Learning a complex new language is no easy task especially when it s an object-oriented computer programming language like Java.");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "/home/luan/Dropbox/CodeJava/Spring/books/Effective Java.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		System.out.println("new book title: " + newBook.getTitle());
		Book createdBook = bookDAO.create(newBook);
		System.out.println("Author = " + newBook.getAuthor());
		assertTrue(createdBook.getBookId() > 0);
	}
	
	
	@Test
	void testDeleteBook() {
		bookDAO.delete(8);
		assertTrue(true);
	}
	
	// Test get book fail
	@Test
	public void testGetBookFail() {
		Book getbook = bookDAO.get(4000);
		assertFalse(getbook != null);
	}
	
	// Test get book done
	@Test
	public void testGetBookSuccess() {
		Book getbook = bookDAO.get(6);
		assertTrue(getbook != null);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBook = bookDAO.listAll();
		assertFalse(listBook.isEmpty());
		
	}
	
	@Test
	public void testFindByTitle() {
		Book book = bookDAO.findByTitle("Java");
		if(book != null) {
			System.out.println(book.getTitle());			
		}
		assertTrue(book != null);
	}
	
	@Test
	public void testCount() {
		long count = bookDAO.count();
		assertTrue(count == 6);
	}

}
