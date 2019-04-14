package com.smart.sso.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.server.dao.DictDao;
import com.smart.sso.server.model.Dict;
import com.smart.sso.server.service.DictService;

@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, Dict, Integer> implements DictService {

	@Autowired
	public void setDao(DictDao dao) {
		this.dao = dao;
	}

}
