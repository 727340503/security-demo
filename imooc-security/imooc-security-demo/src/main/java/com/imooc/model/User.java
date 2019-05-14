package com.imooc.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3181220446265930988L;
	
	public interface userSimpleView{}
	
	public interface userDetailView extends userSimpleView{}
	
	private String userName;
	
	private String password;

	@JsonView(userSimpleView.class)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonView(userDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
