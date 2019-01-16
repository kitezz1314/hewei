<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var dataDataGrid;
    
    $(function() {
        dataDataGrid = $('#dataDataGrid').datagrid({
            url : '${path }/data/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            sortName : 'number',
	        sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ 
            {
                width : '80',
                title : '项目所属省',
                field : 'province'
            },{
                width : '80',
                title : '项目负责人',
                field : 'principle'
            }, {
                width : '80',
                title : '机具数量',
                field : 'number',
            } ] ]
        });
    });
    
   
    
    function searchDataFun() {
        dataDataGrid.datagrid('load', $.serializeObject($('#searchDataForm')));
        var createdateStart = $("input[name='createdateStart']").val();
        var createdateEnd = $("input[name='createdateEnd']").val();
        var myChart3 = echarts.init(document.getElementById('echartsAjax'));
         // 指定图表的配置项和数据
         myChart3.setOption({
             title: {
                 text: '省-机具数量柱形图'
             },
             tooltip: {},
             legend: {
                 data:['销量']
             },
             xAxis: {
                 data: []
             },
             yAxis: {},
             series: [{
                 name: '机具数量',
                 type: 'bar',
                 data: []
             }]
         });
         
         $.ajax({
        	 url:"${path }/data/echarts?createdateStart="+createdateStart+"&createdateEnd="+createdateEnd,
        	 async:false,
        	 cache:false,
        	 dataType:'json',
        	 success:function(data){
        		 myChart3.setOption({
        			 xAxis:{data:data.categories},
        			 series:[{name:"机具数量",data:data.data}]
        		 });
        	 },
        	 error:function(error){
        		 console.log(error);
        	 }
         });
    }
    function cleanDataFun() {
        $('#searchDataForm input').val('');
        dataDataGrid.datagrid('load', {});
        var createdateStart = $("input[name='createdateStart']").val();
        var createdateEnd = $("input[name='createdateEnd']").val();
        var myChart3 = echarts.init(document.getElementById('echartsAjax'));
         // 指定图表的配置项和数据
         myChart3.setOption({
             title: {
                 text: '省-机具数量柱形图'
             },
             tooltip: {},
             legend: {
                 data:['销量']
             },
             xAxis: {
                 data: []
             },
             yAxis: {},
             series: [{
                 name: '机具数量',
                 type: 'bar',
                 data: []
             }]
         });
         
         $.ajax({
        	 url:"${path }/data/echarts?createdateStart="+createdateStart+"&createdateEnd="+createdateEnd,
        	 async:false,
        	 cache:false,
        	 dataType:'json',
        	 success:function(data){
        		 myChart3.setOption({
        			 xAxis:{data:data.categories},
        			 series:[{name:"机具数量",data:data.data}]
        		 });
        	 },
        	 error:function(error){
        		 console.log(error);
        	 }
         });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchDataForm">
            <table>
                <tr>
                    <th>机具安装时间:</th>
                    <td>
                        <input name="createdateStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至
                        <input name="createdateEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchDataFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanDataFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'统计表'" >
        <table id="dataDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'east',split:true" title="统计图" style="width: 800px; overflow: hidden;overflow-y:auto; padding:0px">
            <div id="echartsAjax" style="width: 600px;height:400px;"></div>
    </div>
   
        
    </div>
</div>

<script>
var createdateStart = $("input[name='createdateStart']").val();
var createdateEnd = $("input[name='createdateEnd']").val();
var myChart3 = echarts.init(document.getElementById('echartsAjax'));
 // 指定图表的配置项和数据
 myChart3.setOption({
     title: {
         text: '省-机具数量柱形图'
     },
     tooltip: {},
     legend: {
         data:['销量']
     },
     xAxis: {
         data: []
     },
     yAxis: {},
     series: [{
         name: '机具数量',
         type: 'bar',
         data: []
     }]
 });
 
 $.ajax({
	 url:"${path }/data/echarts?createdateStart="+createdateStart+"&createdateEnd="+createdateEnd,
	 async:false,
	 cache:false,
	 dataType:'json',
	 success:function(data){
		 myChart3.setOption({
			 xAxis:{data:data.categories},
			 series:[{name:"机具数量",data:data.data}]
		 });
	 },
	 error:function(error){
		 console.log(error);
	 }
 });
 




</script>