package com.bookstore.dao;

import com.bookstore.entity.Users;
import java.util.List;

import javax.persistence.EntityManager;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
	}
		
	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public Users create(Users user) {
		return super.create(user);
	}
	
	public boolean checkEmailExist(String email) {
		List<Users> userSameEmail = super.findWithNameQuery("Users.countByEmail", email);
		return (userSameEmail != null);
	}
	
	@Override
	public Users update(Users t) {
		return super.update(t);
	}

	@Override
	public Users delete(Object id) {
		// TODO Auto-generated method stub
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
