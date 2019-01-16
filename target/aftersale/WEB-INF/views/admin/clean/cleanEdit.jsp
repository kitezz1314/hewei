<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#cleanEditForm').form({
            url : '${path}/clean/edit',
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
                    var form = $('#cleanEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
        $("#cleanType").val('${clean.cleanType}'); 
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="cleanEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>机具号：</td>
                    <td><input name="machineId" type="text" placeholder="机具号" class="easyui-validatebox span2" data-options="required:true" value="${clean.machineId }"></td>
               
                <td>清洁编号：</td>
                    <td><input name="cleanId" type="text" placeholder="清洁编号" class="easyui-validatebox span2" data-options="required:true" value="${clean.cleanId }"></td>
                   
                </tr>
                <tr>
                    <td>项目负责人：</td>
                    <td><input name="by1" type="text" placeholder="项目负责人" class="easyui-validatebox span2" data-options="required:true" value="${clean.by1 }"></td>
                
                
                <td>清洁类型:</td>
	    			<td><select id="cleanType" name="cleanType" class="easyui-combobox" data-options="width:140,height:23,editable:false,panelHeight:'auto'">
                            <option value="0" selected>清洁卡清洁</option>
                            <option value="1">清洁打印头</option>
                            <option value="2">清洁滚轮</option>
                    </select></td>
                </tr>
                <tr>
               
	    		 <td>清洁人：</td>
                    <td><input name="cleaner" type="text" placeholder="清洁人" class="easyui-validatebox span2" data-options="required:true" value="${clean.cleaner }"></td>
	    		</tr>
            </table>
        </form>
    </div>
</div>