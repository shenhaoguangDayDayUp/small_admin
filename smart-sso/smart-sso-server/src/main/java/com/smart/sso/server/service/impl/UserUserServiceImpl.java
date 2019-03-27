package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.server.dao.UserUserDao;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.model.Role;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.UserUser;
import com.smart.sso.server.service.OrgService;
import com.smart.sso.server.service.RoleService;
import com.smart.sso.server.service.UserService;
import com.smart.sso.server.service.UserUserService;

@Service("userUserService")
public class UserUserServiceImpl extends ServiceImpl<UserUserDao, UserUser, Integer> implements UserUserService {
	
	@Resource
	private UserService userService;
	
	@Resource
	private OrgService orgService;
	
	@Resource
	private RoleService roleService;
	
	@Autowired
	public void setDao(UserUserDao dao) {
		this.dao = dao;
	}
	

	@Transactional
	public void allocate(Boolean isDelOld,Integer chargeUserId, List<Integer> userIdList) {
		if(isDelOld) {
			dao.deleteByChargeUserIds(Arrays.asList(chargeUserId));
		}
		
		List<UserUser> userUserList=new ArrayList<UserUser>();
		for(Integer userId:userIdList) {
			userUserList.add(new UserUser(chargeUserId,userId));
		}
		if (!CollectionUtils.isEmpty(userUserList)) {
			super.save(userUserList);
		}
	}

	@Override
	public void deleteByChargeUserIds(List<Integer> idList) {
		dao.deleteByChargeUserIds(idList);
		
	}
	
	@Override
	public void deleteByUserIds(List<Integer> idList) {
		dao.deleteByUserIds(idList);
		
	}

	public List<OrgUserDto> findAllNodes(Integer chargeUserId){
		List<OrgUserDto> retList=new ArrayList<OrgUserDto>();
		List<OrgUserDto> orgList=orgService.findAllOrgs(null);
		if (!CollectionUtils.isEmpty(orgList)) {
			retList.addAll(orgList);
		}
		List<OrgUserDto> allUserList=userService.findAllUsers(null);
		List<User> manageUserList= userService.findByChargeUserId(chargeUserId);
		
		List<Role> roleList=roleService.findListByUserId(chargeUserId,null);
		Boolean isSuperAdmin=false;
		if(!CollectionUtils.isEmpty(roleList)) {
			for(Role role:roleList) {
				if("superadmin".equals(role.getBusiCode())) {
					isSuperAdmin=true;
				}
			}
		}
		
		if (!CollectionUtils.isEmpty(allUserList)) {
			retList.addAll(allUserList);
			for(OrgUserDto orgUserDto:allUserList) {
				if(isSuperAdmin) {
					orgUserDto.setChecked(true);
				}
				else if(!CollectionUtils.isEmpty(manageUserList)){
					for(User user:manageUserList) {
						if(orgUserDto.getUserId().equals(user.getId())) {
							orgUserDto.setChecked(true);
							break;
						}
					}
				}
			}
		}
		return retList;
	}
}
