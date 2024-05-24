package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.Category;

public class CategoryDAO extends DAO{
	private static final String SELECT_ALL_CATEGORYS = "select * from tblCategory";
	public List<Category> getCategoyrs(){
		List<Category> categorys = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_CATEGORYS);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String des = rs.getString("des");
				String ds[] = name.split("-");
				String sex = ds[0];
				String type = ds[1].substring(0, 1).toUpperCase()+ds[1].substring(1);
				categorys.add(new Category(id, sex, type, des));
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return categorys;
	}
	
	public void insertCategory(String name) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("insert into tblcategory(name) values (?)");){
			ps.setString(1, name);
			int rs = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkCategory(String name) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from tblcategory where name=?");){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
