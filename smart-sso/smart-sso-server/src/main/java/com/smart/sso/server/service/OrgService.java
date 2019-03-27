package com.smart.sso.server.service;

import java.util.List;

import com.smart.mvc.service.mybatis.Service;
import com.smart.sso.rpc.OrgRpcDto;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.model.Org;

/**
 * 组织服务接口
 * 
 * @author Frank_King
 */
public interface OrgService extends Service<Org, Integer> {

	/**
	 * 查询全部的组织
	 * @param isEnable
	 * @return
	 */
	public List<Org> findByAll(Integer parentId,Boolean isEnable,String name);
	
	/**
	 * 根据父节点Id查询组织
	 * @param parentId
	 * @return
	 */
	public List<Org> findByParentId(Integer parentId);
	
	public List<OrgUserDto> findAllOrgs(String orgBusiCode);
	
	public List<OrgRpcDto> findByBusiCode(String[] busiCode);

}
