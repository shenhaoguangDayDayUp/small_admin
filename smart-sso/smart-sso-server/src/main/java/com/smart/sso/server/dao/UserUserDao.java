package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;

import com.smart.sso.server.model.UserUser;

/**
 * 用户管理用户映射持久化接口
 * 
 * @author Yangxinxia
 */
public interface UserUserDao extends Dao<UserUser, Integer> {


	public int deleteByChargeUserIds(@Param("idList") List<Integer> idList);

	public int deleteByUserIds(@Param("idList") List<Integer> idList);
}
