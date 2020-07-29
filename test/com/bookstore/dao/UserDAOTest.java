package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManger;

	@BeforeClass
	public static void setUpClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManger);
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("john3@gmail.com");
		user1.setFullName("John Smith 3");
		user1.setPassword("johnny3");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 22;
		Users user = userDAO.get(userId);

		if (user != null) {
			System.out.println(user.getEmail());
		}

		assertNotNull(user);
	}
	

	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);

		assertNull(user);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		// Change user ID first;
		user.setUserId(24);
		user.setEmail("luan.sys@gmail.com");
		user.setFullName("Pham Ngoc Luan");
		user.setPassword("mysecret");

		user = userDAO.update(user);
		String expected = "mysecret";
		String actual = user.getPassword();

		assertEquals(expected, actual);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		assertTrue(listUsers.size() > 0);
	}
	
//	@Test
//	public void testCount() {
//		long count = userDAO.count();
//		System.out.println("Count all Users = " + count);
//		assertEquals(count, 11);
//	}
	
	@Test
	public void testCheckLogin() {
		String email = "luan.sys@gmail.com";
		String password = "mysecret";
		
		assertTrue(userDAO.checkLogin(email, password));
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.close();
	}
	
}
