<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var machineProblemsDataGrid;
    $(function() {
        machineProblemsDataGrid = $('#machineProblemsDataGrid').datagrid({
        url : '${path}/machineProblems/dataGrid',
        striped : true,
        pagination : true,
        singleSelect : true,
        idField : 'problemId',
        sortName : 'problemId',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '60',
            title : '编号',
            field : 'problemId',
            sortable : true
        }, {
            width : '100',
            title : '机具号',
            field : 'machineId',
            sortable : true
        },{
            width : '100',
            title : '售后人员',
            field : 'seller',
            sortable : true
        },{
            width : '100',
            title : '售后人员电话',
            field : 'sellerPhone',
            sortable : true
        },{
            width : '100',
            title : '问题描述',
            field : 'questionBehave',
            sortable : true
        },{
            width : '100',
            title : '解决方案',
            field : 'questionSolve',
            sortable : true
        },{
            width : '100',
            title : '是否需要上门',
            field : 'upDoor',
            sortable : true
        },{
            width : '100',
            title : '是否使用配件',
            field : 'fit',
            sortable : true
        },{
            width : '100',
            title : '配件编号',
            field : 'fitId',
            sortable : true
        },{
            width : '100',
            title : '配件名称',
            field : 'fitName',
            sortable : true
        },{
            width : '100',
            title : '问题状态',
            field : 'questionStatus',
            sortable : true,
        }, {
            width : '140',
            title : '问题录入时间',
            field : 'beginTime',
            sortable : true
        }, {
            width : '140',
            title : '问题解决时间',
            field : 'endTime',
            sortable : true
        },{
            field : 'action',
            title : '操作',
            width : 140,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/machineProblems/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="machineProblems-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="machineProblemsEditFun(\'{0}\');" >编辑</a>', row.problemId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/machineProblems/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="machineProblems-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="machineProblemsDeleteFun(\'{0}\');" >删除</a>', row.problemId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.machineProblems-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.machineProblems-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
    });
});




/**
 * 编辑
 */
function machineProblemsEditFun(problemId) {
    if (problemId == undefined) {
        var rows = machineProblemsDataGrid.datagrid('getSelections');
        problemId = rows[0].problemId;
    } else {
        machineProblemsDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 500,
        height : 250,
        href :  '${path}/machineProblems/editPage?problemId=' + problemId,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = machineProblemsDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#machineProblemsEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function machineProblemsDeleteFun(problemId) {
     if (problemId == undefined) {//点击右键菜单才会触发这个
         var rows = machineProblemsDataGrid.datagrid('getSelections');
         problemId = rows[0].problemId;
     } else {//点击操作里面的删除图标会触发这个
         machineProblemsDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前记录？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/machineProblems/delete', {
            	 problemId : problemId
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     machineProblemsDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function machineProblemsCleanFun() {
    $('#machineProblemsSearchForm input').val('');
    machineProblemsDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function machineProblemsSearchFun() {
     machineProblemsDataGrid.datagrid('load', $.serializeObject($('#machineProblemsSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="machineProblemsSearchForm">
            <table>
                <tr>
                    <th>机具序列号：</th>
                    <td><input name="machineId" placeholder="机具序列号"/></td>
                    <th>售后人员：</th>
                    <td><input name="seller" placeholder="售后人员"/></td>
                    <td>问题状态:</td>
	    			<td><select id="questionStatus" name="questionStatus" class="easyui-combobox" data-options="width:140,height:23,editable:false,panelHeight:'auto'">
                            <option value="" selected></option>
                            <option value="已解决">已解决</option>
                            <option value="未解决">未解决</option>
                    </select></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="machineProblemsSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="machineProblemsCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="machineProblemsDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="machineProblemsToolbar" style="display: none;">
    <shiro:hasPermission name="/machineProblems/add">
        <a onclick="machineProblemsAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>