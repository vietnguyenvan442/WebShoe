package com.example.WebShoe.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.CategoryDAO;
import com.example.WebShoe.Model.Category;

@RestController
@CrossOrigin
public class HeaderController {
	private CategoryDAO cDAO = new CategoryDAO();
	@GetMapping("/header")
	public List<Category> getCategory(){
		List<Category> categorys = cDAO.getCategoyrs();
		return categorys;
	}
}
