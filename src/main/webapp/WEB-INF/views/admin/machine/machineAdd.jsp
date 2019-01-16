<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#machineAddForm').form({
            url : '${path}/machine/add',
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
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#machineAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="machineAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>型号编号</td>
                    <td><input name="code" type="text" placeholder="请输入机器编号" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>型号描述</td>
                    <td><input name="note" type="text" placeholder="请输入机器描述" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>型号编码</td>
                    <td><input name="pycode" type="text" placeholder="请输入机器拼音编码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr> 
            </table>
        </form>
    </div>
</div>