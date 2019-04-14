package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.sso.server.model.DictItem;

public interface DictItemDao extends Dao<DictItem, Integer>{
	/**
	 * 根据条件查询
	 * 
	 * @param  DictItem
	 * @return
	 */
	List<DictItem> getByEntity(DictItem dictItem);
	
	/**
	 * 根据id集合查询数据
	 * 
	 * @param  DictItem
	 * @return
	 */
	List<DictItem> selectByIdList(@Param("list") List<Integer> idList);

	
	/**
	 * 根据字典类型编码查询业务字典——不分页
	 * @param dictCode
	 * @return
	 */
	List<DictItem> findList(DictItem dictItem);
	
	/**
	 * 根据字典类型编码查询业务字典——分页
	 * @param dictCode
	 * @return
	 */
	Pagination<DictItem> findListPage(@Param("dictItem")DictItem dictItem,
			Pagination<DictItem> p);
}