package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.sso.rpc.OrgRpcDto;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.model.Org;

/**
 *组织持久化接口
 * 
 * @author Frank_King
 */
public interface OrgDao extends Dao<Org, Integer> {

	
	public List<Org> findByAll(@Param("parentId") Integer parentId,@Param("isEnable") Boolean isEnable,@Param("name") String name);
	
	public List<Org> findByParentId(@Param("parentId") Integer parentId,@Param("isEnable") Boolean isEnable);
	
	public List<OrgUserDto> findAllOrgs(@Param("orgBusiCode") String orgBusiCode);
	
	public List<OrgRpcDto> findByBusiCode(@Param("busiCodes") String[] busiCodes);
}
