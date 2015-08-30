package com.codered.rest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.codered.MySqlDataSource;
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

	public void callSP(Advertisement ad) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = MySqlDataSource.getConnection();
			stmt = conn.createStatement();

			String storeProcCall = "{call insert_proc(?,?,?,?,?,?,?,?,?,?)}";

			System.out.println("Store Procedure statement : " + storeProcCall);

			CallableStatement cStmt = conn.prepareCall(storeProcCall);

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
			cStmt.setString(10, "description"); // TITLE
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

}