package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class UserDao<Users> extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDao(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	public Users create(Users user) {
		return this.create(user);
	}
	
	@Override
	public Users update(Users t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users get(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users delete(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


}
