package com.smart.sso.server.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.client.SessionUser;
import com.smart.sso.client.SessionUtils;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.model.Student;
import com.smart.sso.server.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Frank_King
 */
@Api(tags = "学生管理")
@Controller
@RequestMapping("/admin/student")
public class StudentController extends BaseController {

	@Resource
	private StudentService studentService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/student";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Map<String,Object> studentMap = null;
		if (id == null) {
			studentMap = new HashMap<>();
			// 初始化员工编号
		} else {
			studentMap = studentService.findStuDetail(id);
		}
		model.addAttribute("student", studentMap);
		return "/admin/studentEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(@ApiParam(value = "学生姓名") String name, @ApiParam(value = "所在校区") String campus,
			@ApiParam(value = "手机") String mobile,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		Pagination<Map<String, Object>> studentList;
		studentList = studentService.findPagination(campus, name, mobile,
				new Pagination<Map<String, Object>>(pageNo, pageSize));
		return Result.createSuccessResult().setData(studentList);
	}

	// required = true异常处理，不能为空,如果是空值,就需要提示的,后面还应该有IsRequiredMessage=请输入XX内容!
	// 2019.3.21
	@ApiOperation("新增/修改提交")
	@ApiResponse(response = Result.class, code = 200, message = "success")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(HttpServletRequest request, @ApiParam(value = "id") Integer id,
			@ApiParam(value = "学生姓名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "校区 ") String campus, @ApiParam(value = "家长姓名") String parentName,
			@ApiParam(value = "性别 ") String sex, @ApiParam(value = "家长关系 ") String relationship,
			@ApiParam(value = "学生生日") String birthday,
			@ApiParam(value = "手机") @ValidateParam({ Validator.NOT_BLANK }) String mobile,
			@ApiParam(value = "家庭地址") String address, @ApiParam(value = "来源渠道") String channelSources,
			@ApiParam(value = "销售录入人") Integer headId, @ApiParam(value = "创建人") Integer create_by,
			@ApiParam(value = "创建时间") Date create_time, @ApiParam(value = "更新人") Integer update_by,
			@ApiParam(value = "更新时间") Date update_time) throws ParseException {
		SessionUser sessionstudent = SessionUtils.getSessionUser(request);
		Student student;
		if (id == null) {
			student = new Student();
		} else {
			student = studentService.get(id);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		student.setAddress(address);
		student.setBirthday(sdf.parse(birthday));
		student.setCampus(campus);
		student.setChannel_sources(channelSources);
		student.setHead_id(headId);
		student.setMobile(mobile);
		student.setName(name);
		student.setParentName(parentName);
		student.setRelationship(relationship);
		student.setSex(sex);

		// TODO 设置数据权限

		if (id != null) {
			student.setUpdate_by(sessionstudent.getUserId());
			student.setUpdate_time(new Date());
		} else {
			student.setCreate_by(sessionstudent.getUserId());
			student.setCreate_time(new Date());
		}
		studentService.save(student);
		return Result.createSuccessResult();
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		studentService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}

}