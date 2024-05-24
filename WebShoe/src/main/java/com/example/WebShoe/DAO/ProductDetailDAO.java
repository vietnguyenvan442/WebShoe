package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.Product;
import com.example.WebShoe.Model.ProductDetail;

public class ProductDetailDAO extends DAO{
	private static final String SELECT_ALL_PRODUCTDETAILS = "select * from tblProductDetail where Product_id = ?";
	private static final String UPDATE_PRODUCTDETAILS = "update tblProductDetail set quantity = ? where id = ?";
	
	public List<ProductDetail> getProductDetailByID(int pID){
		List<ProductDetail> pDetails = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTDETAILS);){
			ps.setInt(1, pID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int quantity = rs.getInt("quantity");
				String size = rs.getString("size");
				pDetails.add(new ProductDetail(id, quantity, size));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pDetails;
	}
	
	public void updatePD(int quantity, int idPD) {
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_PRODUCTDETAILS);){
			int rs = 0;
			ps.setInt(1, quantity);
			ps.setInt(2, idPD);
			rs = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProductDetail> selectProductDetails(int id){
		List<ProductDetail> productDetails = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pr = con.prepareStatement("select tblproductdetail.id as id, tblproductdetail.quantity as quantity, "
						+ "tblproductdetail.size as size, tblproduct.name as name, product_id "
						+ "from tblproductdetail, tblproduct where product_id = tblproduct.id and tblproduct.id = ?");){
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				ProductDetail u = new ProductDetail();
				u.setId(rs.getInt("id"));
				u.setProduct(new Product()); 
				u.getProduct().setName(rs.getString("name"));
				u.getProduct().setId(rs.getInt("product_id"));;
				u.setQuantity(rs.getInt("quantity"));
				u.setSize(rs.getString("size"));
				productDetails.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetails;
	}
	
	public ProductDetail selectProDetailByID(int id) {
		ProductDetail u = new ProductDetail();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from tblproductdetail where id = ?");){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.setId(id);
				u.setProduct(new Product()); u.getProduct().setId(rs.getInt("product_id"));;
				u.setQuantity(rs.getInt("quantity"));
				u.setSize(rs.getString("size"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public void insertProductDetail(ProductDetail pro) {
		try (Connection con=getConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO `tblproductdetail` (`quantity`, `size`, `Product_id`) VALUES (?, ?, ?)")){
			ps.setInt(1, pro.getQuantity());
			ps.setString(2, pro.getSize());
			ps.setInt(3, pro.getProduct().getId());
			int rs=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateProDetail(ProductDetail pro) {
		try(Connection conn = getConnection(); 
				PreparedStatement ps = conn.prepareStatement("update tblproductdetail set quantity=?, size=? where id=?");){
			int result = 0;
			ps.setInt(1, pro.getQuantity());
			ps.setString(2, pro.getSize());
			ps.setInt(3, pro.getId());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteProDetail(int id) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("delete from tblproductdetail where id = ?")){
			ps.setInt(1, id);
			int result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteProDetails(int id) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("delete from tblproductdetail where product_id = ?")){
			ps.setInt(1, id);
			int result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkSizeQuantity(ProductDetail pro) {
		try(Connection conn = getConnection(); 
				PreparedStatement ps = conn.prepareStatement("select * from tblproductdetail where size=? and quantity=?");){
			ps.setString(1, pro.getSize());
			ps.setInt(2, pro.getQuantity());
			ResultSet rs = ps.executeQuery();
			if(rs.next()&&rs.getInt(1)>0) return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
