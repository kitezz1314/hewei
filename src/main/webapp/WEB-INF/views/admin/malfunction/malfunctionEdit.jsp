<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	$("#by2").combobox({
            url : '${path }/user/combobox', //可以使本地json文件，也可以是后台返回json的url[{"id":"..","text":".."}]
            valueField : "loginName",
            textField : "name"
        });
    	
        $('#malfunctionEditForm').form({
            url : '${path}/malfunction/edit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#malfunctionEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="malfunctionEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>网点名称：</td>
                    <td><input name="id" type="hidden"  value="${malfunction.id}">
                    <input name="netName" type="text" placeholder="请输入名称" class="easyui-validatebox" data-options="required:true" value="${malfunction.netName}"></td>
                </tr>
                <tr>
                    <td>维修人：</td>
                    <td >
                       <input id="by2" name="by2">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>