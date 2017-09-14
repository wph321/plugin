package com.jdbc.until;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JDBCDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String url = "jdbc:mysql://localhost:3306/face?user=root&password=123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(url);

			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
