package com.smart.sso.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.sso.server.dao.SysOptionDao;
import com.smart.sso.server.service.SysOptionService;

@Service("sysOptionService")
public class SysOptionServiceImpl implements SysOptionService {
	
	@Autowired
	private SysOptionDao sysOptionDao;

	@Override
	public List<Object> getOption(Integer optionId, String optionCode, Integer state) {
		List<Object> list = sysOptionDao.getOption(optionId, optionCode, state);
		return list;
	}

}
