package com.smart.sso.server.service;

import java.util.List;

/**
 * 系统配置表接口
 * 
 * @author Frank_King
 */
public interface SysOptionService {
	/**
	 * 通过optionId模块id、optionCode配置代码、state状态（是否生效中）获取对应模块下的配置信息
	 * 
	 * @Date:2019.03.21 22:13
	 * @author Frank_King
	 */
	public List<Object> getOption(Integer optionId, String optionCode, Integer state);

}
