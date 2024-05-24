package com.example.WebShoe.Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private int total;
	private List<ItemCart> listItemCart;
	public Cart() {
		super();
		listItemCart = new ArrayList<>();
	}
	public Cart(int total, List<ItemCart> listItemCart) {
		super();
		this.total = total;
		this.listItemCart = listItemCart;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<ItemCart> getListItemCart() { 
		return listItemCart;
	}
	
	public void setListItemCart(List<ItemCart> listItemCart) {
		this.listItemCart = listItemCart;
	}
	public void addItem(ItemCart itemCart) {
		listItemCart.add(itemCart);
	}
	public void updateItem(int idPD, int amount) {
		for(ItemCart item : listItemCart) {
			if(item.getpDetail().getId() == idPD) {
				item.setAmount(amount);
				break;
			}
		}
	}
	public void removeItem(int idPD) {
		listItemCart.removeIf(item -> item.getpDetail().getId()==idPD);
	}
	
	public boolean checkPD(int idPD) {
		for(ItemCart item : listItemCart) {
			if(item.getpDetail().getId() == idPD) return true;
		}
		return false;
	}
}
