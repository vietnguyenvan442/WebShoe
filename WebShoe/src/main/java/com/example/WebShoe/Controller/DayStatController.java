package com.example.WebShoe.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.DayStatDAO;
import com.example.WebShoe.Model.DayStat;

@RestController
@CrossOrigin
public class DayStatController {
	private DayStatDAO dsDAO = new DayStatDAO();
	
	@GetMapping("/admin/statByDay/{time}")
	public List<DayStat> getStatByDay(@PathVariable String time){
		String[] t = time.split(",");
		Date st = Date.valueOf(t[0]);
		Date et = Date.valueOf(t[1]);
		List<DayStat> ds = dsDAO.selectStatByDay(st, et);
		return ds;
	}
}
