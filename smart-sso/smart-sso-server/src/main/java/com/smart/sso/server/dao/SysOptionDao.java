package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 系统配置表持久化接口
 * 
 * @author Frank_King
 */
public interface SysOptionDao {

	public List<Object> getOption(@Param("optionId")Integer optionId, @Param("optionCode")String optionCode, @Param("state")Integer state);

}
