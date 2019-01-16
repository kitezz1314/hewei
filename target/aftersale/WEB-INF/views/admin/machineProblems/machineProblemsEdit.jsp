<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$(".fit").change(function(){
	if($("input[name='fit']:checked").val()=="use"){
		$("#xlh").css('display','block'); 
		$("#fitId").css('display','block'); 
		$("#mc").css('display','block'); 
		$("#fitName").css('display','block'); 
	}else if($("input[name='fit']:checked").val()=="nouse"){
		$("#fitId").val("");
		$("#fitName").val(""); 
		$("#xlh").css('display','none'); 
		$("#fitId").css('display','none'); 
		$("#mc").css('display','none'); 
		$("#fitName").css('display','none'); 
	}
});

    $(function() {
        $('#machineProblemsEditForm').form({
            url : '${path}/machineProblems/edit',
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
                    var form = $('#machineProblemsEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        $("#questionStatus").val('${machineProblems.questionStatus}');
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="machineProblemsEditForm" method="post">
            <table class="grid">
            	<tr>
            	<td>问题编号:</td>
	    			<td><input type="text" style="width:136px;height:17px;" name="problemId" value="${machineProblems.problemId}" readonly></input></td>
            	</tr>
                <tr>
	    			<td>机具号:</td>
	    			<td><input type="text" style="width:136px;height:17px;" name="machineId" id="machine_Id" value="${machineProblems.machineId}" readonly></input></td>
	    			<td>问题状态:</td>
	    			<td><select id="questionStatus" name="questionStatus" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="已解决">已解决</option>
                            <option value="未解决">未解决</option>
                    </select></td>
	    		</tr>
	    		
	    		
	    		<tr>
	    			<td>问题表现:</td>
	    			<td><textarea rows="4" cols="17" name="questionBehave" >${machineProblems.questionBehave}</textarea></td>
	    			<td>解决问题方法:</td>
	    			<td><textarea rows="4" cols="17" name="questionSolve">${machineProblems.questionSolve}</textarea></td>
	    		</tr>
	    		
	    		
	    		 
            </table>
        </form>
    </div>
</div>