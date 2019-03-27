package com.smart.sso.rpc;

import java.io.Serializable;


public class UserRpcDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3632274615986492754L;
	
	private Integer userId;
	
	private Integer orgId;
	private String orgName;
	private Integer appId;
	private Integer appUserId;
	/** 登录名 */
	private String account;
	private String userCode;
	private String realName;
	private String sex;
	private String sexDesc;
	private String mobile;
	private String email;
	
	//所教课程名称串，仅作显示用
	private String classTypeDescs;
	
	/** 是否启用 */
	private Integer isEnable;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getClassTypeDescs() {
		return classTypeDescs;
	}

	public void setClassTypeDescs(String classTypeDescs) {
		this.classTypeDescs = classTypeDescs;
	}

	public String getSexDesc() {
		return sexDesc;
	}

	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}
	

}
