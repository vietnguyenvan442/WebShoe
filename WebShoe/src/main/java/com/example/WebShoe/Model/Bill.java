package com.example.WebShoe.Model;

import java.sql.Date;
import java.util.List;

public class Bill {
	private int id;
	private int fee;
	private Date paymentTime;
	private String address;
	private int totalAmount;
	private int totalPrice;
	private Customer cus;
	private User u;
	private List<ItemBill> listItemBill;
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bill(int id, int fee, Date paymentTime, String address, int totalAmount, int totalPrice, Customer cus,
			User u, List<ItemBill> listItemBill) {
		super();
		this.id = id;
		this.fee = fee;
		this.paymentTime = paymentTime;
		this.address = address;
		this.totalAmount = totalAmount;
		this.totalPrice = totalPrice;
		this.cus = cus;
		this.u = u;
		this.listItemBill = listItemBill;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public List<ItemBill> getListItemBill() {
		return listItemBill;
	}
	public void setListItemBill(List<ItemBill> listItemBill) {
		this.listItemBill = listItemBill;
	}
}
