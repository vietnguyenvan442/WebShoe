package com.example.WebShoe.Model;

public class Product {
	private int id;
	private String name;
	private int price;
	private String imageMain;
	private float sale;
	private String imageGallery1;
	private String imageGallery2;
	private String imageGallery3;
	private String des;
	private Category category;
	
	
	public Product() {
		super();
	}

	public Product(int id, String name, int price, String imageMain, float sale, String imageGallery1,
			String imageGallery2, String imageGallery3, String des) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageMain = imageMain;
		this.sale = sale;
		this.imageGallery1 = imageGallery1;
		this.imageGallery2 = imageGallery2;
		this.imageGallery3 = imageGallery3;
		this.des = des;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageMain() {
		return imageMain;
	}

	public void setImageMain(String imageMain) {
		this.imageMain = imageMain;
	}

	public float getSale() {
		return sale;
	}

	public void setSale(float sale) {
		this.sale = sale;
	}

	public String getImageGallery1() {
		return imageGallery1;
	}

	public void setImageGallery1(String imageGallery1) {
		this.imageGallery1 = imageGallery1;
	}

	public String getImageGallery2() {
		return imageGallery2;
	}

	public void setImageGallery2(String imageGallery2) {
		this.imageGallery2 = imageGallery2;
	}

	public String getImageGallery3() {
		return imageGallery3;
	}

	public void setImageGallery3(String imageGallery3) {
		this.imageGallery3 = imageGallery3;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
}
