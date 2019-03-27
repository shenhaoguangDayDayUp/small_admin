package com.smart.sso.server.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.service.UserUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Yangxinxia
 */
@Api(tags = "用户与用户关系管理")
@Controller
@RequestMapping("/admin/userUser")
public class UserUserController extends BaseController {


	
	@Resource
	private UserUserService userUserService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String edit(
			@ApiParam(value = "管理人员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer chargeUserId, Model model) {
		model.addAttribute("chargeUserId", chargeUserId);
		return "/admin/userUser";
	}
	
	@ApiOperation("数据授权提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "管理人员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer chargeUserId,
			@ApiParam(value = "用户ids") String userIds,
			@ApiParam(value = "是否更新数据")  Boolean isUpdData) {
		if(isUpdData) {
			userUserService.allocate(true,chargeUserId, getAjaxIds(userIds));
		}
		return Result.createSuccessResult().setMessage("授权成功");
	}
}