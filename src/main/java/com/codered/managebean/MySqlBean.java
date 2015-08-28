package com.codered.managebean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.codered.MySqlDataSource;
import com.codered.service.Advertisement;

@ManagedBean(name = "sqlBean")
public class MySqlBean {

	private String query;
	private String errMsg;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public void executeQuery(ActionEvent event) {

		System.out.println("Inside execute query");
	}

	Connection conn = null;
	Statement stmt = null;

	public int executeQuery() {
		int rows = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			System.out.println("Query : " + this.query);
			rows = stmt.executeUpdate(this.query);
		} catch (SQLException se) {
			// Handle errors for JDBC
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
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
		this.setErrMsg("Rows impacted : " + rows);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
		return rows;
	}

	public void executeReadQuery() {
		List<String> ls = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			System.out.println("Query : " + this.query);
			ResultSet rs = stmt.executeQuery(this.query);
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();

			while (rs.next()) {
				for (int col = 1; col <= colCount; col++) {
					Object value = rs.getObject(col);
					if (value != null) {
						System.out.print(value.toString());
						ls.add(value.toString());
					}
				}
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
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

		this.setErrMsg("Refere lo: " + ls);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));

	}

	public void callSP(Advertisement ad) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();
			System.out.println("Query : " + this.query);

			CallableStatement cStmt = conn.prepareCall("{call insert_proc(?, ?,?, ?,?, ?,?, ?,?)}");

			String categoryName = "categoryName";
			String vzId = "vzid";
			String firstName = "firstName";
			String lastName = "lastName";

			cStmt.setInt(1, 1); // request oid 1 or 2 (buy or sell)
			cStmt.setString(2, "abcdefg"); // categroy name 'REAL ESTATE'
			cStmt.setString(3, "abcdefg"); // VZID
			cStmt.setString(4, firstName);// FRIST NAME
			cStmt.setString(5, lastName);// LAST NAME
			cStmt.setInt(6, 1234455);// PHONE (INT)
			cStmt.setString(7, "email"); // EMAIL
			cStmt.setFloat(8, 1222F);// PRICE (FLOAT)
			cStmt.setString(9, "title"); // TITLE

			cStmt.execute();

		} catch (SQLException se) {
			// Handle errors for JDBC
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
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

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));

	}

	@Override
	public String toString() {
		return "MySqlBean [query=" + query + ", errMsg=" + errMsg + "]";
	}

}