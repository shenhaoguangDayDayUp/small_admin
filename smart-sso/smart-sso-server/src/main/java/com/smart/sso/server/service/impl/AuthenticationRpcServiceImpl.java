package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.smart.mvc.util.StringUtils;
import com.smart.sso.rpc.AuthenticationRpcService;
import com.smart.sso.rpc.RpcPermission;
import com.smart.sso.rpc.RpcUser;
import com.smart.sso.server.common.LoginUser;
import com.smart.sso.server.common.TokenManager;
import com.smart.sso.server.model.Role;
import com.smart.sso.server.model.User;
import com.smart.sso.server.service.PermissionService;
import com.smart.sso.server.service.RoleService;
import com.smart.sso.server.service.UserService;

@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private PermissionService permissionService;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private TokenManager tokenManager;

	@Override
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}
	
	@Override
	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			RpcUser prcUser=new RpcUser(user.getUserId(),user.getAccount());
			return prcUser;
		}
		return null;
	}
	
	@Override
	public List<RpcPermission> findPermissionList(String token, String appCode) {
		if (StringUtils.isBlank(token)) {
			return permissionService.findListById(appCode, null);
		}
		else {
			LoginUser user = tokenManager.validate(token);
			if (user != null) {
				return permissionService.findListById(appCode, user.getUserId());
			}
			else {
				return new ArrayList<RpcPermission>(0);
			}
		}
	}
	
	public  String findUserByChargeUserId(Integer userId){
		StringBuffer manageUserIds=new StringBuffer();
		String retStr="";
		List<User> userList=userService.findByChargeUserId(userId);
		if(!CollectionUtils.isEmpty(userList)) {
			for(User user:userList) {
				manageUserIds.append(user.getId());
				manageUserIds.append(",");
			}
		}
		if(manageUserIds.length()>1) {
			retStr=manageUserIds.substring(0, manageUserIds.length()-2);
		}
		
		return retStr;
	}
	
	
	/**
	 * 获取角色列表字符串
	 * @param userId
	 * @return
	 */
	public String findRoleBusiCode(Integer userId) {
		StringBuffer roleBusiCode=new StringBuffer();
		String retStr="";
		List<Role> roleList=roleService.findListByUserId(userId,null);
		if(!CollectionUtils.isEmpty(roleList)) {
			for(Role role:roleList) {
				if(role.getBusiCode()!=null) {
					roleBusiCode.append(role.getBusiCode());
					roleBusiCode.append(",");
				}
			}
			
		}
		if(roleBusiCode.length()>1) {
			retStr=roleBusiCode.substring(0, roleBusiCode.length()-2);
		}
		return retStr;
	}

	@Override
	public void removeToken(String token) {
		tokenManager.remove(token);
		
	}
}
