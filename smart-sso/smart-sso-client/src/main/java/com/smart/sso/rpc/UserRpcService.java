package com.smart.sso.rpc;

import java.util.List;
import java.util.Map;


public interface UserRpcService {

	/**
	 * 根据角色业务编码查询用户
	 * @param roleBusiCodes 业务编码
	 * @param exceptUserId 除外的userId
	 * @param realName 姓名
	 * @param isEnable 状态：0禁用，1启用
	 * @return
	 */
	public List<UserRpcDto> findByRoleBusiCode(String[] roleBusiCodes,Integer exceptUserId,String realName, Boolean isEnable);
	
	public List<UserRpcDto> findByUserInfo(String[] roleBusiCodes,Integer orgId,String realName,String userCode,String mobile);
	
	/**
	 * 根据用id查询用户信息
	 * @param idList 用户userId集合
	 * @return
	 */
	public List<UserRpcDto> findByUserIds(List<Integer> idList);
	
	public List<UserRpcDto> findByUserIdsAndParams(List<Integer> idList,Map params);
}
