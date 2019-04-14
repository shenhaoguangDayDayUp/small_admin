package com.smart.sso.server.model;

import java.util.Date;

import com.smart.mvc.model.PersistentObject;

/**
 * 系统字典表基类
 * @createAuthor Eugene.Chen
 * @createDate 2019-04-14
 * @modifyAuthor Eugene.Chen
 * @modifyDate 2019-04-14
 * @table sys_dict_item
 */
public class DictItem extends PersistentObject {

	private static final long serialVersionUID = 1518329354311479394L;
	//("字典类型编码")
	private String dictCode;
	//("字典编码")
	private String dictItemCode;
	//("字典编码描述")
	private String dictItemDesc;
	//("业务编码")
	private String busiCode;
	//("排序")
	private Integer sort;
	//("备注")
	private String remark;
	//("备用字段")
	private String reserve;
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
	public String getDictItemCode() {
		return dictItemCode;
	}
	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}
	public String getDictItemDesc() {
		return dictItemDesc;
	}
	public void setDictItemDesc(String dictItemDesc) {
		this.dictItemDesc = dictItemDesc;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
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