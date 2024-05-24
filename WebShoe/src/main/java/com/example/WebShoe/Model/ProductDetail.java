package com.example.WebShoe.Model;

public class ProductDetail {
	private int id;
	private int quantity;
	private String size;
	private Product product;
	public ProductDetail() {
		super();
	}
	public ProductDetail(int id, int quantity, String size, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.size = size;
		this.product = product;
	}
	
	public ProductDetail(int id, int quantity, String size) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.size = size;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
