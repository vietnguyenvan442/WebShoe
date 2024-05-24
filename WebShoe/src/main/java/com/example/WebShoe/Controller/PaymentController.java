package com.example.WebShoe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.BillDAO;
import com.example.WebShoe.DAO.CustomerDAO;
import com.example.WebShoe.DAO.ItemBillDAO;
import com.example.WebShoe.DAO.ProductDetailDAO;
import com.example.WebShoe.Model.Bill;
import com.example.WebShoe.Model.ItemBill;

@RestController
@CrossOrigin
public class PaymentController {
	private CustomerDAO cDAO = new CustomerDAO();
	private BillDAO bDAO = new BillDAO();
	private ItemBillDAO iBDAO =  new ItemBillDAO();
	private ProductDetailDAO pDDAO = new ProductDetailDAO();
	
	@PostMapping("/addbill")
	public ResponseEntity<String> addBill(@RequestBody Bill bill) {
		if(bill.getCus()==null) {
			bDAO.addBillU(bill);
		}else {
			if(cDAO.checkCus(bill.getCus())) {
				bDAO.addBillC(cDAO.getIdCus(bill.getCus()), bill);
			}else {
				cDAO.addCus(bill.getCus());
				bDAO.addBillC(cDAO.getIdCus(bill.getCus()), bill);
			}
		}
		int idB = bDAO.getIdBill();
		for(ItemBill item : bill.getListItemBill()) {
			iBDAO.addItemBill(idB, item);
			pDDAO.updatePD(item.getpDetail().getQuantity()-item.getAmount(), item.getpDetail().getId());
		}
		return ResponseEntity.ok("addBill complete");
	}
}
