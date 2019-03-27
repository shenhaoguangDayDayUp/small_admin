package com.smart.sso.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.rpc.OrgRpcDto;
import com.smart.sso.server.dao.OrgDao;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.model.Org;
import com.smart.sso.server.service.OrgService;


@Service("orgService")
public class OrgServiceImpl extends ServiceImpl<OrgDao, Org, Integer> implements OrgService {

	@Autowired
	public void setDao(OrgDao dao) {
		this.dao = dao;
		
	}
	@Override
	public List<Org> findByAll(Integer parentId,Boolean isEnable,String name) {
		return dao.findByAll(parentId,isEnable,name);
	}
	public void save(Org t) {
		super.save(t);
	}
	

	@Override
	public List<Org> findByParentId(Integer parentId) {
		return dao.findByParentId(parentId,null);
	}
	
	public List<OrgUserDto> findAllOrgs(String orgBusiCode){
		return dao.findAllOrgs(orgBusiCode);
	}
	@Override
	public List<OrgRpcDto> findByBusiCode(String[] busiCodes) {
		return dao.findByBusiCode(busiCodes);
	}
}
