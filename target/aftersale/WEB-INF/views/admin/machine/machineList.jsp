<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var machineDataGrid;
    $(function() {
        machineDataGrid = $('#machineDataGrid').datagrid({
        url : '${path}/machine/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'code',
        sortName : 'code',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
            width : '60',
            title : '型号编号',
            field : 'code',
            sortable : true
        }, {
            width : '60',
            title : '型号描述',
            field : 'note',
            sortable : true,
           
        }, {
            width : '70',
            title : '型号编码',
            field : 'pycode',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            width : '150',
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/machine/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="machine-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="machineEditFun(\'{0}\');" >编辑</a>', row.code);
                </shiro:hasPermission>
                <shiro:hasPermission name="/machine/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="machine-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="machineDeleteFun(\'{0}\');" >删除</a>', row.code);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.machine-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.machine-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#machineToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function machineAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 240,
        height : 190,
        href : '${path}/machine/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = machineDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#machineAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function machineEditFun(code) {
    if (code == undefined) {
        var rows = machineDataGrid.datagrid('getSelections');
        code = rows[0].code;
    } else {
        machineDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 240,
        height : 190,
        href :  '${path}/machine/editPage?code=' + code,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = machineDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#machineEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function machineDeleteFun(code) {
     if (code == undefined) {//点击右键菜单才会触发这个
         var rows = machineDataGrid.datagrid('getSelections');
         code = rows[0].code;
     } else {//点击操作里面的删除图标会触发这个
         machineDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/machine/delete', {
                 code : code
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     machineDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function machineCleanFun() {
    $('#machineSearchForm input').val('');
    machineDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function machineSearchFun() {
     machineDataGrid.datagrid('load', $.serializeObject($('#machineSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="machineSearchForm">
            <table>
                <tr>
                    
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="machineSearchFun();">查询</a>
                        
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="machineDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="machineToolbar" style="display: none;">
    <shiro:hasPermission name="/machine/add">
        <a onclick="machineAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>