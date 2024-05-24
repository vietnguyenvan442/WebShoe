package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.User;

public class UserDAO extends DAO{
	private static final String SELECT_USER = "select * from tblUser where email = ? and password = ?";
	private static final String CHECK_USER = "select count(*) from tblUser where email = ?";
	private static final String ADD_USER = "insert into tblUser(name, dob, phone, email, password, position) values(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "update tblUser set password=? where id = ?";
	
	
	public User checkLogin(User u) {
		User user = new User();
		boolean check = true;
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_USER);){
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				check = false;
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setDob(rs.getDate("dob"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPosition(rs.getString("position"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(check) return null;
		return user;
	}
	
	public boolean checkLogup(User u) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(CHECK_USER);){
			ps.setString(1, u.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next()&& rs.getInt(1) > 0) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addUser(User u) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(ADD_USER);){
			int rs = 0;
			ps.setString(1, u.getName());
			ps.setDate(2, u.getDob());
			ps.setString(3, u.getPhone());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getPassword());
			ps.setString(6, "User");
			rs = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateUser(User u) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_USER);){
			int rs = 0;
			ps.setString(1, u.getPassword());
			ps.setInt(2, u.getId());
			rs = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<User> selectUsers(){
		List<User> users = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pr = con.prepareStatement("SELECT * FROM tbluser");){
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setDob(rs.getDate("dob"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setPosition(rs.getString("position"));
				users.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User selectUserByID(int id) {
		User u = new User();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from tbluser where id = ?");){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.setId(id);
				u.setName(rs.getString("name"));
				u.setDob(rs.getDate("dob"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setPosition(rs.getString("position"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public void insertUser(User user) {
		try (Connection con=getConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO `tbluser` (`name`, `dob`, `phone`, `email`, `password`, `position`) VALUES (?, ?, ?, ?, ?, ?)")){
			ps.setString(1, user.getName());
			ps.setDate(2, user.getDob());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.setString(6, "admin");
			int rs=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AdminUpdateUser(User user) {
		try(Connection conn = getConnection(); 
				PreparedStatement ps = conn.prepareStatement("update tbluser set name=?,dob=?,phone=?,password=?,position=? where id=?");){
			int result = 0;
			ps.setString(1, user.getName());
			ps.setDate(2, user.getDob());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getPosition());
			ps.setInt(6, user.getId());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteUser(int id) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("delete * from tblUser where id = ?")){
			ps.setInt(1, id);
			int result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkEmail(User user) {
		try(Connection conn = getConnection(); 
				PreparedStatement ps = conn.prepareStatement("select * from tbluser where email=?");){
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next()&&rs.getInt(1)>0) return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
