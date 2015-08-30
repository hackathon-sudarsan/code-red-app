package com.codered;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDataSource {

	public static void main(String[] args) {
		MySqlDataSource.getConnection();
	}
	
	
	public static Connection getConnection()  {
		System.out.println("Connection Started");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			String url ="jdbc:mysql://us-iron-auto-sfo-02-bh.cleardb.net/ad_43f635f72750b54?user=bfe8e3698cba7f&password=ba6201f1";
			System.out.println("Connection Establishied url ====> " + url);
		    conn =
		       DriverManager.getConnection(url);
		    	System.out.println("Connection Establishied ====> " + conn);
		} catch (SQLException ex) {
			ex.printStackTrace();
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		System.out.println("Connection Exit");
		return conn;
	}
	
	public static void closeConnection(Statement stmt, Connection conn) {
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
	}
	
	
}
