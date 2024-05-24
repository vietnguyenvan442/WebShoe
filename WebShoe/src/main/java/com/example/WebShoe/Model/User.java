package com.example.WebShoe.Model;

import java.sql.Date;

public class User {
	private int id;
	private String name;
	private Date dob;
	private String phone;
	private String email;
	private String password;
	private String position;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String name, Date dob, String phone, String email, String password, String position) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.position = position;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
