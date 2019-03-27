package com.smart.sso.server.controller.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.model.Result;
import com.smart.mvc.model.ResultCode;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.model.Org;
import com.smart.sso.server.model.User;
import com.smart.sso.server.service.OrgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.smart.sso.server.service.UserService;

/**
 * @author Yangxinxia
 */
@Api(tags = "权限(含菜单)管理")
@Controller
@RequestMapping("/admin/org")
public class OrgController extends BaseController {

	@Resource
	private OrgService orgService;
	
	@Resource
	private UserService userService;
	

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/org";
	}

	@ApiOperation("权限树节点")
	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody List<Org> nodes(
		   @ApiParam(value = "父id") Integer parentId,
		   @ApiParam(value = "是否启用 ") Boolean isEnable) {
		if(parentId!=null&&parentId.intValue()>1) {
			return orgService.findByAll(parentId,isEnable,null);
		}
		else {
			return orgService.findByAll(null,isEnable,null);
		}
	
	}
	
	@ApiOperation("组织列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
		   @ApiParam(value = "父id") Integer parentId,
		   @ApiParam(value = "是否启用 ") Boolean isEnable,
		   @ApiParam(value = "组织名称 ") String name) {
		if(parentId!=null&&parentId.intValue()>1) {
			return Result.createSuccessResult().setData(orgService.findByAll(parentId,isEnable,name));
		}
		else {
			return Result.createSuccessResult().setData(orgService.findByAll(null,isEnable,name));
		}
	
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "父id", required = true) Integer parentId,
			@ApiParam(value = "名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			@ApiParam(value = "业务编码") String busiCode,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Result result = Result.createSuccessResult();
		Org org;
		if (id == null) {
			org = new Org();
			org.setCreateTime(new Date());
			Org parentOrg=orgService.get(parentId);
			Integer orgLevel=parentOrg.getOrgLevel();
			if(orgLevel==4) {
				result.setCode(ResultCode.ERROR).setMessage("组织架构最多4层");
				return result;
			}
			org.setOrgLevel(orgLevel+1);
		}
		else {
			org = orgService.get(id);
			org.setCreateTime(new Date());
		}
		org.setParentId(parentId);
		org.setName(name);
		org.setSort(sort);
		org.setBusiCode(busiCode);
		org.setIsEnable(isEnable);
		orgService.save(org);
		return Result.createSuccessResult().setMessage("保存成功");
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
		   @ApiParam(value = "id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer id) {
		List<User> userList=userService.findByOrgId(Arrays.asList(id), null);
		Result result = Result.createSuccessResult();
		
		if(userList!=null&&userList.size()>0) {
			result.setCode(ResultCode.ERROR).setMessage("该组织下有用户");
			return result;
		}
		List<Org> orgList=orgService.findByParentId(id);
		if(orgList!=null&&orgList.size()>0) {
			result.setCode(ResultCode.ERROR).setMessage("该组织下有子节点");
			return result;
		}
		
		orgService.deleteById(id);
		return Result.createSuccessResult().setMessage("删除成功");
	}
}