<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="用户" />
</jsp:include>
<link rel="stylesheet"
	href="${_staticPath}/custom/zTree/css/metroStyle/metroStyle.css?v=1" />
<link rel="stylesheet"
	href="${_staticPath}/custom/zTree/css/metroStyle/metroStyle.custom.css" />
<style>
.menuContent {
	min-width: 100%;
	max-height: 180px;
	position: absolute;
	left: 0;
	top: 34px;
	background: #fefefe;
	border: 1px solid #617775;
	z-index: 101;
	overflow: auto;
}

.input-group-addon i {
	width: 14px;
}
</style>

<div class="page-header">
	<h1>${empty user.id ? '添加' : '修改'}用户</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form id="_editForm" class="form-horizontal" role="form"
			validate="true">
			<input type="hidden" name="id" value="${user.id}"> <input
				type="hidden" name="appId" value="1" />

			<div class="form-group">
				<label for="_userCode"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>用户编号</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_userCode" name="userCode" type="text"
								value="${user.userCode}" class="form-data" required
								minlength='4' maxlength='10' readonly /> <span
								class="input-group-addon"><i
								class="ace-icon fa fa-barcode"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_account"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>登录名</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_account" name="account" type="text"
								value="${user.account}" class="form-data"
								ajax="{url : '${_path}/admin/user/validateAccount', dataId : '_editForm'}"
								required minlength='4' maxlength='64' /> <span
								class="input-group-addon"> <i class="ace-icon fa fa-user"></i>
							</span>
						</div>
					</div>
				</div>

			</div>

			<div class="form-group">
				<label for="_password"
					class="col-sm-3 control-label no-padding-right">${empty user.id ? '<span
                        class="form-star">*</span>' : ''}密码</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_password" name="password" type="password" value=""
								class="form-data"
								${!empty user.id ? 'data-rel="tooltip" title="不修改请留空"' : ''}
								${empty user.id ? 'required="true"' : ''} minlength='6'
								maxlength='16' /> <span class="input-group-addon"><i
								class="ace-icon fa fa-lock"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_realName"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>姓名</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_realName" name="realName" type="text"
								value="${user.realName}" class="form-data" required
								minlength='2' maxlength='10' /> <span class="input-group-addon"><i
								class="ace-icon fa fa-user"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span
					class="form-star">*</span>性别</label>

				<div class="col-xs-12 col-sm-9">
					<div class="clearfix help-validate" style="padding-top: 7px">
						<label class="line-height-1 blue"> <input name="sex"
							value="1" type="radio" class="ace"
							${user.sex == 1 || !user.sex ?
                            'checked="checked"' : ''} />
							<span class="lbl"> 男</span>
						</label> <label class="line-height-1 blue"> <input name="sex"
							value="2" type="radio" class="ace"
							${user.sex == 2 ?
                            'checked="checked"' : ''} />
							<span class="lbl"> 女</span>
						</label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_idCard" class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>身份证号</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_idCard" name="idCard" type="text"
								value="${user.idCard}" class="form-data" required vtype="idCard" />
							<span class="input-group-addon"><i
								class="ace-icon fa fa-idCard"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_mobile" class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>电话</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_mobile" name="mobile" type="text"
								value="${user.mobile}" class="form-data" required vtype="mobile" />
							<span class="input-group-addon"><i
								class="ace-icon fa fa-mobile"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_dingUserId"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>钉钉</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_dingUserId" name="dingUserId" type="text"
								value="${user.dingUserId}" class="form-data" required /> <span
								class="input-group-addon"><i
								class="ace-icon fa fa-comment"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_email" class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>邮箱</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_email" name="email" type="text" value="${user.email}"
								class="form-data" required vtype="email" /> <span
								class="input-group-addon"> <i
								class="ace-icon fa fa-envelope"></i>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_org" class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>部门</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="citySel" class="form-data" type="text" readonly /> <input
								id="_org" name="orgId" type="hidden" required
								value="${user.orgId}" /> <input name="leaderOrgIds"
								type="hidden" /> <input name="lowerOrgIds" type="hidden" />
							<div id="menuContent" class="menuContent" style="display: none;">
								<ul id="_tree" class="ztree"></ul>
							</div>
							<span class="input-group-addon" id="orgIcon"> <i
								class="ace-icon fa fa-sitemap"></i>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right">角色</label>

				<div class="col-xs-12 col-sm-9">
					<div class="clearfix help-validate" style="padding-top: 7px">
						<c:forEach var="item" items="${roleList}">
							<label> <input name="roleId" value="${item.id}"
								type="checkbox" class="ace"
								${item.isChecked ?
                                'checked="checked"' : ''} />
								<span class="lbl">&nbsp;&nbsp;${item.name}</span>
							</label>
						</c:forEach>
						<input id="_roleIds" type="hidden" name="roleIds" value="">
						<input name="isUpdRole" type="hidden" value="true" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"><span
					class="form-star">*</span>是否启用</label>

				<div class="col-xs-12 col-sm-9">
					<div class="clearfix help-validate" style="padding-top: 7px">
						<label class="line-height-1 blue"> <input name="isEnable"
							value="true" type="radio" class="ace"
							${user.isEnable ?
                            'checked="checked"' : ''} />
							<span class="lbl"> 是</span>
						</label> <label class="line-height-1 blue"> <input name="isEnable"
							value="false" type="radio" class="ace"
							${!user.isEnable ?
                            'checked="checked"' : ''} />
							<span class="lbl"> 否</span>
						</label>
					</div>
				</div>
			</div>

			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button id="_submit" type="button" class="btn btn-info"
						data-loading-text="正在提交...">
						<i class="ace-icon fa fa-check bigger-110"></i> 提交
					</button>

					&nbsp; &nbsp; &nbsp;
					<button id="_cancle" class="btn" type="reset">F
						<i class="ace-icon fa  fa-times bigger-110"></i> 取消
					</button>
				</div>
			</div>
		</form>

	</div>
</div>

<script type="text/javascript">
	$('.page-content-area')
			.ace_ajax(
					'loadScripts',
					scripts,
					function() {

						jQuery(function($) {

							function onClick(e, treeId, treeNode) {
								var zTree = $.fn.zTree.getZTreeObj("_tree"), nodes = zTree
										.getSelectedNodes(), v = "";
								ids = "";
								nodes.sort(function compare(a, b) {
									return a.id - b.id;
								});
								for (var i = 0, l = nodes.length; i < l; i++) {
									v += nodes[i].name + ",";
									ids += nodes[i].id + ",";
								}
								if (v.length > 0)
									v = v.substring(0, v.length - 1);
								$("#citySel").val(v);
								// 将选中的id放到隐藏的文本域中
								if (ids.length > 0)
									ids = ids.substring(0, ids.length - 1);
								var treeids = $("#_org");
								treeids.attr("value", ids);
								hideMenu();
							}

							function showMenu() {
								$("#menuContent").show();

								$("body").bind("mousedown", onBodyDown);
							}

							function hideMenu() {
								$("#menuContent").fadeOut("fast");
								$("body").unbind("mousedown", onBodyDown);
							}

							function onBodyDown(event) {
								if (!(event.target.id == "menuBtn"
										|| event.target.id == "menuContent" || $(
										event.target).parents("#menuContent").length > 0)) {
									hideMenu();
								}
							}

							$('#citySel').click(showMenu);
							$('#orgIcon').click(showMenu);

							var setting = {
								view : {
									selectedMulti : false
								},
								async : {
									enable : true,
									contentType : "application/x-www-form-urlencoded",
									type : "get",
									dataType : "text",
									url : "${_path}/admin/org/nodes",
									dataFilter : function(treeId, parentNode,
											res) {
										var idx = -1;
										for ( var i in res) {
											if (res[i].orgLevel === 0) {
												idx = i;
												break;
											}
										}
										idx !== -1 && res.splice(idx, 1);
										return res
									}
								},
								data : {
									simpleData : {
										enable : true
									}
								},
								callback : {
									onClick : onClick,
									onAsyncSuccess : function() {
										treeObj.expandAll(true);
										var oldOrgIds = $('#_org').val() - 0;
										if (oldOrgIds) {
											var ns = treeObj
													.transformToArray(treeObj
															.getNodes());
											for ( var i in ns) {
												if (ns[i].id === oldOrgIds) {
													treeObj.selectNode(ns[i]);
													$("#citySel").attr("value",
															ns[i].name);
													break
												}
											}
										}
									}
								},
							};

							//树菜单初始化
							var treeObj = $.fn.zTree.init($("#_tree"), setting);

							//焦点
							$("#_account").focus();

							//帮助查看
							$('[data-rel=tooltip]').tooltip({
								container : 'body'
							});

							// 获取子节点ids
							var cNodes = [];

							function getChildren(c) {
								if (c) {
									for (var i = 0, len = c.length; i < len; i++) {
										cNodes.push(c[i].id);
										getChildren(c[i].children);
									}
								}
							}

							// 获取树结构参数
							function getTreeIds() {

								var sNodes = treeObj.getSelectedNodes();

								cNodes = [];

								getChildren(sNodes[0].children);

								$('[name=lowerOrgIds]').val(cNodes.join(','));

								var pNodes = sNodes[0].getPath();
								// 父节点ids
								var pids = [];
								for (var i = 0, len = pNodes.length - 1; i < len; i++) {
									pids.push(pNodes[i].id);
								}
								$('[name=leaderOrgIds]').val(pids.join(','));
							}

							// 获取权限
							function getRoles() {
								var roleIds = [];
								$("input[name='roleId']:checked").each(
										function(i, d) {
											roleIds.push($(this).val())
										});
								var s = roleIds.join(',');
								$("#_roleIds").val(s);
								return s;
							}

							// 获取权限
							var oldRoleIds = getRoles();

							// 提交
							$("#_submit")
									.click(
											function() {
												if ($('#_editForm').validate()) {

													// 获取权限
													var newRoleIds = getRoles();
													if (oldRoleIds === newRoleIds) {
														$('[name=isUpdRole]')
																.val('false');
													} else {
														$('[name=isUpdRole]')
																.val('true');
													}
													// 获取树结构参数
													getTreeIds();

													var btn = $(this);
													btn.button('loading');

													$
															.post(
																	"${_path}/admin/user/save",
																	$
																			.formJson('_editForm'),
																	function(d) {
																		if (d) {
																			btn
																					.button('reset');
																			if (d.code == 1) {
																				$
																						.aceRedirect("${_path}/admin/user");
																			} else {
																				$.gritter
																						.add({
																							text : d.message
																						});
																			}
																		}
																	}, 'json');
												}
											});

							// 取消
							$("#_cancle").click(function() {
								$.aceRedirect("${_path}/admin/user");
							});

							// 回车绑定
							$(".form-data").bind('keypress', function(event) {
								if (event.keyCode == "13") {
									event.preventDefault();
									$("#_submit").click();
								}
							});
						});
					});
</script>
