package com.example.WebShoe.Model;

import java.sql.Date;

public class DayStat {
	private int amount;
	private int revenue;
	private Date day;
	public DayStat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DayStat(int amount, int revenue, Date day) {
		super();
		this.amount = amount;
		this.revenue = revenue;
		this.day = day;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
}
