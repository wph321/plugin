package com.jdbc.until;

import java.sql.*;
import java.sql.SQLException;

import com.modle.Admin;
public class JdbcUntil {

	String url = "jdbc:mysql://localhost:3306/face?user=root&password=123456";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
	
		

		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection) DriverManager.getConnection(url);
		return conn;
	}
	

	public static void closeConnection(Connection con, Statement ps, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
