package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.smart.sso.rpc.UserRpcDto;
import com.smart.sso.rpc.UserRpcService;
import com.smart.sso.server.model.User;
import com.smart.sso.server.service.UserService;

@Service("userRpcService")
public class UserRpcServiceImpl implements UserRpcService{
	@Resource
	private UserService userService;

	@Override
	public List<UserRpcDto> findByRoleBusiCode(String[] roleBusiCodes,Integer exceptUserId,String realName, Boolean isEnable) {
		return userService.findByRoleBusiCode(roleBusiCodes,exceptUserId,realName, isEnable);
	}

	@Override
	public List<UserRpcDto> findByUserIds(List<Integer> idList) {
		List<User> userList=userService.findByIds(idList);
		return convertData(userList);
	}
	
	/**
	 * 转换数据类型
	 * @param userList
	 * @return
	 */
	private List<UserRpcDto> convertData(List<User> userList){
		List<UserRpcDto> userRpcList=new ArrayList<UserRpcDto>();
		if(!CollectionUtils.isEmpty(userList)) {
			UserRpcDto userRpc=null;
			for(User user:userList) {
				userRpc=new UserRpcDto();
				BeanUtils.copyProperties(user,userRpc);
				userRpc.setUserId(user.getId());
				userRpcList.add(userRpc);
			}
		}
		return userRpcList;
	}

	@Override
	public List<UserRpcDto> findByUserInfo(String[] roleBusiCodes, Integer orgId, String realName, 
			String userCode,String mobile) {
		return userService.findByUserInfo(roleBusiCodes, orgId, realName, userCode, mobile);
	}

	@Override
	public List<UserRpcDto> findByUserIdsAndParams(List<Integer> idList, Map params) {
		return userService.findByUserIdsAndParams(idList, params);
	}

}
