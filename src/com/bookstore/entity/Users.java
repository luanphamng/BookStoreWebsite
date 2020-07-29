package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER by u.fullName"),
		@NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u"),
		@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email=:email"),
		@NamedQuery(name = "Users.findByID", query = "SELECT u from Users u WHERE u.userId=:id"),
		@NamedQuery(name = "Users.checkLogin", query = "SELECT u from Users u WHERE u.email = :email AND u.password = :password")
})

public class Users {

	public Users(String email, String password, String fullName) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public Users(int id, String email, String fullName) {
		super();
		this.userId = id;
		this.email = email;
		this.fullName = fullName;
	}
	
	public Users() {
		super();
	}

	private Integer userId;

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String email;
	private String password;
	private String fullName;
}
