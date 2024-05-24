package com.example.WebShoe.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.ProductDAO;
import com.example.WebShoe.Model.Product;

@RestController
@CrossOrigin
public class CollectionsController {
	private ProductDAO pDAO = new ProductDAO();
	
	@GetMapping("/collections")
	public List<Product> getProducts(){
		List<Product> products = pDAO.getProducts();
		return products;
	}
	@GetMapping("/search/{key}")
	public List<Product> getSearch(@PathVariable String key){
		List<Product> products = pDAO.getProduct_Search(key);
		return products;
	}
	@GetMapping("/collections/{cate}")
	public List<Product> getCollections(@PathVariable String cate){
		List<Product> products = pDAO.getProducts_Collections(cate.toLowerCase());
		return products;
	}
}
