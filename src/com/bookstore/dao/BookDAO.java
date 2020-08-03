package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.sql.Update;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
	}
	
	public BookDAO(EntityManager entityManager){
		super(entityManager);
	}
	
	public Book findByTitle(String title) {
		 List<Book> listBook = super.findWithNameQuery("Book.findByTitle", "title", title);
		 if(!listBook.isEmpty()) {
			 return listBook.get(0);
		 }
		 return null;
	}
	
	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
		
	}
	
	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}
	
	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	@Override
	public long count() {
		return super.countAllRows("Book.countAll");
	}
}
