package com.md5.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ndktools.javamd5.Mademd5;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * 利用MD5进行加密
 * @param str 待加密字符串
 * @return 加密后的字符串	
 * @throw NoSuchAlgorithmException 没有这种产生消息加密的方法
 * @throws UnsupportedEncodingException
 */
public class ConnectionTest {

	public static void main(String[] args) throws Exception {

		String sql = "select * from admin";
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite://d:/pengyang.db");

		Mademd5 md5 = new Mademd5();

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {

			Admin admin = new Admin();
			admin.setId(rs.getInt(1));
			admin.setUsername(rs.getString(2));
			admin.setPassword(rs.getString(3));
			System.out.println(admin.toString());
			System.out.println(EncoderByMD5(rs.getString(3)));
			System.out.println(DecoderByMD5(EncoderByMD5(rs.getString(3))));
		}

		conn.close();
	}

	// 利用BASE64Encoder进行加密字串
	public static String EncoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		// 确认计算方法
		MessageDigest md = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();

		// 加密后的字符串
		String newstr = base64en.encode(str.getBytes());
		return newstr;
	}

	// 判断字符串是否一致
	public static boolean CheckStr(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(EncoderByMD5("admin").equals(str)){
			return true;
		}else{
			return false;
		}
		}
	// 利用BASE64Decoder进行字符串解密
	
	public static String DecoderByMD5(String str){
		
		BASE64Decoder base64de = new BASE64Decoder();
		
		String b = "";
		byte[] newstr;
		try {
			newstr = base64de.decodeBuffer(str);
			b = new String(newstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
		
	}
	
}
