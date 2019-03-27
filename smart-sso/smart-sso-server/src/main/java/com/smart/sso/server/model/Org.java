package com.smart.sso.server.model;


import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.smart.mvc.model.PersistentObject;
import com.smart.sso.server.enums.TrueFalseEnum;
/**
 * 组织
 * 
 * @author Yangxinxia
 */
public class Org extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4501124493389273474L;
	
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
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	public Boolean getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	public Integer getpId() {
		return this.parentId;
	}
	
	/** 以下为显示辅助参数 */
	private boolean checked = false;
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? TrueFalseEnum.TRUE.getLabel() : TrueFalseEnum.FALSE.getLabel();
	}
	
	
}
