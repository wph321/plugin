package com.dao.method;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jdbc.until.JdbcUntil;
import com.modle.Admin;
public class AdminDao {

	//������������󣬷�����ù����෽����ȡ���ر�����
	JdbcUntil ju = new JdbcUntil();

	@Test
	public List<Admin> findAllAdmin() throws ClassNotFoundException, SQLException{
		
		Connection conn = ju.getConnection();
		String sql = "select * from admin;";
		
		
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = (ResultSet) st.executeQuery(sql);
		
		//����list<Admin>���󣬴洢���admin���󣬼���������
		List<Admin> adminList = new ArrayList<>();
		//ѭ���������ݿ⴫��ĵ��е������ݣ���װ��admin�������list���棻
		while(rs.next()){
			//����admin���󣬷�װ���ݿ⴫������
			Admin admin = new Admin();
			admin.setId(rs.getInt(1));//���ݿ��n����һ������
			admin.setUsername(rs.getString(2));//���ݿ��n���ڶ�������
			admin.setPassword(rs.getString(3));//���ݿ��n������������
			admin.setDengji(rs.getInt(4));//���ݿ��n�����ĸ�����
			System.out.println(admin.toString());
			adminList.add(admin);
		}
		ju.closeConnection(conn, st, rs);
		//��������
		return adminList;
		
	}
	
	//��ɾ�Ĳ�������Ҫ����ֵ
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
