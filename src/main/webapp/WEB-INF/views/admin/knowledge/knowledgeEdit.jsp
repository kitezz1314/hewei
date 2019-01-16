<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#knowledgeEditForm').form({
            url : '${path}/knowledge/edit',
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
                    var form = $('#knowledgeEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
       
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="knowledgeEditForm" method="post">
            <table class="grid" align="center">
                <tr align="center">
                   
                    <td><input name="knowledgeId" type="hidden"  value="${knowledge.knowledgeId}">
                     问题描述：<input name="knowledgeName" type="text" placeholder="请输入名称" class="easyui-validatebox" data-options="required:true" value="${knowledge.knowledgeName}"></td>
                </tr>
                <tr align="center">
                    <td>解决方案</td>
                    </tr>
                <tr align="center">
                        <td><textarea rows="30" cols="80" name="content">${knowledge.content}</textarea></td>
                    
                </tr>
            </table>
        </form>
    </div>
</div>