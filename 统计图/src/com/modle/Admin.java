package com.modle;

public class Admin {

	private int id;
	private String username;
	private String password;
	private int dengji;
	
	public Admin(int id, String username, String password, int dengji) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.dengji = dengji;
	}
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDengji() {
		return dengji;
	}

	public void setDengji(int dengji) {
		this.dengji = dengji;
	}

	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", dengji=" + dengji + "]";
	}
	
	
	
	
}
