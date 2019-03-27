package com.smart.sso.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smart.sso.rpc.OrgRpcDto;
import com.smart.sso.rpc.OrgRpcService;
import com.smart.sso.server.service.OrgService;

@Service("orgRpcService")
public class OrgRpcServiceImpl implements OrgRpcService{
	@Resource
	private OrgService orgService;

	@Override
	public List<OrgRpcDto> findByBusiCode(String[] busiCodes) {
		return orgService.findByBusiCode(busiCodes);
	}

}
