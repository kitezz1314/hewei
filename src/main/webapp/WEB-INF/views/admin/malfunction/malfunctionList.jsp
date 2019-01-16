<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<link rel="stylesheet" href="//res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
    var malfunctionDataGrid;
    $(function() {
    	
        malfunctionDataGrid = $('#malfunctionDataGrid').datagrid({
        url : '${path}/malfunction/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '60',
            title : '编号',
            field : 'id',
            sortable : true
        },{
            width : '100',
            title : '机具号',
            field : 'machineId',
            sortable : true
        }, {
            width : '100',
            title : '网点名称',
            field : 'netName'
        },{
            width : '100',
            title : '问题描述',
            field : 'questionDes'
        },{
            width : '100',
            title : '报修人电话',
            field : 'phone'
        },{
            width : '130',
            title : '预约时间',
            field : 'reserveTime',
            sortable : true
        },{
            width : '70',
            title : '问题图片1',
            field : 'image1',
            formatter : function(value, row) {
                var str="";
                if(value!=""||value!=null){
                	//str="<img style=\"height:80px;width:150px;\" src=\"${path}/malfunction/getImage1?id="+row.id+"\"/>";
                	str="<img style=\"height:80px;width:150px;\" onclick=\"enlarge(this)\" src=\"${path}/static/wechatImg/"+row.id+"_01.jpg\"/>";
                	//str="<img style=\"height:80px;width:150px;\" src=\"${path}/static/wechatImg/my.jpg\"/>";
                	return str;
                }
            }
        },{
            width : '70',
            title : '问题图片2',
            field : 'image2',
            formatter : function(value, row) {
                var str="";
                if(value!=""||value!=null){
                	str="<img style=\"height:80px;width:150px;\" onclick=\"enlarge(this)\" src=\"${path}/static/wechatImg/"+row.id+"_02.jpg\"/>";
                	return str;
                }
            }
        },{
            width : '70',
            title : '问题图片3',
            field : 'image3',
            formatter : function(value, row) {
                var str="";
                if(value!=""||value!=null){
                	str="<img style=\"height:80px;width:150px;\" onclick=\"enlarge(this)\" src=\"${path}/static/wechatImg/"+row.id+"_03.jpg\"/>";
                	return str;
                }
            }
        },{
            width : '60',
            title : '状态',
            field : 'status',
            sortable : true,
            formatter : function(value, row, index) {
                switch (value) {
                case "0":
                    return '未派单';
                case "1":
                    return '已派单';
                case "2":
                    return '在途中';
                case "3":
                    return '维修中';
                case "4":
                    return '已完成';
               
                }
            }
        }, {
            field : 'action',
            title : '操作',
            width : 80,
            formatter : function(value, row, index) {
                var str = '';
                if(row.status=='0'){
                	<shiro:hasPermission name="/malfunction/updatestatus1">
                    	str += $.formatString('<a href="javascript:void(0)" class="malfunction-easyui-linkbutton-edit1" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="malfunctionEditFun(\'{0}\');" >派单</a>', row.id);
                	</shiro:hasPermission>
                }else if(row.status=='1'){
                	/* <shiro:hasPermission name="/malfunction/updatestatus2">
                		str += $.formatString('<a href="javascript:void(0)" class="malfunction-easyui-linkbutton-edit2" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="malfunctionEditFun(\'{0}\',\'{1}\');" >已派单->在途中</a>', row.id,row,status);
            		</shiro:hasPermission> */
                	str+='已派单';
                }else if(row.status=='2'){
                	/* <shiro:hasPermission name="/malfunction/updatestatus3">
           				str += $.formatString('<a href="javascript:void(0)" class="malfunction-easyui-linkbutton-edit3" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="malfunctionEditFun(\'{0}\',\'{1}\');" >在途中->维修中</a>', row.id,row.status);
        			</shiro:hasPermission> */
                	str+='在途中';
                }else if(row.status=='3'){
                /* 	<shiro:hasPermission name="/malfunction/updatestatus4">
        				str += $.formatString('<a href="javascript:void(0)" class="malfunction-easyui-linkbutton-edit4" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="malfunctionEditFun(\'{0}\',\'{1}\');" >维修中->已完成</a>', row.id,row.status);
    				</shiro:hasPermission> */
                	str+='维修中';
                }else if(row.status=='4'){
                	str+='已完成';
                }
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	
            $('.malfunction-easyui-linkbutton-edit1').linkbutton({text:'派单'});
           /*  $('.malfunction-easyui-linkbutton-edit2').linkbutton({text:'已派单->在途中'});
            $('.malfunction-easyui-linkbutton-edit3').linkbutton({text:'在途中->维修中'});
            $('.malfunction-easyui-linkbutton-edit4').linkbutton({text:'维修中->已完成'}); */
            
        },
        toolbar : '#malfunctionToolbar'
    });
});

    /*图片放大*/
    function enlarge(dom){
        var nowImgurl = $(dom).attr("src");
        $('#dlg').dialog('open');
        document.getElementById("dlg").innerHTML="<img style=\"height:500px;width:400px;\" src=\""+nowImgurl+"\"/>";
    }
    
/**
 * 添加框
 * @param url
 */
function malfunctionAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/malfunction/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = malfunctionDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#malfunctionAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function malfunctionEditFun(id) {
    if (id == undefined) {
        var rows = malfunctionDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        malfunctionDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 300,
        height : 200,
        href :  '${path}/malfunction/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = malfunctionDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#malfunctionEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function malfunctionDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = malfunctionDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         malfunctionDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/malfunction/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     malfunctionDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function malfunctionCleanFun() {
    $('#malfunctionSearchForm input').val('');
    malfunctionDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function malfunctionSearchFun() {
     malfunctionDataGrid.datagrid('load', $.serializeObject($('#malfunctionSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="malfunctionSearchForm">
            <table>
                <tr>
                    <th>名称:</th>
                    <td><input name="name" placeholder="搜索条件"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="malfunctionSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="malfunctionCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
 <div id="dlg" class="easyui-dialog" title="图片预览" data-options="iconCls:'icon-save',closed: 'true'" style="width:405px;height:533px">
		
	</div>
 
    <div data-options="region:'center',border:false">
        <table id="malfunctionDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div> 
<div id="malfunctionToolbar" style="display: none;">
    <shiro:hasPermission name="/malfunction/add">
        <a onclick="malfunctionAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>