package com.budgetbuddy.finance.jwtLogin.model;

import java.io.Serializable;

public class JwtRequest implements Serializable{
	private static final long serialVersionUID = 5926468583005150707L;
	
	private String user_id;
	private String user_pw;
	
	public JwtRequest() {}
	
	public JwtRequest(String user_id, String user_pw, String user_nickname) {
		this.setUser_id(user_id);
		this.setUser_pw(user_pw);
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
}