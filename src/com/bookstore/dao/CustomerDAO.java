package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	public CustomerDAO() {
	}
	
	public CustomerDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Customer create(Customer t) {
		return super.create(t);
	}

	@Override
	public Customer update(Customer t) {
		return super.update(t);
	}

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		return super.countAllRows("Customer.countAll");
	}
	
	public List<Customer> findByEmail(String email){
		return super.findWithNameQuery("Customer.findByEmail", email);
	}
	
	public boolean checkEmailExist(String email) {
		List<Customer> customerSameEmail = super.findWithNameQuery("Customer.findByEmail", email);
		return (customerSameEmail.size() != 0);
	}

}
