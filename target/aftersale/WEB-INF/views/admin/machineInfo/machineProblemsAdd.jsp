<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

//
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
        $('#machineProblemsAddForm').form({
            url : '${path}/machineProblems/add',
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
                    var form = $('#machineProblemsAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="machineProblemsAddForm" method="post">
            <table class="grid">
                <tr>
	    			<td>机具号:</td>
	    			<td><input type="text" style="width:136px;height:17px;" name="machineId" id="machine_Id" value="${machineId}" readonly></input></td>
	    			<td>问题出现时间:</td>
	    			<td><input class="easyui-datetimebox" name="beginTime"></input></td>
	    		</tr>
	    		<tr>
	    			<td>售后人员:</td>
	    			<td><input type="text" style="width:136px;height:17px;" name="seller"></input></td>
	    			<td>售后联系方式:</td>
	    			<td><input type="text" style="width:136px;height:17px;" name="sellerPhone"></input></td>
	    		</tr>
	    		<tr>
	    			<td>问题表现:</td>
	    			<td><textarea rows="4" cols="17" name="questionBehave"></textarea></td>
	    			<td>解决问题方法:</td>
	    			<td><textarea rows="4" cols="17" name="questionSolve"></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>是否上门服务:</td>
	    			<td>需要：
						<input type="radio" checked="checked" name="upDoor" value="need" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						不需要：
						<input type="radio" name="upDoor" value="noneed" />
                    </td>
	    		</tr>
	    		<tr>
	    			<td>是否零配件:</td>
	    			<td>使用：
						<input type="radio" class="fit"  name="fit" value="use" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						没使用：
						<input type="radio" class="fit" checked="checked" name="fit" value="nouse" />
                    </td>
	    		</tr>
	    		<tr>
	    		<div id ="fitradio">
	    			<td><lable id="xlh" style="display:none;">配件序列号:</label></td>
	    			<td><input id ="fitId" type="text" style="width:136px;height:17px;display:none;" name="fitId"></input></td>
	    			<td><lable id="mc" style="display:none;">配件名称:</lable></td>
	    			<td><input id="fitName" type="text" style="width:136px;height:17px; display:none;" name="fitName"></input></td>
	    		</div>
	    		</tr> 
            </table>
        </form>
    </div>
</div>