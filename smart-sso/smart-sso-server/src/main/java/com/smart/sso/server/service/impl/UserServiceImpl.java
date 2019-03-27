package com.smart.sso.server.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.model.ResultCode;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.sso.rpc.UserRpcDto;
import com.smart.sso.server.dao.UserDao;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.enums.TrueFalseEnum;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.UserRole;
import com.smart.sso.server.provider.PasswordProvider;
import com.smart.sso.server.service.AppService;
import com.smart.sso.server.service.SysOptionService;
import com.smart.sso.server.service.UserRoleService;
import com.smart.sso.server.service.UserService;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User, Integer> implements UserService {
	
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private AppService appService;

	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	public Result login(String ip, String account, String password) {
		Result result = Result.createSuccessResult();
		User user = findByAccount(account);
		if (user == null) {
			result.setCode(ResultCode.ERROR).setMessage("登录名不存在");
		}
		else if (!user.getPassword().equals(password)) {
			result.setCode(ResultCode.ERROR).setMessage("密码不正确");
		}
		else if (TrueFalseEnum.FALSE.getValue().equals(user.getIsEnable())) {
			result.setCode(ResultCode.ERROR).setMessage("已被用户禁用");
		}
		else {
			user.setLastLoginIp(ip);
			user.setLoginCount(user.getLoginCount() + 1);
			user.setLastLoginTime(new Date());
			dao.update(user);
			result.setData(user);
		}
		return result;
	}

	public void enable(Boolean isEnable, List<Integer> idList,Integer updateUserId,Date updateTime) {
		verifyRows(dao.enable(isEnable, idList,updateUserId,updateTime), idList.size(), "用户数据库更新失败");
	}
	
	public void save(User t) {
		super.save(t);
	}

	public void resetPassword(String password, List<Integer> idList) {
		verifyRows(dao.resetPassword(password, idList), idList.size(), "用户密码数据库重置失败");
	}

	public Pagination<User> findPagination(Integer orgId,String account,String realName,String mobile, Pagination<User> p) {
		dao.findPagination(orgId,account,realName,mobile, p);
		return p;
	}
	
	public User findByAccount(String account) {
		return dao.findByAccount(account);
	}
	
	@Transactional
	public void deleteById(List<Integer> idList) {
		userRoleService.deleteByUserIds(idList);
		verifyRows(dao.deleteById(idList), idList.size(), "用户数据库删除失败");
	}

	@Override
	public void updatePassword(Integer id, String newPassword) {
		User user = get(id);
		user.setPassword(PasswordProvider.encrypt(newPassword));
		update(user);
	}
	

	@Override
	public void save(User user, List<Integer> roleIdList) {
		save(user);
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		UserRole bean;
		for (Integer roleId : roleIdList) {
			bean = new UserRole();
			bean.setUserId(user.getId());
			bean.setRoleId(roleId);
			userRoleList.add(bean);
		}
		userRoleService.allocate(user.getId(), userRoleList);
	}

	@Override
	public List<User> findByOrgId(List<Integer> orgIdList, Boolean isEnable) {
		return dao.findByOrgId(orgIdList, isEnable);
	}

	@Override
	public List<User> findByOrgBusiCode(String busiCode, Boolean isEnable) {
		return dao.findByOrgBusiCode(busiCode, isEnable);
	}
	
	
	@Override
	public List<User> findByChargeUserId(Integer chargeUserId) {
		return dao.findByChargeUserId(chargeUserId);
	}
	public List<OrgUserDto> findAllUsers(Boolean isEnable){
		return dao.findAllUsers(isEnable);
	}

	@Override
	public User findByUserCode(String userCode) {
		return dao.findByUserCode(userCode);
	}

	
	@Override
	public List<User> findByIds(List<Integer> idList) {
		return dao.findByIds(idList);
	}

	@Override
	public List<UserRpcDto> findByRoleBusiCode(String[] roleBusiCodes, Integer exceptUserId, String realName,Boolean isEnable) {
		return dao.findByRoleBusiCode(roleBusiCodes, exceptUserId, realName, isEnable);
	}

	@Override
	public List<UserRpcDto> findByUserInfo(String[] roleBusiCodes, Integer orgId, String realName, String userCode,
			String mobile) {
		return dao.findByUserInfo(roleBusiCodes, orgId, realName, mobile, userCode);
	}

	@Override
	public List<UserRpcDto> findByUserIdsAndParams(List<Integer> idList, Map param) {
		return dao.findByUserIdsAndParams(idList,param);
	}

	@Override
	public String genUserCode() {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		Integer year=calendar.get(Calendar.YEAR);
		String userCodePrifix=String.valueOf(year).substring(2);
		Integer totalCnt=dao.countByUserCodePrifix(userCodePrifix);
		String userCode=userCodePrifix+String.format("%03d", totalCnt+1);
		return userCode;
	}

	@SuppressWarnings("null")
	public List<Object> getOption(Integer optionId, String optionCode, Integer state) {
		SysOptionService sysOption = null;
		return sysOption.getOption(optionId, optionCode, state);
	}
}
