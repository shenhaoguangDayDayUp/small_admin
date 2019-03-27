package com.smart.sso.server.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.config.ConfigUtils;
import com.smart.mvc.exception.ValidateException;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.model.ResultCode;
import com.smart.mvc.util.StringUtils;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.client.SessionUser;
import com.smart.sso.client.SessionUtils;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.dto.OrgUserDto;
import com.smart.sso.server.enums.TrueFalseEnum;
import com.smart.sso.server.model.Role;
import com.smart.sso.server.model.User;
import com.smart.sso.server.model.UserRole;
import com.smart.sso.server.provider.PasswordProvider;
import com.smart.sso.server.service.RoleService;
import com.smart.sso.server.service.SysOptionService;
import com.smart.sso.server.service.UserRoleService;
import com.smart.sso.server.service.UserService;
import com.smart.sso.server.service.UserUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Joe
 */
@Api(tags = "用户管理")
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private UserUserService userUserService;
	@Resource
	private SysOptionService sysOptionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/user";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		User user;
		if (id == null) {
			user = new User();
			// 初始化员工编号
			user.setUserCode(userService.genUserCode());
		} else {
			user = userService.get(id);
		}
		model.addAttribute("user", user);
		model.addAttribute("roleList", getRoleList(id));
		return "/admin/userEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(@ApiParam(value = "登录名") String account, @ApiParam(value = "组织Id") Integer orgId,
			@ApiParam(value = "姓名") String realName, @ApiParam(value = "手机") String mobile,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		Pagination<User> userList;
		if (orgId != null && orgId.intValue() > 1) {
			userList = userService.findPagination(orgId, account, realName, mobile,
					new Pagination<User>(pageNo, pageSize));
		} else {
			userList = userService.findPagination(null, account, realName, mobile,
					new Pagination<User>(pageNo, pageSize));
		}
		return Result.createSuccessResult().setData(userList);
	}

	@ApiOperation("验证登录名")
	@RequestMapping(value = "/validateAccount", method = RequestMethod.POST)
	public @ResponseBody Result validateAccount(@ApiParam(value = "id") Integer id,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account) {
		Result result = Result.createSuccessResult();
		User user = userService.findByAccount(account);
		if (null != user && !user.getId().equals(id)) {
			result.setCode(ResultCode.ERROR).setMessage("登录名已存在");
		}
		return result;
	}

	/*
	 * @ApiOperation("验证用户编号")
	 * 
	 * @RequestMapping(value = "/validateUserCode", method = RequestMethod.POST)
	 * public @ResponseBody Result validateUserCode(
	 * 
	 * @ApiParam(value = "id") Integer id,
	 * 
	 * @ApiParam(value = "用户编号", required = true) @ValidateParam({
	 * Validator.NOT_BLANK }) String userCode) { Result result =
	 * Result.createSuccessResult(); User user =
	 * userService.findByUserCode(userCode); if (null != user &&
	 * !user.getId().equals(id)) {
	 * result.setCode(ResultCode.ERROR).setMessage("用户编号已存在"); } return result; }
	 */

	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(HttpServletRequest request,
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		SessionUser sessionUser = SessionUtils.getSessionUser(request);
		userService.enable(isEnable, getAjaxIds(ids), sessionUser.getUserId(), new Date());
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@ApiResponse(response = Result.class, code = 200, message = "success")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(HttpServletRequest request, @ApiParam(value = "id") Integer id,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account,
			@ApiParam(value = "密码 ") String password, @ApiParam(value = "应用Id ") Integer appId,
			@ApiParam(value = "性别 ") String sex, @ApiParam(value = "员工编号 ") String userCode,
			@ApiParam(value = "组织Id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer orgId,
			@ApiParam(value = "姓名") String realName, @ApiParam(value = "身份证号") String idCard,
			@ApiParam(value = "手机") String mobile, @ApiParam(value = "邮箱") String email,
			@ApiParam(value = "钉钉号") String dingUserId,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ApiParam(value = "上级组织ids") String leaderOrgIds, @ApiParam(value = "下级组织ids") String lowereOrgIds,
			@ApiParam(value = "是否更新角色") Boolean isUpdRole, @ApiParam(value = "角色ids") String roleIds) {
		SessionUser sessionUser = SessionUtils.getSessionUser(request);
		User user;
		Boolean isNewUser = false;
		User userTemp = userService.findByUserCode(userCode);
		if (id == null) {
			if (StringUtils.isBlank(password)) {
				throw new ValidateException("密码不能为空");
			}
			user = new User();
			user.setCreateUserId(sessionUser.getUserId());
			user.setAppId(appId);
			user.setCreateTime(new Date());
			user.setOrgId(orgId);
			// TODO 设置数据权限
			isNewUser = true;
			if (userTemp != null) {
				// 若是用户编号已经存在则重新生成一个
				user.setUserCode(userService.genUserCode());
			} else {
				user.setUserCode(userCode);
			}
		} else {
			user = userService.get(id);
			user.setUpdateUserId(sessionUser.getUserId());
			user.setUpdateTime(new Date());
			Boolean isUpdUserUser = false;
			if (!user.getOrgId().equals(orgId)) {
				// 更改部门
				isUpdUserUser = true;
			}
			if (isUpdRole) {
				// 角色更新了
				isUpdUserUser = true;
			}
			if (isUpdUserUser) {
				// TODO 更新数据权限
				saveDataRange(false, appId, id, leaderOrgIds, lowereOrgIds);
			}
		}
		user.setAccount(account);
		user.setRealName(realName);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setSex(sex);
		user.setIdCard(idCard);
		user.setDingUserId(dingUserId);
		user.setOrgId(orgId);
		if (StringUtils.isNotBlank(password)) {
			user.setPassword(PasswordProvider.encrypt(password));
		}
		user.setIsEnable(isEnable);
		userService.save(user, getAjaxIds(roleIds));
		if (isNewUser) {
			saveDataRange(true, orgId, user.getId(), leaderOrgIds, lowereOrgIds);
		}
		return Result.createSuccessResult();
	}

	@ApiOperation("重置密码")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody Result resetPassword(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.resetPassword(PasswordProvider.encrypt(ConfigUtils.getProperty("system.reset.password")),
				getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	private List<Role> getRoleList(Integer userId) {
		List<Role> list = roleService.findByAll(TrueFalseEnum.TRUE.getValue());
		if (userId != null) {
			for (Role role : list) {
				UserRole userRole = userRoleService.findByUserRoleId(userId, role.getId());
				if (null != userRole) {
					role.setIsChecked(true);
				} else {
					role.setIsChecked(false);
				}
			}
		}
		return list;
	}

	@ApiOperation("数据权限")
	@RequestMapping(value = "/dataRange", method = RequestMethod.GET)
	public @ResponseBody Result dataRange(
			@ApiParam(value = "userId", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer userId) {
		List<OrgUserDto> resultList = userUserService.findAllNodes(userId);
		return Result.createSuccessResult().setData(resultList);
	}

	@Transactional
	private int saveDataRange(Boolean isNewUser, Integer orgId, Integer userId, String leaderOrgIds,
			String lowereOrgIds) {
		List<Integer> userIdList = Arrays.asList(userId);
		// 判断角色权限
		List<Role> roleList = roleService.findListByUserId(userId, null);
		Boolean isSuperAdmin = false;
		Boolean isOrgAdmin = false;
		if (!CollectionUtils.isEmpty(roleList)) {
			for (Role role : roleList) {
				if ("superadmin".equals(role.getBusiCode())) {
					isSuperAdmin = true;
				} else if ("orgadmin".equals(role.getBusiCode())) {
					isOrgAdmin = true;
				}
			}
		}
		// 超级管理员不用设置权限
		if (isSuperAdmin) {
			return 0;
		}
		if (!isNewUser) {
			userUserService.deleteByChargeUserIds(userIdList);
			userUserService.deleteByUserIds(userIdList);
		}
		if (StringUtils.isNotBlank(leaderOrgIds)) {
			// 上级被管理
			List<User> leaderList = userService.findByOrgId(getAjaxIds(leaderOrgIds), null);
			if (!CollectionUtils.isEmpty(leaderList)) {
				for (User userTemp : leaderList) {
					userUserService.allocate(false, userTemp.getId(), userIdList);
				}

			}
		}
		List<User> lowereList = new ArrayList<User>();
		if (StringUtils.isNotBlank(lowereOrgIds)) {
			// 下级管理
			lowereList = userService.findByOrgId(getAjaxIds(lowereOrgIds), null);
		}

		if (isOrgAdmin) {
			// 若是部门管理员，则管理本部门所有人
			List<User> sameOrgUser = userService.findByOrgId(Arrays.asList(orgId), null);
			if (!CollectionUtils.isEmpty(sameOrgUser)) {
				User user = userService.get(userId);
				lowereList.addAll(sameOrgUser);
			}
		} else {
			// 若不是部门管理员,则只自己管理自己
			User user = userService.get(userId);
			lowereList.add(user);
		}

		if (!CollectionUtils.isEmpty(lowereList)) {
			StringBuffer lowerUserIds = new StringBuffer("");
			for (User userTemp : lowereList) {
				lowerUserIds.append(userTemp.getId());
				lowerUserIds.append(",");
			}
			if (lowerUserIds.length() > 1) {
				lowerUserIds.subSequence(0, lowerUserIds.length() - 2);
			}
			userUserService.allocate(false, userId, getAjaxIds(lowerUserIds.toString()));
		}
		return 0;
	}
	
	// 获取所有用戶
	@ApiOperation("获取所有用戶")
	@RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
	public @ResponseBody List<OrgUserDto> findAllUsers(String optionId, String optionCode, String state) {
		List<OrgUserDto> list = userService.findAllUsers(true);
		return list;
	}

	// 获取所有来源渠道
	@ApiOperation("获取所有来源渠道")
	@RequestMapping(value = "/getOption", method = RequestMethod.GET)
	public @ResponseBody List<Object> getOption(String optionId, String optionCode, String state) {
		List<Object> list = sysOptionService.getOption(Integer.valueOf(optionId), optionCode, Integer.valueOf(state));
		return list;
	}
}