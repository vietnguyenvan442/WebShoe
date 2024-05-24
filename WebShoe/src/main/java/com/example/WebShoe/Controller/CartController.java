package com.example.WebShoe.Controller;


import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.Cart;
import com.example.WebShoe.Model.ItemCart;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CartController {
	private Cart cart = new Cart();
	
	@PostMapping("/cart/add")
	public void addItemCart(@RequestBody ItemCart itemCart) {
		boolean check = false;
		if(cart.getListItemCart().size()==0) {
			cart.addItem(itemCart);
		}
		else {
			for(ItemCart iCart : cart.getListItemCart()) {
				if(iCart.getpDetail().getId() == itemCart.getpDetail().getId()) {
					cart.updateItem(itemCart.getpDetail().getId(), iCart.getAmount()+1);
					check = true;
					break;
				}
			}
			if(!check) cart.addItem(itemCart);
		}
	}
	
	@GetMapping("/cart")
	public List<ItemCart> getItemCart(){
		return cart.getListItemCart();
	}
	
	@DeleteMapping("cart/delete/{idPD}")
	public void deleteItemCart(@PathVariable int idPD) {
		if(cart.checkPD(idPD)) {
			cart.removeItem(idPD);
		}
	}
	
	@PostMapping("/cart/update")
	public void updateItemCart(@RequestBody ItemCart itemCart) {
		for(ItemCart iCart : cart.getListItemCart()) {
			if(iCart.getpDetail().getId() == itemCart.getpDetail().getId()) {
				cart.updateItem(itemCart.getpDetail().getId(), itemCart.getAmount());
				break;
			}
		}
	}
	@PostMapping("/cart/deletes")
	public void deleteCart() {
		cart.setListItemCart(new ArrayList<>());
	}
}

