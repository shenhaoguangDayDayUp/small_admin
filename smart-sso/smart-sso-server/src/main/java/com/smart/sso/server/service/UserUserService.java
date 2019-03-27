package com.smart.sso.server.service;

import java.util.List;

import com.smart.mvc.service.mybatis.Service;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.model.UserUser;

/**
 * 用户与用户映射服务接口
 * 
 * @author Yangxinxia
 */
public interface UserUserService extends Service<UserUser, Integer> {

	/**
	 * 根据管理者生成映射关系
	 * 
	 * @param chargeUserId
	 * @param list
	 */
	public void allocate(Boolean isDelOld, Integer chargeUserId, List<Integer> userIdList);

	public void deleteByChargeUserIds(List<Integer> idList);

	public void deleteByUserIds(List<Integer> idList);

	public List<OrgUserDto> findAllNodes(Integer chargeUserId);

}
