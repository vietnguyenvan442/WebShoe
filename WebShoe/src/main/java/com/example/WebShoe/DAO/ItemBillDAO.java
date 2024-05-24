package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.Product;
import com.example.WebShoe.Model.ItemBill;
import com.example.WebShoe.Model.ProductDetail;

public class ItemBillDAO extends DAO{
	private static final String INSERT_ITEMBILL = "insert into tblItemBill(amount, price, ProductDetail_id, Bill_id) values (?, ?, ?, ?)";
	private static final String SELECT_ITEMBILL_BY_BILLID = "select tblproduct.imageMain, tblproduct.name, tblproductdetail.size, tblitembill.amount, tblproduct.price \r\n"
			+ "from tblitembill, tblproductdetail, tblproduct\r\n"
			+ "where tblitembill.Bill_id = ? and tblitembill.ProductDetail_id = tblproductdetail.id and tblproductdetail.Product_id = tblproduct.id";
	
	
	public ItemBillDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

    public List<ItemBill> getItemBillsByBillID(int idB){
    	List<ItemBill> itemBills = new ArrayList<>();
    	try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ITEMBILL_BY_BILLID)){
    		ps.setInt(1, idB);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			ItemBill itemBill = new ItemBill();
    			itemBill.setAmount(rs.getInt("amount"));
    			ProductDetail pDetail = new ProductDetail();
    			pDetail.setSize(rs.getString("size"));
    			Product product = new Product();
    			product.setImageMain(rs.getString("imageMain"));
    			product.setName(rs.getString("name"));
    			product.setPrice(rs.getInt("price"));
    			pDetail.setProduct(product);
    			itemBill.setpDetail(pDetail);
    			itemBills.add(itemBill);
    		}
    	}catch (SQLException e) {
			e.printStackTrace();
		}
    	return itemBills;
    }
	
	public void addItemBill(int idB, ItemBill itemBill) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_ITEMBILL)){
			int rs = 0;
			ps.setInt(1, itemBill.getAmount());
			ps.setInt(2, itemBill.getPrice());
			ps.setInt(3, itemBill.getpDetail().getId());
			ps.setInt(4, idB);
			rs = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
