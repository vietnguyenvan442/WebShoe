package com.example.WebShoe.Controller;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.Model.Bill;
import com.example.WebShoe.DAO.BillDAO;
import com.example.WebShoe.DAO.ItemBillDAO;

@RestController
@CrossOrigin
public class BillController {
	private List<Bill> bills = new ArrayList<>();
	private BillDAO bDAO = new BillDAO();
	private ItemBillDAO itemBillDAO = new ItemBillDAO();
	
	@PostMapping("/bill/add")
	public void addMenu(@RequestBody Bill bill) {
		if(bills.size()>0) {
			bill.setId((bills.get(bills.size()-1).getId()+1)%1000000);
		}else {
			bill.setId(1);
		}
		bills.add(bill);
	}
	
	@GetMapping("/bills")
	public List<Bill> getBill(){
		return bills;
	}
	
	@DeleteMapping("/bill/delete")
	public void deleteMenu(@RequestBody int idB) {
		for(Bill bill : bills) {
			if(bill.getId() == idB) {
				bills.removeIf(item -> item.getId() == idB);
				break;
			}
		}
	}
	
	@GetMapping("/bills/user/{idU}")
	public List<Bill> getBillsByU(@PathVariable int idU){
		List<Bill> billsByU= new ArrayList<>();
		for(Bill bill: bills) {
			if(bill.getU() != null) {
				if(bill.getU().getId() == idU) billsByU.add(bill);
			}
		}
		return billsByU;
	}
	
	@GetMapping("/bills/cus/{phone}")
	public List<Bill> getBillsByC(@PathVariable String phone){
		List<Bill> billsByC= new ArrayList<>();
		for(Bill bill: bills) {
			if(bill.getCus()!=null) {
				if(bill.getCus().getPhone().equals(phone)) billsByC.add(bill);
			}
		}
		return billsByC;
	}
	
	@GetMapping("/billed/user/{idU}")
	public List<Bill> getBilledU(@PathVariable int idU){
		List<Bill> bills = bDAO.getBillbyU(idU);
		for(Bill bill : bills) {
			bill.setListItemBill(itemBillDAO.getItemBillsByBillID(bill.getId()));
		}
		return bills;
	}
	
	@GetMapping("/billed/cus/{phone}")
	public List<Bill> getBilledC(@PathVariable String phone){
		List<Bill> bills = bDAO.getBillbyC(phone);
		for(Bill bill : bills) {
			bill.setListItemBill(itemBillDAO.getItemBillsByBillID(bill.getId()));
		}
		return bills;
	}
	
	@GetMapping("/admin/statByDay/bills/{day}")
	public List<Bill> getBillByDay(@PathVariable Date day){
		List<Bill> b = bDAO.selectBillsByDay(day);
		return b;
	}
	
	@GetMapping("/admin/statByPro/bills/{time}")
	public List<Bill> getBillByPro(@PathVariable String time){
		String[] t = time.split(",");
		int idp = Integer.parseInt(t[0]);
		Date st = Date.valueOf(t[1]);
		Date et = Date.valueOf(t[2]);
		List<Bill> b = bDAO.selectBillsByProduct(idp, st, et);
		return b;
	}
}
