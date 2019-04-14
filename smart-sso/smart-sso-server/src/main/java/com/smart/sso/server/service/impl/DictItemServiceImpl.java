package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.mvc.util.StringUtils;
import com.smart.sso.server.dao.DictItemDao;
import com.smart.sso.server.model.DictItem;
import com.smart.sso.server.service.DictItemService;

@Service("dictItemService")
public class DictItemServiceImpl extends ServiceImpl<DictItemDao, DictItem, Integer> implements DictItemService {

	@Autowired
	public void setDao(DictItemDao dao) {
		this.dao = dao;
	}

	@Override
	public List<DictItem> findList(DictItem di) {
		if(StringUtils.isBlank(di.getDictCode())) {
			return new ArrayList<>();
		}
		
		return null;
	}

	@Override
	public Pagination<DictItem> findListPage(DictItem di, Pagination<DictItem> p) {
		if(StringUtils.isBlank(di.getDictCode())) {
			return p;
		}
		return null;
	}
	

}
