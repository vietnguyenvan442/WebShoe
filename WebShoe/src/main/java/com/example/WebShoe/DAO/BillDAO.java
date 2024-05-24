package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.WebShoe.Model.Bill;
import com.example.WebShoe.Model.Customer;
import com.example.WebShoe.Model.User;

public class BillDAO extends DAO{
	private static final String SELECT_BILL_ID = "select id from tblBill order by id desc limit 1";
	private static final String INSERT_BILL_U = "insert into tblBill(fee, paymentTime, address, User_id) values(?, ?, ?, ?)";
	private static final String INSERT_BILL_C = "insert into tblBill(fee, paymentTime, address, Customer_id) values(?, ?, ?, ?)";
	private static final String SELECT_BILL_BY_U = "select tblbill.paymentTime, tblbill.id from tblbill, tbluser where tblbill.User_id = tbluser.id and tbluser.id = ?";
	private static final String SELECT_BILL_BY_C = "select tblbill.paymentTime, tblbill.id from tblbill, tblcustomer where tblbill.Customer_id = tblcustomer.id and tblcustomer.phone = ?";
	
	public BillDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Bill> getBillbyC(String phone) {
		List<Bill> bills = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BILL_BY_C)){
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setPaymentTime(rs.getDate("paymentTime"));
				bills.add(bill);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return bills;
	}
	
	public List<Bill> getBillbyU(int idU) {
		List<Bill> bills = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BILL_BY_U)){
			ps.setInt(1, idU);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setPaymentTime(rs.getDate("paymentTime"));
				bills.add(bill);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return bills;
	}

	public void addBillU(Bill bill) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_BILL_U)){
			int rs = 0;
			ps.setInt(1, bill.getFee());
			ps.setDate(2, bill.getPaymentTime());
			ps.setString(3, bill.getAddress());
			ps.setInt(4, bill.getU().getId());
			rs = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addBillC(int idC ,Bill bill) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_BILL_C)){
			int rs = 0;
			ps.setInt(1, bill.getFee());
			ps.setDate(2, bill.getPaymentTime());
			ps.setString(3, bill.getAddress());
			ps.setInt(4, idC);
			rs = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getIdBill() {
		int id = 0;
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BILL_ID)){
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}	
	
	public List<Bill> selectBillsByDay(Date day){
		List<Bill> bills = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps1 = con.prepareStatement("select tblbill.id as id, tbluser.name as name, sum(tblitembill.amount) as total, sum(tblitembill.price) + sum(distinct tblbill.fee) as revenue, tblbill.paymentTime as day "
						+ "from tblbill, tblitembill, tbluser "
						+ "where tblbill.id = tblitembill.bill_id and tblbill.user_id = tbluser.id and tblbill.paymentTime = ? "
						+ "group by tblbill.id");
				PreparedStatement ps2 = con.prepareStatement("select tblbill.id as id, tblcustomer.name as name, sum(tblitembill.amount) as total, sum(tblitembill.price) + sum(distinct tblbill.fee) as revenue, tblbill.paymentTime as day "
						+ "from tblbill, tblitembill, tblcustomer "
						+ "where tblbill.id = tblitembill.bill_id and tblbill.customer_id = tblcustomer.id and tblbill.paymentTime = ? "
						+ "group by tblbill.id");){
			ps1.setDate(1, day);
			ps2.setDate(1, day);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Bill b = new Bill();
				b.setId(rs1.getInt("id"));
				b.setU(new User()); b.getU().setName(rs1.getString("name"));
				b.setTotalAmount(rs1.getInt("total"));
				b.setTotalPrice(rs1.getInt("revenue"));
				b.setPaymentTime(rs1.getDate("day"));
				bills.add(b);
			}
			
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Bill b = new Bill();
				b.setId(rs2.getInt("id"));
				b.setCus(new Customer()); b.getCus().setName(rs2.getString("name"));
				b.setTotalAmount(rs2.getInt("total"));
				b.setTotalPrice(rs2.getInt("revenue"));
				b.setPaymentTime(rs2.getDate("day"));
				bills.add(b);
			}
			
			Collections.sort(bills, new Comparator<Bill>() {

				@Override
				public int compare(Bill o1, Bill o2) {
					return o2.getPaymentTime().compareTo(o1.getPaymentTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bills;
	}
	
	public List<Bill> selectBillsByProduct(int idp, Date st, Date et){
		List<Bill> bills = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps1 = con.prepareStatement("select tblbill.id as id, tbluser.name as name, sum(tblitembill.amount) as total, sum(tblitembill.price) + sum(distinct tblbill.fee) as revenue, tblbill.paymentTime as day "
						+ "from tblbill, tblitembill, tbluser , tblproduct, tblproductdetail "
						+ "where tblbill.id = tblitembill.bill_id and tblbill.user_id = tbluser.id and tblbill.paymenttime >= ? and tblbill.paymenttime <= ? and tblproduct.id = ?	 and tblproduct.id = tblproductdetail.product_id and tblproductdetail.id = tblitembill.productdetail_id "
						+ "group by tblbill.id");
				PreparedStatement ps2 = con.prepareStatement("select tblbill.id as id, tblcustomer.name as name, sum(tblitembill.amount) as total, sum(tblitembill.price) + sum(distinct tblbill.fee) as revenue, tblbill.paymentTime as day "
						+ "from tblbill, tblitembill, tblcustomer, tblproduct, tblproductdetail "
						+ "where tblbill.id = tblitembill.bill_id and tblbill.customer_id = tblcustomer.id and tblbill.paymenttime >= ? and tblbill.paymenttime <= ? and tblproduct.id = ? and tblproduct.id = tblproductdetail.product_id and tblproductdetail.id = tblitembill.productdetail_id "
						+ "group by tblbill.id");){
			ps1.setDate(1, st);
			ps1.setDate(2, et);
			ps1.setInt(3, idp);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Bill b = new Bill();
				b.setId(rs1.getInt("id"));
				b.setU(new User()); b.getU().setName(rs1.getString("name"));
				b.setTotalAmount(rs1.getInt("total"));
				b.setTotalPrice(rs1.getInt("revenue"));
				b.setPaymentTime(rs1.getDate("day"));
				bills.add(b);
			}
			ps1.close(); rs1.close();
			
			ps2.setDate(1, st);
			ps2.setDate(2, et);
			ps2.setInt(3, idp);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Bill b = new Bill();
				b.setId(rs2.getInt("id"));
				b.setCus(new Customer()); b.getCus().setName(rs2.getString("name"));
				b.setTotalAmount(rs2.getInt("total"));
				b.setTotalPrice(rs2.getInt("revenue"));
				b.setPaymentTime(rs2.getDate("day"));
				bills.add(b);
			}
			
			Collections.sort(bills, new Comparator<Bill>() {

				@Override
				public int compare(Bill o1, Bill o2) {
					return o2.getPaymentTime().compareTo(o1.getPaymentTime());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bills;
	}
}
