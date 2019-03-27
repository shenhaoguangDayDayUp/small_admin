<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/common.jsp">
    <jsp:param name="title" value="用户"/>
</jsp:include>
<link rel="stylesheet" href="${_staticPath}/custom/zTree/css/metroStyle/metroStyle.css?v=1"/>
<link rel="stylesheet" href="${_staticPath}/custom/zTree/css/metroStyle/metroStyle.custom.css"/>

<div class="row">
    <div class="col-xs-2">
        <ul id="_tree" class="ztree" style="max-height:75vh"></ul>
    </div>
    <div class="col-xs-10">
        <div class="widget-box">
            <div class="widget-header widget-header-small">
                <h5 class="widget-title lighter">搜索栏</h5>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <form id="_form" class="form-inline">
                        <label>
                            <label class="control-label" for="form-field-1"> 登录名： </label>
                            <input name="account" type="text" class="form-data input-medium search-data">
                        </label>
                        <label>
                            <label class="control-label" for="form-field-1"> 姓名： </label>
                            <input name="realName" type="text" class="form-data input-medium search-data">
                        </label>
                        <label>
                            <label class="control-label" for="form-field-1"> 手机号： </label>
                            <input name="mobile" type="text" class="form-data input-medium search-data">
                        </label>
                        <input name="orgId" type="hidden" id="_orgInp"/>
                        <a href="javascript:void(0);" id="_search" class="btn btn-sm btn-white btn-info btn-bold">
                            <i class="ace-icon fa fa-search blue bigger-120"></i>搜索
                        </a>
                    </form>
                </div>
            </div>
        </div>

        <div>
            <div class="dataTables_wrapper form-inline no-footer">
                <table id="_table" class="table table-striped table-bordered table-hover dataTable no-footer"></table>
            </div>
        </div>
    </div>
</div>
<a id="modalBtn" href="#my-modal" role="button" class="hide" data-toggle="modal"></a>
<div id="my-modal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="smaller lighter blue no-margin">人员权限配置</h3>
            </div>

            <div class="modal-body">
                <ul id="_dialogTree" class="ztree" style="max-height:60vh"></ul>
            </div>

            <div class="modal-footer">
                <button id="_cancel" class="btn btn-sm btn-danger pull-right" data-dismiss="modal" type="reset">
                    <i class="ace-icon fa fa-times"></i>关闭
                </button>
                <button id="_submit" class="btn btn-sm btn-success pull-right" data-dismiss="modal" data-loading-text="正在提交...">
                    <i class="ace-icon fa fa-check"></i>保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        jQuery(function ($) {
            var userId = '';
            // 列表
            var $table = $("#_table").table({
                url: "${_path}/admin/user/list",
                formId: "_form",
                tools: [
                    {
                        text: '新增',
                        clazz: 'btn-info',
                        icon: 'fa fa-plus-circle blue',
                        permission: '/admin/user/edit',
                        handler: function () {
                            $.aceRedirect("${_path}/admin/user/edit");
                        }
                    },
                    {
                        text: '禁用',
                        clazz: 'btn-warning',
                        icon: 'fa fa-lock orange',
                        permission: '/admin/user/enable',
                        handler: function () {
                            $table.ajaxEnable({url: "${_path}/admin/user/enable"}, false);
                        }
                    },
                    {
                        text: '启用',
                        clazz: 'btn-success',
                        icon: 'fa fa-unlock green',
                        permission: '/admin/user/enable',
                        handler: function () {
                            $table.ajaxEnable({url: "${_path}/admin/user/enable"}, true);
                        }
                    },
                    {
                        text: '重置密码',
                        clazz: 'btn-default',
                        icon: 'fa fa-key grey',
                        permission: '/admin/user/resetPassword',
                        handler: function () {
                            $table.ajax({
                                url: "${_path}/admin/user/resetPassword",
                                confirm: "确认重置密码?",
                                after: function () {
                                    $table.reload();
                                }
                            });
                        }
                    }
                ],
                columns: [
                    {field: 'id', hide: true},
                    {field: 'isEnable', hide: true},
                    {field: 'account', title: '登录名', align: 'left'},
                    {field: 'realName', title: '姓名', align: 'left'},
                    {field: 'sex', title: '性别', align: 'left', replace: function (d) {
                        return d.sex == 1 ? '男' : '女';
                    }},
                    {field: 'orgName', title: '组织', align: 'left'},
                    {field: 'mobile', title: '电话', align: 'left'},
                    {field: 'email', title: '邮箱', align: 'left'},
                    {field: 'userCode', title: '员工编号', align: 'left'},
                    {field: 'idCard', title: '身份证号', align: 'left'},
                    {field: 'dingUserId', title: '钉钉号', align: 'left'},
                    {field: 'lastLoginTime', title: '最后登录时间', mobileHide: true},
                    {
                        field: 'isEnableStr', title: '是否启用', replace: function (d) {
                            return "<span class='label label-sm label-" + (d.isEnable ? "success" : "warning") + "'>" + d.isEnableStr + "</span>";
                        }
                    },
                    {field: 'createTime', title: '创建时间', mobileHide: true}
                ],
                operate: [
                    {
                        text: '修改', clazz: 'blue', icon: 'fa fa-pencil', permission: '/admin/user/edit', handler: function (d, i) {
                            $.aceRedirect("${_path}/admin/user/edit?id=" + d.id);
                        }
                    },
                    {
                        text: '禁用', clazz: 'orange', icon: 'fa fa-lock', permission: '/admin/user/enable',
                        handler: function () {
                            $table.ajaxEnable({url: "${_path}/admin/user/enable"}, false);
                        },
                        show: function (d) {
                            return d.isEnable;
                        }
                    },
                    {
                        text: '启用', clazz: 'green', icon: 'fa fa-unlock', permission: '/admin/user/enable',
                        handler: function () {
                            $table.ajaxEnable({url: "${_path}/admin/user/enable"}, true);
                        },
                        show: function (d) {
                            return !d.isEnable;
                        }
                    },
                    {
                        text: '重置密码',
                        clazz: 'grey',
                        icon: 'fa fa-key',
                        permission: '/admin/user/resetPassword',
                        handler: function (d, i) {
                            $table.ajax({
                                url: "${_path}/admin/user/resetPassword",
                                confirm: "确认重置密码?",
                                after: function () {
                                    $table.reload();
                                }
                            });
                        }
                    },
                    {
                        text: '数据权限',
                        clazz: 'grey',
                        icon: 'fa fa-eye-slash',
                        permission: '/admin/user/dataRange',
                        handler: function (d, i) {
                            $('#modalBtn').click();
                            userId = d.id;
                            dialogTreeObj = $.fn.zTree.init($("#_dialogTree"), getDiaSetting());
                        }
                    }
                ],
                after: function () {
                    // 权限处理
                    $.permission();
                }
            });

            //搜索
            $("#_search").click(function () {
                $table.search();
            });

            // 取消
            $("#_cancel").click(function () {
                $table.search();
            });

            function onClick(e, treeId, treeNode) {
                $('#_orgInp').val(treeNode.id);
                $table.search();
            }

            var setting = {
                view: {
                    selectedMulti: false
                },
                async: {
                    enable: true,
                    contentType: "application/x-www-form-urlencoded",
                    type: "get",
                    dataType: "text",
                    url: "${_path}/admin/org/nodes"
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: onClick,
                    onAsyncSuccess: function () {
                        treeObj.expandAll(true);
                    }
                },
            };

            //树菜单初始化
            var treeObj = $.fn.zTree.init($("#_tree"), setting);
            //树菜单初始化
            var dialogTreeObj = null;
            // 初始选中数据
            var oldList = [];
            function getDiaSetting () {
                return {
                    view: {
                        selectedMulti: false
                    },
                    async: {
                        enable: true,
                        contentType: "application/x-www-form-urlencoded",
                        type: "get",
                        otherParam: {userId: userId},
                        dataType: "text",
                        url: "${_path}/admin/user/dataRange",
                        dataFilter: function (treeId, parentNode, res) {
                            oldList = [];
                            var data = res.data;
                            for (var i in data) {
                                var item = data[i];
                                if (item.checked) {
                                    oldList.push(item.userId);
                                }
                            }
                            return data;
                        }
                    },
                    check: {
                        enable: true,
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onAsyncSuccess: function () {
                            dialogTreeObj.expandAll(true);
                        }
                    }
                }
            }

            //点击保存按钮
            $("#_submit").click(function () {
                var btn = $(this);
                btn.button('loading');
                var ns = dialogTreeObj.getCheckedNodes();
                var isUpdData = ns.length === oldList.length;
                var ar = [];
                for (var i in ns) {
                    ar.push(ns[i].userId);
                }
                // 长度一样判断内容
                var flg;
                if (isUpdData) {
                   for (var j in ar) {
                       flg = true;
                       for (var k in oldList) {
                           if (ar[j] === oldList[k]) {
                               flg = false;
                               break;
                           }
                       }
                       if (flg) {
                           isUpdData = false;
                           break
                       }
                   }
                }

                $.post("${_path}/admin/userUser/save", {
                    chargeUserId: userId,
                    userIds: ar.join(','),
                    isUpdData: !isUpdData
                }, function (d) {
                    if (d) {
                        btn.button('reset');
                        $.gritter.add({
                            text: d.message,
                            sticky: false,
                            time: '1000'
                        });
                    }
                }, 'json');
            });

            // 取消
            $("#_cancel").click(function () {
            });
        });
    });
</script>