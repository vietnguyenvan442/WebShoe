package com.example.WebShoe.Model;


public class ProductStat extends Product{
	private int total;
	private float revenue;
	public ProductStat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductStat(int total, float revenue) {
		super();
		this.total = total;
		this.revenue = revenue;
	}
	
	public ProductStat(int id, String name, int price, String imageMain, float sale, String imageGallery1,
			String imageGallery2, String imageGallery3, String des) {
		super(id, name, price, imageMain, sale, imageGallery1, imageGallery2, imageGallery3, des);
		// TODO Auto-generated constructor stub
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	
	
}
