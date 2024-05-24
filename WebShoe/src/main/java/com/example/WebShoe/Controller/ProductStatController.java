package com.example.WebShoe.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.ProductStatDAO;
import com.example.WebShoe.Model.ProductStat;


@RestController
@CrossOrigin
public class ProductStatController {
	private ProductStatDAO proStatDAO = new ProductStatDAO();
	
	@GetMapping("/admin/statByPro/{time}")
	public List<ProductStat> getStatByPro(@PathVariable String time){
		String[] t = time.split(",");
		Date st = Date.valueOf(t[0]);
		Date et = Date.valueOf(t[1]);
		List<ProductStat> proStat = proStatDAO.selectProStats(st, et);
		return proStat;
	}
}
