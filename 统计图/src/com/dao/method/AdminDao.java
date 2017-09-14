package com.dao.method;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jdbc.until.JdbcUntil;
import com.modle.Admin;
public class AdminDao {

	//创建工具类对象，方便调用工具类方法获取，关闭驱动
	JdbcUntil ju = new JdbcUntil();

	@Test
	public List<Admin> findAllAdmin() throws ClassNotFoundException, SQLException{
		
		Connection conn = ju.getConnection();
		String sql = "select * from admin;";
		
		
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = (ResultSet) st.executeQuery(sql);
		
		//定义list<Admin>对象，存储多个admin对象，即多条数据
		List<Admin> adminList = new ArrayList<>();
		//循环遍历数据库传输的单列单个数据，封装到admin对象加入list保存；
		while(rs.next()){
			//创建admin对象，封装数据库传输数据
			Admin admin = new Admin();
			admin.setId(rs.getInt(1));//数据库第n条第一个数据
			admin.setUsername(rs.getString(2));//数据库第n条第二个数据
			admin.setPassword(rs.getString(3));//数据库第n条第三个数据
			admin.setDengji(rs.getInt(4));//数据库第n条第四个数据
			System.out.println(admin.toString());
			adminList.add(admin);
		}
		ju.closeConnection(conn, st, rs);
		//返回数据
		return adminList;
		
	}
	
	//增删改操作不需要返回值
	public void addAdmin(Admin admin) throws ClassNotFoundException, SQLException{
		
		Connection conn = ju.getConnection();
		String sql = "insert into admin values(null,?,?,?);";
		
		PreparedStatement st = (PreparedStatement) conn.createStatement();
		st.setString(1, admin.getUsername());
		st.setString(2, admin.getPassword());
		st.setInt(3, admin.getDengji());
		
		st.executeUpdate(sql);
	}
	
	
	public void deleteAdmin(int id) throws ClassNotFoundException, SQLException{
		
		Connection conn = ju.getConnection();
		String sql = "delete * from admin where id=?";
		
		PreparedStatement ps = (PreparedStatement) conn.createStatement();
		ps.setInt(1, id);
		
		ps.executeUpdate();
		
	}
	
	public void updateAdmin(Admin admin) throws ClassNotFoundException, SQLException{
		
		Connection conn = ju.getConnection();
		String sql = "update admin s set s.username=? s.password=? s.dengji=? where id=?;";
	
		PreparedStatement st = (PreparedStatement) conn.createStatement();
		st.setString(1, admin.getUsername());
		st.setString(2, admin.getPassword());
		st.setInt(3, admin.getDengji());
		st.setInt(4, admin.getId());
		st.executeUpdate(sql);
	}
	
}
