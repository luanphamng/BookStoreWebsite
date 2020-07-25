package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest{
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManger);
	}

	@Test
	public void testCreateCategory() {
		Category cate1 = new Category();
		cate1.setName("Java");
		Category categoryRet = categoryDAO.create(cate1);
		assertTrue(categoryRet.getCategoryId() > 0);
	}

//	@Test
//	public void testGet() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testListAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCount() {
//		fail("Not yet implemented");
//	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
