package com.codered.rest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.codered.MySqlDataSource;
import com.codered.dataobject.Category;
import com.codered.service.Advertisement;

@Path("/codeRedServices")
public class CodeRedServices {

	@GET
	@Produces("text/plain")
	@Path("/hello")
	public String getClichedMessage() {
		return "Hello World";
	}

	private Connection conn = null;
	private Statement stmt = null;
	public void manageAd(Advertisement ad) {
		final String METHOD_NAME = "CoreRedServices : manageAd";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			String storeProcCall = "{call insert_proc(?,?,?,?,?,?,?,?,?,?)}";
			System.out.println(METHOD_NAME+"Store Procedure statement : " + storeProcCall);

			CallableStatement cStmt = conn.prepareCall(storeProcCall);
			cStmt.setInt(1, Integer.getInteger(ad.getRequestType())); // request oid 1or 2(buy orsell)
			cStmt.setString(2, ad.getCategroy()); // categroy name 'REAL ESTATE'
			cStmt.setString(3, ad.getVzId()); // VZID
			cStmt.setString(4, ad.getFirstName());// FRIST NAME
			cStmt.setString(5, ad.getLastName());// LAST NAME
			cStmt.setInt(6, ad.getPhone());// PHONE (INT)
			cStmt.setString(7, ad.getEmail()); // EMAIL
			cStmt.setFloat(8, ad.getPrice());// PRICE (FLOAT)
			cStmt.setString(9, ad.getTitle()); // TITLE
			cStmt.setString(10, ad.getDescription()); // TITLE
			cStmt.execute();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} 
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		} 
		System.out.println(METHOD_NAME+"EXIT");
	}
	
	public List<Category> getALLCategory() {
		final String METHOD_NAME = "CoreRedServices : getALLCategory";
		List<Category> categoryList = new ArrayList<Category>();
		Category categoryObj = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT category_oid, category_name FROM category";
			System.out.println(METHOD_NAME + "QUERY :" +query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				categoryObj = new Category();
				categoryObj.setCategoryOid(rs.getInt("category_oid"));
				categoryObj.setCategoryName(rs.getString("category_name"));
				categoryList.add(categoryObj);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		} 
		System.out.println(METHOD_NAME + "categoryList :" +categoryList);
		return categoryList;
	}

	
	public List<Advertisement> getRecentAdsByRequestType(int requestType) {
		final String METHOD_NAME = "CoreRedServices : getRecentAdsByRequestType";
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		Advertisement ad = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT category_oid, category_name FROM category";
			System.out.println(METHOD_NAME + "QUERY :" +query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ad = new Advertisement();
				/*ad.setRequestType();
				ad.setCategroy();
				ad.setVzId();
				ad.setFirstName();
				ad.setLastName();
				ad.setPhone();
				ad.setEmail();
				ad.setPrice();
				ad.setTitle();
				ad.setDescription();
				advertisementList.add(adObj);*/
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		} 
		System.out.println(METHOD_NAME + "advertisementList :" +advertisementList);
		return advertisementList;
	}
	
	public Advertisement getAdDetails(int id) {
		final String METHOD_NAME = "CoreRedServices : getAdDetails";
		Advertisement ad = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT category_oid, category_name FROM category";
			System.out.println(METHOD_NAME + "QUERY :" +query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				/*ad = new Advertisement();
				ad.etRequestType();
				ad.setCategroy();
				ad.setVzId();
				ad.setFirstName();
				ad.setLastName();
				ad.setPhone();
				ad.setEmail();
				ad.setPrice();
				ad.setTitle();
				ad.setDescription();
				advertisementList.add(adObj);*/
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		} 
		System.out.println(METHOD_NAME + "ad :" +ad);
		return ad;
	}
	
}