package com.example.WebShoe.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.CategoryDAO;
import com.example.WebShoe.DAO.ProductDAO;
import com.example.WebShoe.DAO.ProductDetailDAO;
import com.example.WebShoe.Model.Product;
import com.example.WebShoe.Model.ProductDetail;

@RestController
@CrossOrigin
public class ProductController {
	private ProductDetailDAO pDetailDAO = new ProductDetailDAO();
	private ProductDAO pDAO = new ProductDAO();
	private CategoryDAO categoryDAO = new CategoryDAO();
	
	@GetMapping("/size/{id}")
	public List<ProductDetail> getSize(@PathVariable int id){
		List<ProductDetail> pDetails = pDetailDAO.getProductDetailByID(id);
		return pDetails;
	}
	
	@GetMapping("/card/{id}")
	public List<Product> getCard(@PathVariable int id){
		List<Product> products = pDAO.getProductsCard(id);
		return products;
	}
	
	@GetMapping("/admin/products")
	public List<Product> getPros(){
		List<Product> pros = pDAO.selectPros();
		return pros;
	}
	
	@GetMapping("/admin/product/{id}")
	public Product getUser(@PathVariable int id) {
		Product p = pDAO.selectProByID(id);
		return p;
	}
	
	@PostMapping("/admin/product/save/{id}")
	public ResponseEntity<String> addOrUpdatePro(@PathVariable int id, @RequestBody Product pro) {
		boolean checkName = pDAO.checkName(pro.getName());
		boolean checkCategory = categoryDAO.checkCategory(pro.getCategory().getSex()+"-"+pro.getCategory().getType());
		
		if(id <= 0) {
			if(!checkCategory) {
				categoryDAO.insertCategory(pro.getCategory().getSex()+"-"+pro.getCategory().getType());
				pDAO.insertPro(pro);
				return ResponseEntity.ok("Save complete");
			}
			else if(!checkName) {
				pDAO.insertPro(pro);
				return ResponseEntity.ok("Save complete");
			}
			else {
				pDAO.updatePro(pro);
				return ResponseEntity.ok("Save complete");
			}
		}
		else {
			if(!checkCategory) {
				categoryDAO.insertCategory(pro.getCategory().getSex()+"-"+pro.getCategory().getType());
				pDAO.insertPro(pro);
				return ResponseEntity.ok("Save complete");
			}
			else {
				pDAO.updatePro(pro);
				return ResponseEntity.ok("Save complete");
			}
		}
	}
	@DeleteMapping("/admin/product/delete/{id}")
	public ResponseEntity<String> deletePro(@PathVariable int id) {
	    Product pro = pDAO.selectProByID(id);
	    if (pro != null) {
	    	pDetailDAO.DeleteProDetails(id);
	        pDAO.DeletePro(id);
	        return ResponseEntity.ok("Product deleted with ID: "+pro.getId());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
