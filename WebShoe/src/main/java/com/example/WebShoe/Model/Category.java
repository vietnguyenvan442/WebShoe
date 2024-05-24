package com.example.WebShoe.Model;

public class Category {
	private int id;
	private String sex;
	private String type;
	private String des;
	public Category() {
		super();
	}
	public Category(int id, String sex, String type, String des) {
		super();
		this.id = id;
		this.sex = sex;
		this.type = type;
		this.des = des;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
