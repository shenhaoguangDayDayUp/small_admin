package com.smart.sso.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.sso.server.model.Student;

/**
 * 学生持久化接口
 * 
 * @author Frank_King
 */
public interface StudentDao extends Dao<Student, Integer> {

	/* 分頁 */
	public List<Map<String,Object>> findPagination(@Param("campus") String campus, @Param("name") String name,
			@Param("mobile") String mobile, Pagination<Map<String,Object>> p);

	public Student findByName(@Param("name") String name);

	public Student findByMobile(@Param("mobile") String mobile);

	public List<Student> findByCampus(@Param("campus") String campus);

	public List<Student> findByChargeStudentId(@Param("chargeStudentId") Integer chargeStudentId);

	public Map<String,Object> findStuDetail(Integer id);
}
