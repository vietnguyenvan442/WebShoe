package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.WebShoe.Model.Customer;

public class CustomerDAO extends DAO{
	private static final String SELECT_CUS_ID = "select id from tblCustomer where name = ? and phone = ? limit 1";
	private static final String CHECK_CUS = "select count(*) from tblCustomer where name = ? and phone = ?";
	private static final String INSERT_CUS = "insert into tblCustomer(name, phone) values (?, ?)";
	
	
	public CustomerDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean checkCus(Customer cus) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(CHECK_CUS)){
			ps.setString(1, cus.getName());
			ps.setNString(2,  cus.getPhone());
			ResultSet rs = ps.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getIdCus(Customer cus) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_CUS_ID)){
			ps.setString(1, cus.getName());
			ps.setString(2, cus.getPhone());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cus.setId(rs.getInt("id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cus.getId();
	}
	
	public void addCus(Customer cus) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_CUS)){
			ps.setString(1, cus.getName());
			ps.setNString(2,  cus.getPhone());
			int rs = 0;
			rs = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
