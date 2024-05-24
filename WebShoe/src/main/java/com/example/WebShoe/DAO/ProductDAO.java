package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.Category;
import com.example.WebShoe.Model.Product;

public class ProductDAO extends DAO{
	private static final String SELECT_ALL_PRODUCTS = "select * from tblProduct";
	private static final String SELECT_ALL_PRODUCTS_SEARCH = "select * from tblProduct where lower(name) like ?";
	private static final String SELECT_ALL_PRODUCTS_COLLECTIONS1 = "select * from tblProduct, tblCategory where tblproduct.Category_id = tblcategory.id and tblcategory.name like ?";
	private static final String SELECT_ALL_PRODUCTS_COLLECTIONS2 = "select * from tblProduct where sale > 0";
	private static final String SELECT_ALL_PRODUCTS_CARD = "select * from tblProduct where Category_id = (select Category_id from tblproduct where id = ?) order by id desc limit 5";
	private static final String SELECT_ALL_PRODUCTS_NEW = "select * from tblProduct order by id desc limit 10";
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String imageMain = rs.getNString("imageMain");
				float sale = rs.getFloat("sale");
				String imageGallery[] = rs.getNString("imageGallery").split(",");
				String des = rs.getNString("des");
				products.add(new Product(id, name, price, imageMain, sale, imageGallery[0], imageGallery[1], imageGallery[2], des));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public List<Product> getProduct_Search(String key) {
		List<Product> products = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_SEARCH);){
			ps.setString(1, "%"+key+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String imageMain = rs.getNString("imageMain");
				float sale = rs.getFloat("sale");
				String imageGallery[] = rs.getNString("imageGallery").split(",");
				String des = rs.getNString("des");
				products.add(new Product(id, name, price, imageMain, sale, imageGallery[0], imageGallery[1], imageGallery[2], des));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public List<Product> getProducts_Collections(String cate){
		List<Product> products = new ArrayList<>();
		try(Connection conn = getConnection(); ){
			PreparedStatement ps = null;
			if(cate.equals("sale")) {
				ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_COLLECTIONS2);
			}
			else {
				ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_COLLECTIONS1);
				ps.setString(1, "%" + cate + "%");
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String imageMain = rs.getNString("imageMain");
				float sale = rs.getFloat("sale");
				String imageGallery[] = rs.getNString("imageGallery").split(",");
				String des = rs.getNString("des");
				products.add(new Product(id, name, price, imageMain, sale, imageGallery[0], imageGallery[1], imageGallery[2], des));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public List<Product> getProductsCard(int pID){
		List<Product> products = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_CARD);){
			ps.setInt(1, pID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String imageMain = rs.getNString("imageMain");
				float sale = rs.getFloat("sale");
				String imageGallery[] = rs.getNString("imageGallery").split(",");
				String des = rs.getNString("des");
				products.add(new Product(id, name, price, imageMain, sale, imageGallery[0], imageGallery[1], imageGallery[2], des));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	public List<Product> getProductsNew(){
		List<Product> products = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_NEW);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String imageMain = rs.getNString("imageMain");
				float sale = rs.getFloat("sale");
				String imageGallery[] = rs.getNString("imageGallery").split(",");
				String des = rs.getNString("des");
				products.add(new Product(id, name, price, imageMain, sale, imageGallery[0], imageGallery[1], imageGallery[2], des));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Product> selectPros(){
		List<Product> products = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pr = con.prepareStatement("select tblproduct.id as id, tblproduct.name as name, tblproduct.price as price, tblproduct.imageMain as imageMain, tblproduct.imageGallery as imageGallery, tblproduct.sale as sale, tblproduct.des as des, "
						+ "tblcategory.id as idc, tblcategory.name as nameC "
						+ "from tblproduct, tblcategory "
						+ "where tblproduct.Category_id = tblcategory.id;");){
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setImageMain(rs.getString("imageMain"));
				String img[] = rs.getString("imageGallery").trim().split(",");
				p.setImageGallery1(img[0]);
				p.setImageGallery2(img[1]);
				p.setImageGallery3(img[2]);
				p.setSale(rs.getFloat("sale"));
				p.setDes(rs.getString("des"));
				p.setCategory(new Category());
				p.getCategory().setId(rs.getInt("idc"));
				String[] nameC = rs.getString("nameC").split("-");
				p.getCategory().setSex(nameC[0]);
				p.getCategory().setType(nameC[1]);
				products.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Product selectProByID(int id) {
		Product p = new Product();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select tblproduct.id as id, tblproduct.name as name, tblproduct.price as price, "
						+ "tblproduct.imageMain as imageMain, tblproduct.imageGallery as imageGallery, tblproduct.sale as sale, tblproduct.des as des, tblcategory.id as idc, tblcategory.name as nameC "
						+ "from tblproduct, tblcategory where tblproduct.category_id = tblcategory.id and tblproduct.id = ?");){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getInt("price"));
				p.setImageMain(rs.getString("imageMain"));
				String img[] = rs.getString("imageGallery").trim().split(",");
				if(img.length == 3) {
					p.setImageGallery1(img[0]);
					p.setImageGallery2(img[1]);
					p.setImageGallery3(img[2]);
				}
				p.setSale(rs.getFloat("sale"));
				p.setDes(rs.getString("des"));
				p.setCategory(new Category());
				p.getCategory().setId(rs.getInt("idc"));
				String[] nameC = rs.getString("nameC").split("-");
				p.getCategory().setSex(nameC[0]);
				p.getCategory().setType(nameC[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void insertPro(Product product) {
		try (Connection con=getConnection();
				PreparedStatement ps = con.prepareStatement("INSERT INTO `tblproduct` (`name`, `price`, `imageMain`, `sale`, `imageGallery`, `Category_id`) VALUES (?, ?, ?, ?, ?, ?)")){
			ps.setString(1, product.getName());
			ps.setFloat(2, product.getPrice());
			ps.setString(3, product.getImageMain());
			ps.setFloat(4, product.getSale());
			ps.setString(5, product.getImageGallery1()+","+product.getImageGallery2()+","+product.getImageGallery3());
			PreparedStatement ps1 = con.prepareStatement("select * from tblcategory where name = ?");
			ps1.setString(1, product.getCategory().getSex() + "-" + product.getCategory().getType());
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			ps.setInt(6, rs1.getInt("id"));
			int rs=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePro(Product pro) {
		try(Connection conn = getConnection(); 
				PreparedStatement ps = conn.prepareStatement("update tblproduct set name=?, price=?, imageMain=?, sale=?, imageGallery=?, des=?, category_id=? where id=?");){
		
			ps.setString(1, pro.getName());
			ps.setFloat(2, pro.getPrice());
			ps.setString(3, pro.getImageMain());
			ps.setFloat(4, pro.getSale());
			ps.setString(5, pro.getImageGallery1()+","+pro.getImageGallery2()+","+pro.getImageGallery3());
			ps.setString(6, pro.getDes());
			PreparedStatement ps1 = conn.prepareStatement("select * from tblcategory where name = ?");
			ps1.setString(1, pro.getCategory().getSex() + "-" + pro.getCategory().getType());
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			ps.setInt(7, rs1.getInt("id"));
			ps.setInt(8, pro.getId());
			int rs=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DeletePro(int id) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("delete from tblproduct where id = ?");){
			ps.setInt(1, id);
			int result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkName(String name) {
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select * from tblproduct where name=?");){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
