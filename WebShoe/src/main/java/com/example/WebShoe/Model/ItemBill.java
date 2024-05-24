package com.example.WebShoe.Model;

public class ItemBill {
	private int id;
	private int amount;
	private int price;
	private int totalPrice;
	private ProductDetail pDetail;
	public ItemBill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemBill(int id, int amount, int totalPrice, ProductDetail pDetail) {
		super();
		this.id = id;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.pDetail = pDetail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public ProductDetail getpDetail() {
		return pDetail;
	}
	public void setpDetail(ProductDetail pDetail) {
		this.pDetail = pDetail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
