package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.sso.server.model.Role;

/**
 * 角色持久化接口
 * 
 * @author Frank_King
 */
public interface RoleDao extends Dao<Role, Integer> {

	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);

	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Role> findPagination(@Param("name") String name,@Param("busiCode") String busiCode,
			@Param("isEnable") Boolean isEnable,Pagination<Role> p);
	
	public List<Role> findListByUserId(@Param("userId") Integer userId,@Param("busiCode") String busiCode);
}
