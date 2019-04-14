<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
	<jsp:param name="title" value="学生" />
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
	<h1>${empty student.id ? '添加' : '修改'}学生</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form id="_editForm" class="form-horizontal" role="form"
			validate="true">
			<input type="hidden" name="id" value="${student.id}"> 
			<input type="hidden" name="appId" value="1" />

			<div class="form-group">
				<label for="_name" class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>学生姓名</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_name" name="name" type="text"
								value="${student.name}" class="form-data" required
								minlength='2' maxlength='10' /> <span class="input-group-addon">
								<i class="ace-icon fa fa-child"></i>
							</span>
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
							${student.sex == 1 || !student.sex ?
                            'checked="checked"' : ''} />
							<span class="lbl"> 男</span>
						</label> <label class="line-height-1 blue"> <input name="sex"
							value="2" type="radio" class="ace"
							${student.sex == 2 ?
                            'checked="checked"' : ''} />
							<span class="lbl"> 女</span>
						</label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_campus" class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>所在校区</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_campus" name="campus" type="text"
								value="${student.campus}" class="form-data" required
								minlength='2' maxlength='10' /> <span class="input-group-addon"><i
								class="ace-icon fa fa-university"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_parentName"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star"></span>家长姓名</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_parentName" name="parentName" type="text"
								value="${student.parentName}" class="form-data" required
								minlength='2' maxlength='10' /> <span class="input-group-addon"><i
								class="ace-icon fa fa-user"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_relationship"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star"></span>家长关系</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_relationship" name="relationship" type="text"
								value="${student.relationship}" class="form-data" required
								minlength='2' maxlength='10' /> <span class="input-group-addon"><i
								class="ace-icon fa fa-slideshare"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_birthday"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star"></span>学生出生日期</label>
				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
		            	<input type="text" name="date1" id="date1" class="form-control" style="width:159px;">
		            	 <input type="hidden" id = "submitDate" name="submitDate" class="form-control" />
		            	 <span class="input-group-addon">
									<i class="ace-icon fa fa-birthday-cake"></i></span>
						</div>
	            	</div>
				</div>
			<%-- 	<div class="col-sm-9">
					<div class="input-medium help-validate" data-date="12-02-2012"
						data-date-format="dd-mm-yyyy">
						<div class="input-group">
							<input id="_birthday" name="birthday" type="text"
								value="${student.birthday}" readonly class="form_datetime" /> 
								<span class="input-group-addon">
								<i class="ace-icon fa fa-birthday-cake"></i></span>
						</div>
					</div>
				</div> --%>
			</div>

			<div class="form-group">
				<label for="_mobile" class="col-sm-3 control-label no-padding-right"><span
					class="form-star"></span>家长电话</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_mobile" name="mobile" type="text"
								value="${student.mobile}" class="form-data" required
								vtype="mobile" /> <span class="input-group-addon"><i
								class="ace-icon fa fa-mobile"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_address"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star"></span>家庭住址</label>

				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
							<input id="_address" name="address" type="text"
								value="${student.address}" class="form-data" required
								minlength='2' maxlength='10' /> <span class="input-group-addon"><i
								class="ace-icon fa fa-home"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_channel_Sources"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>来源渠道</label>
				
				<div class="col-sm-9">
					<div class="input-medium help-validate">
						<div class="input-group">
						<input type="hidden" value="${student.optionCode}" id="channelSourceCode"/>
							<select id="field" name="channelSources" data-live-search="true" required="required"  style="width:159px;">
								<option style='display: none'></option>
							</select> <span class="input-group-addon"><i
								class="ace-icon fa fa-paste"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="_head_id"
					class="col-sm-3 control-label no-padding-right"><span
					class="form-star">*</span>学生录入人</label>
				<div class="col-sm-9">					
					<div class="input-medium help-validate">
						<div class="input-group">
						<input type="hidden" value="${student.headId}" id="head_id_hidden"/>
							<select id="head_id" name="headId"  required="required"  style="width:159px;">
								<option style='display: none'></option>
							</select> <span class="input-group-addon"><i
								class="ace-icon fa fa-paste"></i></span>
						</div>
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
					<button id="_cancle" class="btn" type="reset">
						<i class="ace-icon fa  fa-times bigger-110"></i> 取消
					</button>
				</div>
			</div>
		</form>

	</div>
</div>

<script type="text/javascript">
	$.ajax({
		type : 'get',
		url : "${_path}/admin/user/findAllUsers",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		dataType : "json",
		data : {
			"pageNo" : 1,//模块ID
			"pageSize" : 10 //状态
		},
		success : function(data) {
			var tempIdStr = '';
			$("#head_id").append(tempIdStr);
			$.each(data, function(i, item) {
				var tempId = '<option  value="' + item.id + '">'
						+ item.name + '</option>';
					
				$("#head_id").append(tempId);
				$("#head_id").on("change", function(a, b, c) {
					
					$("#id").val($("#head_id option:selected").val());

					$("#name").val($("#head_id option:selected").text());

				})
			});
			// 更新 。 这一步很重要
			//		$('#field').selectpicker('refresh');
			//		$('#field').selectpicker('val', '2');
			var x = $("#head_id_hidden").val();
			$("#head_id").val(x);
		}
	});

	$.ajax({
		type : 'get',
		url : "${_path}/admin/dictItem/list",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		dataType : "json",
		data : {
			"dictCode" : 'src_ditch'//渠道来源字典编码
		},
		success : function(data) {
			var tempIdStr = '';
			$("#field").append(tempIdStr);
			$.each(data, function(i, item) {
				var tempId = '<option  value="' + item.dictItemCode + '">'
						+ item.dictItemDesc + '</option>';
				$("#field").append(tempId);
				$("#field").on("change", function(a, b, c) {
					$("#option_code").val($("#field option:selected").val());

					$("#option_value").val($("#field option:selected").text());
				})
			});
			var channelSourceCode = $("#channelSourceCode").val();
			console.log(channelSourceCode);
			$("#field").val(channelSourceCode);
			// 更新 。 这一步很重要
			//		$('#field').selectpicker('refresh');
			//		$('#field').selectpicker('val', '2');
		}
	});
	
	$('.page-content-area')
			.ace_ajax(
					'loadScripts',
					scripts,
					function() {
						jQuery(function($) {

							$("#queryDevice")
									.on(
											'shown.bs.select',
											function(e) {
												$('#queryDevice')
														.prev()
														.find("input")
														.keyup(
																function() {
																	$(
																			'#queryDevice')
																			.prev()
																			.find(
																					"input")
																			.attr(
																					'id',
																					"deviceInput"); //为input增加id属性
																	console
																			.log($(
																					'#deviceInput')
																					.val()); //获取输入框值输出到控制台
																	var deviceInput = $(
																			'#deviceInput')
																			.val();
																	var deviceStr = "";
																	for (var i = 0; i < 8; i++) {
																		deviceStr += "<option  data-icon='glyphicon glyphicon-heart' data-tokens='"+i+"'> 设备"
																				+ i
																				+ "</option>";
																	}
																	$(
																			"#queryDevice")
																			.html(
																					"");
																	$(
																			'#queryDevice')
																			.append(
																					deviceStr);
																	$(
																			'#queryDevice')
																			.selectpicker(
																					'refresh');
																})
											});

							//焦点
							$("#_name").focus();
							$(".form_datetime").datetimepicker({
								format : 'yyyy-mm-dd',
								language : 'zh-CN'
							});
							//帮助查看
							$('[data-rel=tooltip]').tooltip({
								container : 'body'
							});
							// 提交
							$("#_submit")
									.click(
											function() {
												if ($('#_editForm').validate()) {
													var btn = $(this);
													btn.button('loading');
													$
															.post(
																	"${_path}/admin/student/save",
																	$
																			.formJson('_editForm'),
																	function(d) {
																		if (d) {
																			btn
																					.button('reset');
																			if (d.code == 1) {
																				$
																						.aceRedirect("${_path}/admin/student");
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
								$.aceRedirect("${_path}/admin/student");
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
	
	$(function(){
		//渲染时间
		 $("input[name='date1']").daterangepicker(
	    		 {
	    	            singleDatePicker: true,//设置为单个的datepicker，而不是有区间的datepicker 默认false
	    	            showDropdowns: true,//当设置值为true的时候，允许年份和月份通过下拉框的形式选择 默认false
	    	            autoUpdateInput: false,//1.当设置为false的时候,不给与默认值(当前时间)2.选择时间时,失去鼠标焦点,不会给与默认值 默认true
	    	            timePicker24Hour : true,//设置小时为24小时制 默认false
	    	            timePicker : false,//可选中时分 默认false
	    	    		locale: {
	    	    			format: "YYYY-MM-DD",
	    	    			separator: " - ",
	    	    			daysOfWeek: ["日","一","二","三","四","五","六"],
	    	    			monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
	    	    		}
	    	    			
	    	     }
	     ).on('cancel.daterangepicker', function(ev, picker) {
	         $("#date1").val("请选择日期");
	         $("#submitDate").val("");
	     }).on('apply.daterangepicker', function(ev, picker) {
	     $("#submitDate").val(picker.startDate.format('YYYY-MM-DD'));
	     $("#date1").val(picker.startDate.format('YYYY-MM-DD'));
	 });
	});
</script>
