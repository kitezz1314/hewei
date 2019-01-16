<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var knowledgeDataGrid;
    $(function() {
        knowledgeDataGrid = $('#knowledgeDataGrid').datagrid({
        url : '${path}/knowledge/dataGrid',
        striped : true,
        pagination : true,
        singleSelect : true,
        idField : 'knowledgeId',
        sortName : 'knowledgeId',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '60',
            title : '知识编号',
            hidden:true,
            field : 'knowledgeId',
            sortable : true
        }, {
            width : '400',
            title : '问题描述',
            field : 'knowledgeName',
            sortable : true,
        }, {
            width : '140',
            title : '创建时间',
            field : 'createTime',
            sortable : true
        }, {
            field : 'action',
            title : '操作',
            width : 200,
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/knowledge/edit">
                    str += $.formatString('<a href="javascript:void(0)" class="knowledge-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-zoom-in icon-blue\'" onclick="knowledgeEditFun(\'{0}\');" >详情</a>', row.knowledgeId);
                </shiro:hasPermission>
                <shiro:hasPermission name="/knowledge/delete">
                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    str += $.formatString('<a href="javascript:void(0)" class="knowledge-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="knowledgeDeleteFun(\'{0}\');" >删除</a>', row.knowledgeId);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.knowledge-easyui-linkbutton-edit').linkbutton({text:'详情'});
            $('.knowledge-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#knowledgeToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function knowledgeAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/knowledge/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = knowledgeDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#knowledgeAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function knowledgeEditFun(knowledgeId) {
    if (knowledgeId == undefined) {
        var rows = knowledgeDataGrid.datagrid('getSelections');
        knowledgeId = rows[0].knowledgeId;
    } else {
        knowledgeDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '详情',
        width : 700,
        height : 600,
        href :  '${path}/knowledge/editPage?knowledgeId=' + knowledgeId,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = knowledgeDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#knowledgeEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function knowledgeDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = knowledgeDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         knowledgeDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前记录？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/knowledge/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     knowledgeDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}



/**
 * 搜索
 */
function knowledgeSearchFun() {
     knowledgeDataGrid.datagrid('load', $.serializeObject($('#knowledgeSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 60px; overflow: hidden;background-color: #fff">
        <form id="knowledgeSearchForm">
            <table align="center">
                <tr>
                   
                    <td><input style="width:300px;height:36px;font-size:28px;" name="knowledgeName"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true,size:'large'" onclick="knowledgeSearchFun();"><b>博雅一下</b></a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="knowledgeDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="knowledgeToolbar" style="display: none;">
    <shiro:hasPermission name="/knowledge/add">
        <a onclick="knowledgeAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
    </shiro:hasPermission>
</div>