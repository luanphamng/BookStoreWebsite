package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManger;

	public BaseDAOTest() {
	}

	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityManagerFactory.createEntityManager();
	}

	public static void tearDownAfterClass() throws Exception {
		entityManger.close();
		entityManagerFactory.close();
	}
	
}
