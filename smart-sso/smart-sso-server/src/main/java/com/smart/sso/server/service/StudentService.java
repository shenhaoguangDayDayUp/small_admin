package com.smart.sso.server.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.sso.server.model.Student;

/**
 * 学生服务接口
 * 
 * @author Frank_King
 */
public interface StudentService extends Service<Student, Integer> {
	
	/**
	 * 根据校区/学生姓名/手机查询分页列表
	 * @param campus 校区
	 * @param name 学生姓名
	 * @param mobile 手机
	 * @param pageNo 分页起始
	 * @param pageSize 分页记录数
	 * @return
	 */
	public Pagination<Map<String,Object>> findPagination(String campus,String name,String mobile, Pagination<Map<String,Object>> p);
	
	/**
	 * 根据名字查询
	 * @param name 名字
	 * @return
	 */
	public Student findByName(String name);
	/**
	 * 根据手机号查询
	 * @param mobile 手机号
	 * @return
	 */
	public Student findByMobile(String mobile);
	/**
	 * 根据校区查询
	 * @param campus 校区
	 * @return
	 */
	public List<Student> findByCampus(String campus);
	
	public void deleteById(List<Integer> idList);
	
	public List<Student> findByChargeStudentId(Integer chargeStudentId);
	
	public Map<String,Object> findStuDetail(Integer id);
}
