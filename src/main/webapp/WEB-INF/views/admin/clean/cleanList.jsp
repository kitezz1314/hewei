<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var cleanDataGrid;
    $(function() {
        cleanDataGrid = $('#cleanDataGrid').datagrid({
        url : '${path}/clean/dataGrid',
        striped : true,
        pagination : true,
        singleSelect : true,
        idField : 'cleanId',
        sortName : 'cleanId',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '60',
            title : '编号',
            field : 'cleanId',
            sortable : true
        }, {
            width : '100',
            title : '机具号',
            field : 'machineId',
            sortable : true
        }, {
            width : '100',
            title : '清洁人员',
            field : 'cleaner',
            sortable : true
        },  {
            width : '100',
            title : '项目负责人',
            field : 'by1',
            sortable : true
        },  {
            width : '100',
            title : '清洁类型',
            field : 'cleanType',
            sortable : true,
            formatter : function(value, row, index) {
                switch (value) {
                case 0:
                    return '清洁卡清洁';
                case 1:
                    return '清洁打印头';
                case 2:
                    return '清洁滚轮';
                }
            }
        }, {
            width : '140',
            title : '清洁时间',
            field : 'cleanTime',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            width : 140,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/clean/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="clean-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="cleanEditFun(\'{0}\');" >编辑</a>', row.cleanId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/clean/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="clean-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="cleanDeleteFun(\'{0}\');" >删除</a>', row.cleanId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.clean-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.clean-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
    });
});




/**
 * 编辑
 */
function cleanEditFun(cleanId) {
    if (cleanId == undefined) {
        var rows = cleanDataGrid.datagrid('getSelections');
        cleanId = rows[0].cleanId;
    } else {
        cleanDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 550,
        height : 250,
        href :  '${path}/clean/editPage?cleanId=' + cleanId,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = cleanDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#cleanEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function cleanDeleteFun(cleanId) {
     if (cleanId == undefined) {//点击右键菜单才会触发这个
         var rows = cleanDataGrid.datagrid('getSelections');
         cleanId = rows[0].cleanId;
     } else {//点击操作里面的删除图标会触发这个
         cleanDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前清洁记录？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/clean/delete', {
            	 cleanId : cleanId
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     cleanDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}

/**
 * 清除
 */
function cleanCleanFun() {
    $('#cleanSearchForm input').val('');
    cleanDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function cleanSearchFun() {
     cleanDataGrid.datagrid('load', $.serializeObject($('#cleanSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="cleanSearchForm">
            <table>
                <tr>
                    <th>机具序列号:</th>
                    <td><input name="machineId" placeholder="机具序列号"/></td>
                    <th>清洁人员:</th>
                    <td><input name="cleaner" placeholder="清洁人员"/></td>
                    <th>项目负责人:</th>
                    <td><input name="by1" placeholder="项目负责人"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="cleanSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="cleanDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
