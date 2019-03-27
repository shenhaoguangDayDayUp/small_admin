package com.smart.sso.server.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.smart.mvc.model.PersistentObject;

/**
 * 学生
 * 
 * @author Frank_King
 */
public class Student extends PersistentObject {

	private static final long serialVersionUID = 1106412532325860697L;
	private Integer id;
	private String name;
	private String sex;
	private String campus;
	private String parentName;
	/** 登录名 */
	private String relationship;
	/** 学生生日 */
	@JSONField(format = "yyyy-MM-dd")
	private Date birthday;

	private String mobile;
	private String address;
	private String channel_sources;

	private Integer head_id;
	private Integer create_by;
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	private Integer update_by;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChannel_sources() {
		return channel_sources;
	}

	public void setChannel_sources(String channel_sources) {
		this.channel_sources = channel_sources;
	}

	public Integer getHead_id() {
		return head_id;
	}

	public void setHead_id(Integer head_id) {
		this.head_id = head_id;
	}

	public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(Integer update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
