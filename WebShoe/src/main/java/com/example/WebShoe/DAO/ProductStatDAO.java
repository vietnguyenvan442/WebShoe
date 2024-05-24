package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.Category;
import com.example.WebShoe.Model.ProductStat;


public class ProductStatDAO extends DAO{

	public ProductStatDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<ProductStat> selectProStats(Date st, Date et){
		List<ProductStat> proStat = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select tblproduct.id as id, tblproduct.name as name, tblcategory.name as category, "
						+ "sum(tblitembill.amount) as total, sum(tblitembill.price) + sum(distinct tblbill.fee) as revenue "
						+ "from tblbill, tblitembill, tblproduct, tblproductdetail, tblcategory "
						+ "where tblitembill.bill_id = tblBill.id and tblitembill.productdetail_id = tblproductdetail.id and tblproduct.id = tblproductdetail.product_id "
						+ "and tblcategory.id = tblproduct.Category_id and (tblbill.paymentTime >= ? and tblbill.paymenttime <= ?) "
						+ "group by tblproduct.id")){
			ps.setDate(1, st);
			ps.setDate(2, et);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductStat p = new ProductStat();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setCategory(new Category());
				String[] nameC = rs.getString("category").split("-");
				p.getCategory().setSex(nameC[0]);
				p.getCategory().setType(nameC[1]);
				p.setTotal(rs.getInt("total"));
				p.setRevenue(rs.getFloat("revenue"));
				proStat.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proStat;
	}
}
