package com.smart.sso.client;

import java.io.Serializable;

/**
 * 已登录用户信息
 * 
 * @author Joe
 */
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1764365572138947234L;

	// 登录用户访问Token
	private String token;
	
	// 登录成功ID
	private Integer userId;
	
	// 登录名
	private String account;
	
	//角色业务编码，用逗号分隔
	private String roleBusiCode;
	
	//管理人员ids
	private String manageUserIds;
	
	public SessionUser() {
		super();
	}

	public SessionUser(String token, Integer userId,String account) {
		super();
		this.token = token;
		this.userId=userId;
		this.account = account;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleBusiCode() {
		return roleBusiCode;
	}

	public void setRoleBusiCode(String roleBusiCode) {
		this.roleBusiCode = roleBusiCode;
	}

	public String getManageUserIds() {
		return manageUserIds;
	}

	public void setManageUserIds(String manageUserIds) {
		this.manageUserIds = manageUserIds;
	}
	
	
}
