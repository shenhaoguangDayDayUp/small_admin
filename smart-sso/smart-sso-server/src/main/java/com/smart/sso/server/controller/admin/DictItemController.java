package com.smart.sso.server.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.model.ResultCode;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.sso.server.controller.common.BaseController;
import com.smart.sso.server.model.DictItem;
import com.smart.sso.server.service.DictItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 系统字典控制层-Controller
 * @createAuthor Eugene.Chen
 * @createDate 2019-04-14
 * @modifyAuthor Eugene.Chen
 * @modifyDate 2019-04-14
 */
@Api(tags = "系统字典管理")
@Controller
@RequestMapping("/admin/dictItem")
public class DictItemController extends BaseController {

	@Resource
	private DictItemService dictItemService;

	/**
	 * 查询业务字典列表——不分页
	 * @param dictItemDesc 字典项描述
	 * @param dictCode 字典类型编码（必填）
	 * @return
	 */
	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "字典项描述") String dictItemDesc,
			@ApiParam(value = "字典类型编码", required = true) 
			@ValidateParam({ Validator.NOT_BLANK }) String dictCode) {
		try {
			DictItem di = new DictItem();
			di.setDictCode(dictCode);
			di.setDictItemDesc(dictItemDesc);
			List<DictItem> list = dictItemService.findList(di);
			return Result.createSuccessResult().setData(list);
		}catch(Exception e) {
			log.error("【系统字典】查询系统字典异常——不分页",e);
			Result result = Result.createSuccessResult();
			result.setCode(ResultCode.ERROR).setMessage("字典查询失败");
			return result;
		}
	}
	
	/**
	 * 查询业务字典列表——分页
	 * @param dictItemDesc 字典项描述
	 * @param dictCode 字典类型编码（必填）
	 * @return
	 */
	@ApiOperation("列表")
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public @ResponseBody Result listPage(
			@ApiParam(value = "字典项描述") String dictItemDesc,
			@ApiParam(value = "字典类型编码", required = true) 
			@ValidateParam({ Validator.NOT_BLANK }) String dictCode,
			@ApiParam(value = "开始页码") Integer pageNo,
			@ApiParam(value = "显示条数") Integer pageSize) {
		try {
			Pagination<DictItem> p = new Pagination<DictItem>();
			p.initPagination(pageNo, pageSize);
			DictItem di = new DictItem();
			di.setDictCode(dictCode);
			di.setDictItemDesc(dictItemDesc);
			Pagination<DictItem> pag = dictItemService.findListPage(di, p);
			return Result.createSuccessResult().setData(pag);
		}catch(Exception e) {
			log.error("【系统字典】查询系统字典异常——分页",e);
			Result result = Result.createSuccessResult();
			result.setCode(ResultCode.ERROR).setMessage("字典查询失败");
			return result;
		}
	}

}