package com.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.services.HashGenerationException;
import com.bookstore.services.HashGeneratorUtils;

class HashGeneratorUtilsTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGenerateSHA256() {
		String hashGen = null;
		try {
			hashGen = HashGeneratorUtils.generateSHA256("mysecret");			
		} catch (HashGenerationException ex) {
			
		}
		System.out.println("Hash for mysecret = " + hashGen);
		assertTrue(hashGen != null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
}
