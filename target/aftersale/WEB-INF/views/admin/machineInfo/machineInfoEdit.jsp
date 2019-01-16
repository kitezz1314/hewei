<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$('#province').combobox({
	url:'${path}/city/province',
	editable:false,//不可编辑状态 
	cache: false,
	// panelHeight: 'auto',//自动高度适合
	valueField:'code',
	textField:'note',            
	onHidePanel: function(){
		$("#city").combobox('setValue','');
		$("#county").combobox('setValue','');
		//$("#cregicounty").val('');
		var province = $('#province').combobox('getValue');
		if(province!=''){
			$.ajax({
				type: "POST",
				url: "${path}/city/city?province="+province,
				cache: false,
				dataType : "json",
				success: function(data){
					$("#city").combobox("loadData",data);
				}
			});
		}
	}
});

$('#city').combobox({
	editable:false, //不可编辑状态 
	cache: false,  
	//panelHeight: 'auto',//自动高度适合 
	valueField:'code', 
	textField:'note',
	onHidePanel: function(){
		//$("#cregicounty").val('');
		$("#county").combobox('setValue','');
		var city = $('#city').combobox('getValue');  
		if(city!=''){
			$.ajax({
				type: "POST",
				url: "${path}/city/county?city="+city,
				cache: false,
				dataType : "json",
				success: function(data){
					$("#county").combobox("loadData",data);
				}
			});
		}
	}
});
$('#county').combobox({
	editable:false,//不可编辑状态 
	cache: false,
	// panelHeight: 'auto',//自动高度适合
	valueField:'code',  
	textField:'note',
	onHidePanel: function(){
		var str=$('#county').combobox('getText');
		//$("#cregicounty").val(str);
	}
});
$('#type').combobox({
	url:'${path}/machinecode/type',
	editable:false,//不可编辑状态 
	cache: false,
	// panelHeight: 'auto',//自动高度适合
	valueField:'code',
	textField:'note',            
	onHidePanel: function(){
		$("#number").combobox("setValue",'');
		var type = $('#type').combobox('getValue');
		if(type!=''){
			$.ajax({
				type: "POST",
				url: "${path}/machinecode/number?type="+type,
				cache: false,
				dataType : "json",
				success: function(data){
					$("#number").combobox("loadData",data);
				}
			});
		}
	}
});

$('#number').combobox({
	editable:false,//不可编辑状态 
	cache: false,
	// panelHeight: 'auto',//自动高度适合
	valueField:'code',  
	textField:'note',
	onHidePanel: function(){
		var str=$('#number').combobox('getText');
		//$("#cregicounty").val(str);
	}
});

    $(function() {
        $('#machineInfoEditForm').form({
            url : '${path}/machineInfo/edit',
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
                    var form = $('#machineInfoEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
        
       
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="machineInfoEditForm" method="post">
            <table class="grid">
                <tr>
	    			<td>机器品牌:</td>
	    			<td>
	    				<input name="machineType"  id="type" value="${machineInfo.machineType}" >
	    			</td>
	    			<td>机器型号:</td>
	    			<td>
	    				<input name="machineNumber"  id="number" value="${machineInfo.machineNumber}" >
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>机器所属省:</td>
	    			<td><input name="province"  id="province"  value="${machineInfo.province}"></td>
	    			<td>机器所属市:</td>
	    			<td><input   name="city" id="city" value="${machineInfo.city}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>机器所属县/区:</td>
	    			<td><input name="county"  id="county" value="${machineInfo.county}" ></td>
	    			<td>机器所属网点:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="netName" value="${machineInfo.netName}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>安装软件名称:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="softwareName" value="${machineInfo.softwareName}"></input></td>
	    			<td>安装软件版本:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="softwareVersion" value="${machineInfo.softwareVersion}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>安装目录:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="installDir" value="${machineInfo.installDir}"></input></td>
	    			
	    			
	    			<td>机具序列号:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="machineId" value="${machineInfo.machineId}"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>安装人姓名:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="installer" value="${machineInfo.installer}"></input></td>
	    			<td>安装人联系方式:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="installerPhone" value="${machineInfo.installerPhone}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>使用人姓名:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="operator" value="${machineInfo.operator}"></input></td>
	    			<td>使用人联系方式:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="operatorPhone" value="${machineInfo.operatorPhone}"></input></td>
	    		</tr>
	    		<tr>
	    			<td>项目负责人:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="principle" value="${machineInfo.principle}"></input></td>
	    			<td>负责人联系方式:</td>
	    			<td><input class="easyui-validatebox span2" data-options="required:true" name="principlePhone" value="${machineInfo.principlePhone}"></input></td>
	    		</tr>
	    		
            </table>
        </form>
    </div>
</div>