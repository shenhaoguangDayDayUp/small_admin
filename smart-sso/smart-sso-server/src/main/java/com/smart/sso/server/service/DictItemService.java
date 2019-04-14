package com.smart.sso.server.service;

import java.util.List;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.sso.server.model.DictItem;

public interface DictItemService extends Service<DictItem, Integer>  {
	
	/**
	 * 根据字典类型编码查询业务字典——不分页
	 * @param di
	 * @return
	 */
	List<DictItem> findList(DictItem di);
	
	/**
	 * 根据字典类型编码查询业务字典——分页
	 * @param di
	 * @param p
	 * @return
	 */
	Pagination<DictItem> findListPage(DictItem di, Pagination<DictItem> p);
	
}
