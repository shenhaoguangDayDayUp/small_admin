package com.smart.sso.server.model;

import java.util.Date;

import com.smart.mvc.model.PersistentObject;

/**
 * @createAuthor Eugene.Chen
 * @createDate 2019-04-14
 * @modifyAuthor Eugene.Chen
 * @modifyDate 2019-04-14
 * @table sys_dict
 */
public class Dict extends PersistentObject {
	private static final long serialVersionUID = 6316894126034595720L;
	//("字典类型编码")
	private String dictCode;
	//("字典类型描述")
	private String dictDesc;
	//("备注")
	private String remark;
	//("是否启用 1启用 0禁用")
	private Integer isEnabel;
	//("创建时间")
	private Date createTime;
	//("更新时间")
	private Date updateTime;

	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictDesc() {
		return dictDesc;
	}
	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsEnabel() {
		return isEnabel;
	}
	public void setIsEnabel(Integer isEnabel) {
		this.isEnabel = isEnabel;
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

}