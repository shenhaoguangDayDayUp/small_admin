package com.smart.sso.server.dto;

import java.io.Serializable;

public class OrgUserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5631729121131155448L;
	
	private Integer id;
	private Integer orgId;
	private Integer userId;
	private String name;
	private Boolean nocheck;
	private Integer parentId;
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}


	public Integer getpId() {
		return this.parentId;
	}

	/** 以下为显示辅助参数 */
	private boolean checked = false;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
