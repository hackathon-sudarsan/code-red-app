package com.codered.managebean;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
			stmt = conn.createStatement();
			System.out.println("Query : " + this.query);
			rows = stmt.executeUpdate(this.query);
		} catch (SQLException se) {
			// Handle errors for JDBC
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
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

	@Override
	public String toString() {
		return "MySqlBean [query=" + query + ", errMsg=" + errMsg + "]";
	}

}