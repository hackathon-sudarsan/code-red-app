package com.codered.rest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
			String storeProcCall = "{call insert_proc(?,?,?,?,?,?,?,?,?,?,?)}";
			System.out.println(METHOD_NAME + "Store Procedure statement : " + storeProcCall);

			CallableStatement cStmt = conn.prepareCall(storeProcCall);

			cStmt.setString(1, ad.getRequestType()); // request oid 1 or 2 (buy or sell)
			cStmt.setString(2, (ad.getCategroy() == null) ? "" : ad.getCategroy()); // categroy name 'REAL ESTATE'
			cStmt.setString(3, (ad.getVzId()== null) ? "" : ad.getVzId()); // VZID
			cStmt.setString(4, (ad.getFirstName()== null) ? "" : ad.getFirstName());// FRIST NAME
			cStmt.setString(5, (ad.getLastName()== null) ? "" : ad.getLastName());// LAST NAME
			cStmt.setInt(6,  ad.getPhone());// PHONE (INT)
			cStmt.setString(7, (ad.getEmail()== null) ? "" : ad.getEmail()); // EMAIL
			cStmt.setFloat(8, ad.getPrice());// PRICE (FLOAT)
			cStmt.setString(9, (ad.getTitle()== null) ? "" : ad.getTitle()); // TITLE
			cStmt.setString(10,  (ad.getDescription()== null) ? "" : ad.getDescription()); // TITLE
			cStmt.registerOutParameter(11, Types.INTEGER);
			boolean hadResults = cStmt.execute();
			System.out.println(METHOD_NAME + "hadResults : " + hadResults);
			int outputValue = 0;
		    while (hadResults) {
		         outputValue = cStmt.getInt(11); 
		    }
		    System.out.println(METHOD_NAME + "outputValue : " + outputValue);
			
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
		System.out.println(METHOD_NAME + "EXIT");
	}

	public List<Category> getALLCategory() {
		final String METHOD_NAME = "CoreRedServices : getALLCategory";
		List<Category> categoryList = new ArrayList<Category>();
		Category categoryObj = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT category_oid, category_name FROM category";
			System.out.println(METHOD_NAME + "QUERY :" + query);
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
		System.out.println(METHOD_NAME + "categoryList :" + categoryList);
		return categoryList;
	}

	public List<Advertisement> getRecentAdsByRequestType(int requestType) {
		final String METHOD_NAME = "CoreRedServices : getRecentAdsByRequestType";
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		Advertisement ad = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct ");
			if (requestType == 1)
				sb.append(" 'SELL' as request_type, ");
			else
				sb.append(" 'BUY' as request_type, ");

			sb.append(" cat.category_name, ");
			sb.append(" map.admin_map_oid, ");
			sb.append(" map.emp_vz_id, ");
			sb.append(" map.emp_first_name, ");
			sb.append(" map.emp_last_name, ");
			sb.append(" map.emp_phone, ");
			sb.append(" map.emp_email_id, ");
			sb.append(" map.price, ");
			sb.append(" map.data_added, ");
			sb.append(" map.product_title, ");
			sb.append(" map.product_comment ");
			sb.append(" from admin_map map,category cat ");
			sb.append(" where  ");
			sb.append(" map.category_oid=cat.category_oid and  ");
			sb.append(" map.request_status = 'A'  ");
			sb.append(" and map.request_type_oid=").append(requestType);
			sb.append(" and DATE(map.data_added) > (DATE(NOW())-10) ");

			System.out.println(METHOD_NAME + "QUERY :" + sb.toString());
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				ad = new Advertisement();
				ad.setRequestType(rs.getString("request_type"));
				ad.setCategroy(rs.getString("category_name"));
				ad.setVzId(rs.getString("emp_vz_id"));
				ad.setFirstName(rs.getString("emp_first_name"));
				ad.setLastName(rs.getString("emp_last_name"));
				ad.setPhone(rs.getInt("emp_phone"));
				ad.setEmail(rs.getString("emp_email_id"));
				ad.setPrice(rs.getFloat("price"));
				ad.setTitle(rs.getString("product_title"));
				ad.setDescription(rs.getString("product_comment"));
				ad.setPrimaryKey(rs.getInt("admin_map_oid"));
				advertisementList.add(ad);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		}
		System.out.println(METHOD_NAME + "advertisementList :" + advertisementList);
		return advertisementList;
	}

	public Advertisement getAdDetails(int id) {
		final String METHOD_NAME = "CoreRedServices : getAdDetails";
		Advertisement ad = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct ");
			sb.append(" cat.category_name, ");
			sb.append(" map.emp_vz_id, ");
			sb.append(" map.emp_first_name, ");
			sb.append(" map.emp_last_name, ");
			sb.append(" map.emp_phone, ");
			sb.append(" map.emp_email_id, ");
			sb.append(" map.price, ");
			sb.append(" map.data_added, ");
			sb.append(" map.product_title, ");
			sb.append(" map.product_comment ");
			sb.append(" from admin_map map,category cat ");
			sb.append(" where  ");
			sb.append(" map.category_oid=cat.category_oid and  ");
			sb.append(" map.request_status = 'A'  ");
			sb.append(" and map.admin_map_oid=").append(id);

			System.out.println(METHOD_NAME + "QUERY :" + sb.toString());
			ResultSet rs = stmt.executeQuery(sb.toString());
			if (rs.next()) {
				ad = new Advertisement();
				ad.setRequestType(rs.getString("request_type"));
				ad.setCategroy(rs.getString("category_name"));
				ad.setVzId(rs.getString("emp_vz_id"));
				ad.setFirstName(rs.getString("emp_first_name"));
				ad.setLastName(rs.getString("emp_last_name"));
				ad.setPhone(rs.getInt("emp_phone"));
				ad.setEmail(rs.getString("emp_email_id"));
				ad.setPrice(rs.getFloat("price"));
				ad.setTitle(rs.getString("product_title"));
				ad.setDescription(rs.getString("product_comment"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		}
		System.out.println(METHOD_NAME + "ad :" + ad);
		return ad;
	}
	
	
	public List<Advertisement> getAdListForAdmin() {
		final String METHOD_NAME = "CoreRedServices : getAdListForAdmin";
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		Advertisement ad = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			StringBuilder sb = new StringBuilder();
			
			sb.append(" select ");
			sb.append(" distinct ");
			sb.append(" map.admin_map_oid, ");
			sb.append(" req.request_text, ");
			sb.append(" cat.category_name, ");
			sb.append(" map.emp_vz_id, ");
			sb.append(" map.emp_first_name, ");
			sb.append(" map.emp_last_name, ");
			sb.append(" map.emp_phone, ");
			sb.append(" map.emp_email_id, ");
			sb.append(" map.price, ");
			sb.append(" map.data_added, ");
			sb.append(" map.product_title, ");
			sb.append(" map.product_comment ");
			sb.append(" from admin_map map,category cat,request_type req ");
			sb.append(" where  ");
			sb.append(" map.category_oid=cat.category_oid and req.request_type_oid=map.request_type_oid ");
			sb.append(" and request_status='P' ");

			System.out.println(METHOD_NAME + "QUERY :" + sb.toString());
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				ad = new Advertisement();
				ad.setAdminMapOid(rs.getString("admin_map_oid"));
				ad.setRequestType(rs.getString("request_text"));
				ad.setCategroy(rs.getString("category_name"));
				ad.setVzId(rs.getString("emp_vz_id"));
				ad.setFirstName(rs.getString("emp_first_name"));
				ad.setLastName(rs.getString("emp_last_name"));
				ad.setPhone(rs.getInt("emp_phone"));
				ad.setEmail(rs.getString("emp_email_id"));
				ad.setPrice(rs.getFloat("price"));
				ad.setTitle(rs.getString("product_title"));
				ad.setDescription(rs.getString("product_comment"));
				ad.setDateAdded(rs.getString("data_added"));
				advertisementList.add(ad);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		}
		System.out.println(METHOD_NAME + "advertisementList :" + advertisementList);
		return advertisementList;
	}

	public List<Advertisement> search(String titleSearchStr,String commentSearchStr, String category) {
		final String METHOD_NAME = "CoreRedServices : getAdListForAdmin";
		List<Advertisement> advertisementList = new ArrayList<Advertisement>();
		Advertisement ad = null;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append(" select ");
			sb.append(" distinct ");
			sb.append(" map.request_type_oid, ");
			sb.append(" cat.category_name, ");
			sb.append(" map.emp_vz_id, ");
			sb.append(" map.emp_first_name, ");
			sb.append(" map.emp_last_name, ");
			sb.append(" map.emp_phone, ");
			sb.append(" map.emp_email_id, ");
			sb.append(" map.price, ");
			sb.append(" map.data_added, ");
			sb.append(" map.product_title, ");
			sb.append(" map.product_comment ");
			sb.append(" from admin_map map,category cat ");
			sb.append(" where  ");
			sb.append(" map.category_oid=cat.category_oid and  ");
			sb.append(" map.category_oid in (select category_oid from category where category_name='").append(category).append("'");
			sb.append(" and (product_title like '%").append(titleSearchStr).append("%'");
			sb.append(" or product_comment like '%").append(commentSearchStr).append("%'");

			System.out.println(METHOD_NAME + "QUERY :" + sb.toString());
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				ad = new Advertisement();
				ad.setCategroy(rs.getString("category_name"));
				ad.setVzId(rs.getString("emp_vz_id"));
				ad.setFirstName(rs.getString("emp_first_name"));
				ad.setLastName(rs.getString("emp_last_name"));
				ad.setPhone(rs.getInt("emp_phone"));
				ad.setEmail(rs.getString("emp_email_id"));
				ad.setPrice(rs.getFloat("price"));
				ad.setTitle(rs.getString("product_title"));
				ad.setDescription(rs.getString("product_comment"));
				advertisementList.add(ad);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySqlDataSource.closeConnection(stmt, conn);
		}
		System.out.println(METHOD_NAME + "advertisementList :" + advertisementList);
		return advertisementList;
	}
	
	
	public int approveAdmin(String admin_map_oid) {
		int rows = 0;
		try {
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			String query = "UPDATE admin_map set request_status = 'A' where admin_map_oid  ='"+admin_map_oid+"'";
			System.out.println("Query : " + query);
			rows = stmt.executeUpdate(query);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		System.out.println("Rows impacted : " + rows);
		return rows;
	}
}
