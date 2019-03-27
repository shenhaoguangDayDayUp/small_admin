package com.smart.sso.server.model;


import com.smart.mvc.model.PersistentObject;

/**
 * 系统配置
 * 
 * @author Frank_King
 */
public class SysOption extends PersistentObject {

	private static final long serialVersionUID = 1106412532325860697L;
	/** 模块ID */
	private Integer optionId;
	/** 配置代码 */
	private Integer optionCode;
	/** 配置名称*/
	private Integer optionName;
	/** 配置说明 */
	private String description;
	/** 属性值 */
	private String optionValue;
	//状态（0有效9失效）
	private String state;
	
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(Integer optionCode) {
		this.optionCode = optionCode;
	}
	public Integer getOptionName() {
		return optionName;
	}
	public void setOptionName(Integer optionName) {
		this.optionName = optionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
