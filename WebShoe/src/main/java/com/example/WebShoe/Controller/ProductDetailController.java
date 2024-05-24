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

import com.example.WebShoe.DAO.ProductDetailDAO;
import com.example.WebShoe.Model.ProductDetail;


@RestController
@CrossOrigin
public class ProductDetailController {
	private ProductDetailDAO proDetailDAO = new ProductDetailDAO();
	
	@GetMapping("/admin/product/{idp}/sizes")
	public List<ProductDetail> getSizePro(@PathVariable int idp){
		List<ProductDetail> p = proDetailDAO.selectProductDetails(idp);
		return p;
	}
	
	@GetMapping("/admin/product/{idp}/size/{id}")
	public ProductDetail getProDetail(@PathVariable int id) {
		ProductDetail u = proDetailDAO.selectProDetailByID(id);
		return u;
	}
	
	@PostMapping("/admin/size/save/{id}")
	public ResponseEntity<String> addOrUpdateUser(@PathVariable int id, @RequestBody ProductDetail pro) {
	    if(!proDetailDAO.checkSizeQuantity(pro)) {
	    	if (pro.getId() <= 0) { 
		    	proDetailDAO.insertProductDetail(pro);
		    } else {
		        proDetailDAO.updateProDetail(pro);
		    }
	    	return ResponseEntity.ok("Save complete");
	    }
	    return ResponseEntity.ok("Duplicate");
	}
	
	@DeleteMapping("/admin/size/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
	    ProductDetail pro = proDetailDAO.selectProDetailByID(id);
	    if (pro != null) {
	        proDetailDAO.DeleteProDetail(id);
	        return ResponseEntity.ok("size deleted with ID: "+pro.getId());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
