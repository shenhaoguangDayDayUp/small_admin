<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="学生列表" />
</jsp:include>

<div class="row">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<div class="widget-box">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">搜索栏</h5>
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<form id="_form" class="form-inline">
								<label> <label class="control-label" for="form-field-1">
										学生姓名： </label> <input name="name" type="text"
									class="form-data input-medium search-data">
								</label> <label> <label class="control-label" for="form-field-1">
										电话： </label> <input name="mobile" type="text"
									class="form-data input-medium search-data">
								</label> <label> <label class="control-label" for="form-field-1">
										校区： </label> <input name="campus" type="text"
									class="form-data input-medium search-data">
								</label> <a href="javascript:void(0);" id="_search"
									class="btn btn-sm btn-white btn-info btn-bold"> <i
									class="ace-icon fa fa-search blue bigger-120"></i>搜索
								</a>
							</form>
						</div>
					</div>
				</div>

				<div>
					<div class="dataTables_wrapper form-inline no-footer">
						<table id="_table"
							class="table table-striped table-bordered table-hover dataTable no-footer">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('.page-content-area').ace_ajax('loadScripts',scripts,function() {
						jQuery(function($) {
							// 列表
							var $table = $("#_table").table({
												url : "${_path}/admin/student/list",
												formId : "_form",
												tools : [
													{text : '新增', clazz : 'btn-info', icon : 'fa fa-plus-circle blue', permission : '/admin/student/edit', handler : function(){
														$.aceRedirect("${_path}/admin/student/edit");
													}},
													{text : '删除', clazz : 'btn-danger', icon : 'fa fa-trash-o red', permission : '/admin/student/delete', handler : function(){
														$table.ajaxDelete({
															confirm : "确认要删除?",
															url : "${_path}/admin/student/delete"
														});
													}}
												],
												columns : [ {
													field : 'id',
													hide : true
												},{
													field : 'name',
													title : '学生姓名',
													align : 'left'
												}, {
													field : 'parentName',
													title : '家长姓名',
													align : 'left',
													mobileHide : true
												}, {
													field : 'relationship',
													title : '家长关系',
													align : 'left'
												}, {
													field : 'birthday',
													title : '学生出生日期',
													align : 'left'
												}, {
													field : 'mobile',
													title : '家长电话',
													align : 'left'
												}, {
													field : 'address',
													title : '家庭住址',
													align : 'left'
												}, {
													field : 'campus',
													title : '校区',
													align : 'left'
												},{
													field : 'option_value',
													title : '渠道来源',
													align : 'left'
												}, {
													field : 'realName',
													title : '录入人',
													align : 'left'
												} ],
												operate : [
														{
															text : '修改',
															clazz : 'blue',
															icon : 'fa fa-pencil',
															permission : '/admin/student/edit',
															handler : function(d, i) {
																$.aceRedirect("${_path}/admin/student/edit?id="
																				+ d.id);
															}
														},
														{
															text : '删除',
															clazz : 'red',
															icon : 'fa fa-trash-o',
															permission : '/admin/student/delete',
															handler : function(d, i) {
																$table.ajaxDelete({
																			confirm : "确认要删除?",
																			url : "${_path}/admin/student/delete"
																		});
															}
														} ],
												after : function() {
													// 权限处理
													$.permission();
												}
											});

							//搜索
							$("#_search").click(function() {
								$table.search();
							});

						});
					});
</script>
