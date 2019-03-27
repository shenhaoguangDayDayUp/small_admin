package com.smart.sso.server.model;

import com.smart.mvc.model.PersistentObject;

/**
 * 用户角色映射
 * 
 * @author Frank_King
 */
public class UserUser extends PersistentObject {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 管理人ID */
	private Integer chargeUserId;

	/** 被用户ID */
	private Integer userId;
	
	public UserUser() {
		super();
	}
	
	public UserUser(Integer chargeUserId,Integer userId) {
		super();
		this.chargeUserId=chargeUserId;
		this.userId=userId;
	}
	
	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChargeUserId() {
		return chargeUserId;
	}

	public void setChargeUserId(Integer chargeUserId) {
		this.chargeUserId = chargeUserId;
	}
	
	
}
