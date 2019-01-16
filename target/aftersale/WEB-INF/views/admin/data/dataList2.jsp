<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var dataDataGrid2;
    
    $(function() {
        dataDataGrid2 = $('#dataDataGrid2').datagrid({
            url : '${path }/data/dataGrid2',
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
                title : '项目负责人',
                field : 'principle'
            }, {
                width : '80',
                title : '机具数量',
                field : 'number',
            } ] ]
        });
    });
    
    function searchDataFun2() {
        dataDataGrid2.datagrid('load', $.serializeObject($('#searchDataForm2')));
        var createdateStart = $("input[name='createdateStart2']").val();
        var createdateEnd = $("input[name='createdateEnd2']").val();
        var myChart2 = echarts.init(document.getElementById('echartsAjax2'));
         // 指定图表的配置项和数据
         myChart2.setOption({
             title: {
                 text: '项目负责人-机具数量柱形图'
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
        	 url:"${path }/data/echarts2?createdateStart="+createdateStart+"&createdateEnd="+createdateEnd,
        	 async:false,
        	 cache:false,
        	 dataType:'json',
        	 success:function(data){
        		 myChart2.setOption({
        			 xAxis:{data:data.categories},
        			 series:[{name:"机具数量",data:data.data}]
        		 });
        	 },
        	 error:function(error){
        		 console.log(error);
        	 }
         });
    }
    function cleanDataFun2() {
        $('#searchDataForm2 input').val('');
        dataDataGrid2.datagrid('load', {});
        var createdateStart = $("input[name='createdateStart2']").val();
        var createdateEnd = $("input[name='createdateEnd2']").val();
        var myChart2 = echarts.init(document.getElementById('echartsAjax2'));
         // 指定图表的配置项和数据
         myChart2.setOption({
             title: {
                 text: '项目负责人-机具数量柱形图'
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
        	 url:"${path }/data/echarts2?createdateStart="+createdateStart+"&createdateEnd="+createdateEnd,
        	 async:false,
        	 cache:false,
        	 dataType:'json',
        	 success:function(data){
        		 myChart2.setOption({
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
        <form id="searchDataForm2">
            <table>
                <tr>
                    <th>机具安装时间:</th>
                    <td>
                        <input name="createdateStart2" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至
                        <input  name="createdateEnd2" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchDataFun2();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanDataFun2();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'统计表'" >
        <table id="dataDataGrid2" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'east',split:true" title="统计图" style="width: 800px; overflow: hidden;overflow-y:auto; padding:0px">
            <div id="echartsAjax2" style="width: 600px;height:400px;"></div>
    </div>
</div>

<script>
var createdateStart = $("input[name='createdateStart2']").val();
var createdateEnd = $("input[name='createdateEnd2']").val();
var myChart2 = echarts.init(document.getElementById('echartsAjax2'));
 // 指定图表的配置项和数据
 myChart2.setOption({
     title: {
         text: '项目负责人-机具数量柱形图'
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
	 url:"${path }/data/echarts2?createdateStart="+createdateStart+"&createdateEnd="+createdateEnd,
	 async:false,
	 cache:false,
	 dataType:'json',
	 success:function(data){
		 myChart2.setOption({
			 xAxis:{data:data.categories},
			 series:[{name:"机具数量",data:data.data}]
		 });
	 },
	 error:function(error){
		 console.log(error);
	 }
 });
</script>