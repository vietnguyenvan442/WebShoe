package com.example.WebShoe.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.ProductDAO;
import com.example.WebShoe.Model.Product;

@RestController
@CrossOrigin
public class IndexController {
	private ProductDAO pDAO = new ProductDAO();
	
	@GetMapping("/new")
	public List<Product> getNew(){
		List<Product> products = pDAO.getProductsNew();
		return products;
	}
}
