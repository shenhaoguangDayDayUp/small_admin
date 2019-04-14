package com.smart.sso.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.sso.server.model.Dict;

public interface DictDao extends Dao<Dict, Integer>{
	/**
	 * 根据条件查询
	 * 
	 * @param  Dict
	 * @return
	 */
	List<Dict> getByEntity(Dict dict);
	
	
	
	/**
	 * 根据id集合查询数据
	 * 
	 * @param  Dict
	 * @return
	 */
	List<Dict> selectByIdList(@Param("list") List<Integer> idList);

}