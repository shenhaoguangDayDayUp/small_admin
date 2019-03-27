package com.smart.sso.server.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.sso.rpc.UserRpcDto;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.model.User;

/**
 * 用户持久化接口
 * 
 * @author Frank_King
 */
public interface UserDao extends Dao<User, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList,
			@Param("updateUserId") Integer updateUserId,@Param("updateTime") Date updateTime);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<User> findPagination(@Param("orgId") Integer orgId,@Param("account") String account,
			@Param("realName") String realName,@Param("mobile") String mobile,Pagination<User> p);
	
	public User findByAccount(@Param("account") String account);
	
	public User findByUserCode(@Param("userCode") String userCode);
	
	public List<User> findByOrgId(@Param("orgIdList") List<Integer> orgIdList,@Param("isEnable") Boolean isEnable);
	
	public List<User> findByOrgBusiCode(@Param("busiCode") String busiCode,@Param("isEnable") Boolean isEnable);
	
	
	public List<User> findByChargeUserId(@Param("chargeUserId") Integer chargeUserId);
	
	public List<OrgUserDto> findAllUsers(@Param("isEnable") Boolean isEnable);
	
	public List<UserRpcDto> findByRoleBusiCode(@Param("roleBusiCodes") String[] roleBusiCodes,
			@Param("exceptUserId") Integer exceptUserId,@Param("realName") String realName,
			@Param("isEnable") Boolean isEnable);
	
	public List<UserRpcDto> findByUserInfo(@Param("roleBusiCodes") String[] roleBusiCodes,
			@Param("orgId") Integer orgId,@Param("realName") String realName,
			@Param("mobile") String mobile,@Param("userCode") String userCode);
	
	public List<User> findByIds(@Param("idList") List<Integer> idList);
	
	public List<UserRpcDto> findByUserIdsAndParams(@Param("idList") List<Integer> idList,@Param("params") Map params);
	
	public Integer countByUserCodePrifix(@Param("userCodePrifix") String userCodePrifix);
	public List<User> findAllSource();

	
}
