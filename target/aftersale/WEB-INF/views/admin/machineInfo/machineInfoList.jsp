<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var machineInfoDataGrid;
    $(function() {
        machineInfoDataGrid = $('#machineInfoDataGrid').datagrid({
        url : '${path}/machineInfo/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'machineId',
        sortName : 'machineId',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '100',
            title : '机器序列号',
            field : 'machineId',
            sortable : true
        }, {
            width : '100',
            title : '机器品牌',
            field : 'machineType',
            sortable : true,
        }, {
            width : '80',
            title : '机器型号',
            field : 'machineNumber',
            sortable : true,
        },{
            width : '100',
            title : '所属省',
            field : 'province',
            sortable : true,
        },{
            width : '100',
            title : '所属市',
            field : 'city',
            sortable : true,
        },{
            width : '120',
            title : '所属区/县',
            field : 'county',
            sortable : true,
        },{
            width : '120',
            title : '安装软件名称',
            field : 'softwareName',
            sortable : true,
        },{
            width : '100',
            title : '安装软件版本',
            field : 'softwareVersion',
            sortable : true,
        },{
            width : '100',
            title : '安装目录',
            field : 'installDir',
            sortable : true,
        },{
            width : '120',
            title : '安装时间',
            field : 'installTime',
            sortable : true,
        },{
            width : '80',
            title : '安装人',
            field : 'installer',
            sortable : true,
        },{
            width : '100',
            title : '安装人联系方式',
            field : 'installerPhone',
            sortable : true,
        },{
            width : '100',
            title : '使用人',
            field : 'operator',
            sortable : true,
        },{
            width : '100',
            title : '使用人联系方式',
            field : 'operatorPhone',
            sortable : true,
        },{
            width : '100',
            title : '项目负责人',
            field : 'principle',
            sortable : true,
        },{
            width : '100',
            title : '负责人联系方式',
            field : 'principlePhone',
            sortable : true,
        },{
            width : '120',
            title : '机器所属网点',
            field : 'netName',
            sortable : true,
        },{
            field : 'action',
            title : '操作',
            width : 330,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/machineInfo/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="machineInfo-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="machineInfoEditFun(\'{0}\');" >编辑</a>', row.machineId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/machineInfo/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="machineInfo-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="machineInfoDeleteFun(\'{0}\');" >删除</a>', row.machineId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/machineInfo/adderror">
                	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                	str += $.formatString('<a href="javascript:void(0)" class="machineInfo-easyui-linkbutton-adderror" data-options="plain:true,iconCls:\'fi-plus icon-green\'" onclick="machineInfoAdderrorFun(\'{0}\');" >问题录入</a>', row.machineId);
            	</shiro:hasPermission>
            	 <shiro:hasPermission name="/machineInfo/addclean">
             	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
             	str += $.formatString('<a href="javascript:void(0)" class="machineInfo-easyui-linkbutton-addclean" data-options="plain:true,iconCls:\'fi-plus icon-green\'" onclick="machineInfoAddcleanFun(\'{0}\');" >清洁录入</a>', row.machineId);
         	</shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.machineInfo-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.machineInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
            $('.machineInfo-easyui-linkbutton-adderror').linkbutton({text:'问题录入'});
            $('.machineInfo-easyui-linkbutton-addclean').linkbutton({text:'清洁录入'});
        },
        toolbar : '#machineInfoToolbar'
    });
});

    /**
     * 问题录入
     * @param url
     */
    function machineInfoAdderrorFun(machineId) {
        parent.$.modalDialog({
            title : '添加',
            width : 600,
            height : 400,
            href : '${path}/machineProblems/addPage?machineId='+machineId,
            buttons : [ {
                text : '确定',
                handler : function() {
                	parent.$.modalDialog.openner_dataGrid = machineInfoDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#machineProblemsAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
     * 清洁录入
     * @param url
     */
    function machineInfoAddcleanFun(machineId) {
        parent.$.modalDialog({
            title : '添加',
            width : 550,
            height : 250,
            href : '${path}/clean/addPage?machineId='+machineId,
            buttons : [ {
                text : '确定',
                handler : function() {
                	 parent.$.modalDialog.openner_dataGrid = machineInfoDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#cleanAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
	/**
	 * 添加框
	 * @param url
	 */
	function machineInfoAddFun() {
	    parent.$.modalDialog({
	        title : '添加',
	        width : 550,
	        height : 400,
	        href : '${path}/machineInfo/addPage',
	        buttons : [ {
	            text : '确定',
	            handler : function() {
	                parent.$.modalDialog.openner_dataGrid = machineInfoDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
	                var f = parent.$.modalDialog.handler.find('#machineInfoAddForm');
	                f.submit();
	            }
	        } ]
	    });
	}


/**
 * 编辑
 */
function machineInfoEditFun(machineId) {
    if (machineId == undefined) {
        var rows = machineInfoDataGrid.datagrid('getSelections');
        machineId = rows[0].machineId;
    } else {
        machineInfoDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 550,
        height : 400,
        href :  '${path}/machineInfo/editPage?machineId=' + machineId,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = machineInfoDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#machineInfoEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function machineInfoDeleteFun(machineId) {
     if (machineId == undefined) {//点击右键菜单才会触发这个
         var rows = machineInfoDataGrid.datagrid('getSelections');
         machineId = rows[0].machineId;
     } else {//点击操作里面的删除图标会触发这个
         machineInfoDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前机具？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/machineInfo/delete', {
                 machineId : machineId
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     machineInfoDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function machineInfoCleanFun() {
    $('#machineInfoSearchForm input').val('');
    machineInfoDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function machineInfoSearchFun() {
     machineInfoDataGrid.datagrid('load', $.serializeObject($('#machineInfoSearchForm')));
}

//导入文件  
function upload()  
{     
   //获取上传文件控件内容。根据实际情况需要file对象的获取方式有如下两种方法，filebox_file_id_这个是easyui自己封装的文件上传id，可以在  
   //jquery.easyui.min.js这个js文件中搜到.所以你在input里面写的id=“aa”是没有实际意义的，故用如下两种方式获取。  
  //<span style="color: rgb(255, 102, 102);"> </span>
  var file = $('input[name="fileimport"][type="file"]').prop('files')[0];  
   /*var file = document.getElementById('filebox_file_id_1').files[0];*/  
   //判断控件中是否存在文件内容，如果不存在，弹出提示信息，阻止进一步操作  
  if (file == null) { alert('错误，请选择文件'); return; }  
   //获取文件名称  
   var fileName = file.name; 
  
   //获取文件类型名称  
   var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length); 
   //这里限定上传文件文件类型必须为.xlsx，如果文件类型不符，提示错误信息  
   if (file_typename == '.xlsx')  
   {  
       //获取form数据  
       var formData = new FormData($("#importFileForm")[0]);  
       alert(formData);
       //调用apicontroller后台action方法，将form数据传递给后台处理。contentType必须设置为false,否则chrome和firefox不兼容  
       $.ajax({  
           url:'${path}/machineInfo/upload',  
           type: 'POST',  
           data: formData,   
           async: false,  
           cache: false,  
           processData: false,  
           success: function (result) {  
               //上传成功后将控件内容清空，并显示上传成功信息  
        	   parent.$.messager.alert('成功', eval(result.msg), 'info');
           },  
           error: function (result) {  
               //上传失败时显示上传失败信息  
        	   $.messager.alert('错误', eval(result.msg), 'error');
           }  
       });  
   }  
   else {   
         //将错误信息显示在前端label文本中  
       $.messager.alert('错误', "文件类型错误", 'error');
   }  
}  
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="machineInfoSearchForm">
            <table>
                <tr>
                    <th>机具序列号:</th>
                    <td><input name="machineId" placeholder="机具序列号"/></td>
                    <th>项目负责人:</th>
                    <td><input name="principle" placeholder="项目负责人"/></td>
                    <th>机器所属网点:</th>
                    <td><input name="netName" placeholder="机器所属网点"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="machineInfoSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="machineInfoCleanFun();">清空</a>
                        <%-- <a href="${path}/machineInfo/download" class="easyui-linkbutton" data-options="iconCls:'fi-download',plain:true">下载模板</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <form id='importFileForm' method="post" enctype="multipart/form-data">
							<input id="fileimport"  name="fileimport" class="easyui-filebox" data-options="buttonText:'选择文件',editable:false" />
						</form>
                       <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-upload',plain:true" onclick="upload();">上传数据</a> --%>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="machineInfoDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="machineInfoToolbar" style="display: none;">
    <shiro:hasPermission name="/machineInfo/add">
        <a onclick="machineInfoAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>