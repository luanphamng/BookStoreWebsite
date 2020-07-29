package com.bookstore.dao;

import com.bookstore.entity.Users;
import com.bookstore.services.HashGenerationException;
import com.bookstore.services.HashGeneratorUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
	}

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public Users create(Users user) {
		try {
			String password = HashGeneratorUtils.generateSHA256(user.getPassword());
			user.setPassword(password);
		} catch (HashGenerationException ex) {
			ex.printStackTrace();
		}
		System.out.println("password hash SH256 = " + user.getPassword());
		return super.create(user);
	}

	public boolean checkEmailExist(String email) {
		List<Users> userSameEmail = super.findWithNameQuery("Users.findByEmail", email);
		return (userSameEmail.size() != 0);
	}

	public Users findUser(int id) {
		return super.find(Users.class, id);
	}

	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);
		try {
			password = HashGeneratorUtils.generateSHA256(password);			
		} catch (HashGenerationException ex) {
		}
		parameters.put("password", password);
		List<Users> usersList = findWithNameQuery("Users.checkLogin", parameters);
		return (usersList.size() == 1);
	}

	@Override
	public Users update(Users t) {
		return super.update(t);
	}

	@Override
	public Users delete(Object id) {
		Users uDelete = super.find(Users.class, id);
		super.destroy(uDelete);
		return null;
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countAllRows("Users.countAll");
	}

	@Override
	public Users get(Object id) {
		return super.find(Users.class, id);
	}
}
