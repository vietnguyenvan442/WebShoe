package com.example.WebShoe.Model;

public class ItemCart {
	private int amount;
	private ProductDetail pDetail;
	public ItemCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemCart(int amount, ProductDetail pDetail) {
		super();
		this.amount = amount;
		this.pDetail = pDetail;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public ProductDetail getpDetail() {
		return pDetail;
	}
	public void setpDetail(ProductDetail pDetail) {
		this.pDetail = pDetail;
	}
	
}
