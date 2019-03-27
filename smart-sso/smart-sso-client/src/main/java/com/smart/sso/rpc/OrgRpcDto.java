package com.smart.sso.rpc;

import java.io.Serializable;

public class OrgRpcDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5018099656538243062L;
	
	private Integer id;
	/** 父ID */
	private Integer parentId;
	/** 名称 */
	private String name;

	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	
	/** 是否启用 */
	private Boolean isEnable = Boolean.valueOf(true);
	
	private Integer orgLevel;
	
	private String busiCode;
	
	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	

}
