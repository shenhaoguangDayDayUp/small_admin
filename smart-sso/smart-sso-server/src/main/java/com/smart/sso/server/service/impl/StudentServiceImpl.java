package com.smart.sso.server.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.server.dao.StudentDao;
import com.smart.sso.server.model.Student;
import com.smart.sso.server.service.StudentService;



@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student, Integer> implements StudentService {
	@Resource
	private StudentService studentService;
	
	@Autowired
	public void setDao(StudentDao dao) {
		this.dao = dao;
	}
	@Override
	public Pagination<Map<String,Object>> findPagination(String campus, String name, String mobile, Pagination<Map<String,Object>> p) {
		dao.findPagination(campus, name, mobile, p);
		return p;
	}

	public void save(Student t) {
		super.save(t);
	}

	@Override
	public Student findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Student findByMobile(String mobile) {
		return dao.findByMobile(mobile);
	}

	@Override
	public List<Student> findByCampus(String campus) {
		return dao.findByCampus(campus);
	}

	@Override
	public List<Student> findByChargeStudentId(Integer chargeStudentId) {
		return findByChargeStudentId(chargeStudentId);
	}
	
	@Transactional
	public void deleteById(List<Integer> idList) {
		studentService.deleteById(idList);
		verifyRows(dao.deleteById(idList), idList.size(), "用户数据库删除失败");
	}
	@Override
	public Map<String, Object> findStuDetail(Integer id) {
		return this.dao.findStuDetail(id);
	}

}
