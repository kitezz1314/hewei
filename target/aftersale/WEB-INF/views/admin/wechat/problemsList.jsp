<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>我的故障单</title>
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="//res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body>
	<script type="text/javascript">
		var appId = "";
		var timestamp = "";
		var nonceStr = "";
		var signature = "";
		var openid = "";
		$(function() {
			var url = location.href;
			$.ajax({
						type : 'get',
						url : 'http://aftersale.boyasafe.com/wechat/addPage4?url='
								+ url,
						async : false,
						cache : false,
						dataType : 'JSON',
						success : function(data) {
							/* alert(location.href.split('#')[0]); */
							timestamp = data.timestamp;
							nonceStr = data.nonceStr;
							signature = data.signature;
							appId = data.appId;
							openid = data.openid;
/* 							if (data.net_name == undefined) {
								document.getElementById('netName').innerHTML = '无';
							} 
							if (data.reserve_time == undefined) {
								document.getElementById('reserveTime').innerHTML = '无';
							} else {
								document.getElementById('reserveTime').innerHTML = timestampToTime(data.reserve_time);
							}
 */							
 							alert(data.malfunctions);
							$.each(data.malfunctions, function() { 
								if(this.status==1 ){
									$('#problemsList').append('<div class="weui-form-preview"><div class="weui-form-preview__hd"><label class="weui-form-preview__label">网点名称</label> <em class="weui-form-preview__value">'+this.netName+'</em></div><div class="weui-form-preview__bd"><p><label class="weui-form-preview__label">机具编号</label><span class="weui-form-preview__value">'+this.machineId+'</span></p><p><label class="weui-form-preview__label">故障描述</label> <span class="weui-form-preview__value">'+this.questionDes+'</span></p><p><label class="weui-form-preview__label">客户电话</label> <span class="weui-form-preview__value">'+this.phone+'</span></p><p><label class="weui-form-preview__label">预约时间</label> <span class="weui-form-preview__value">'+timestampToTime(this.reserveTime)+'</span></p><p><label class="weui-form-preview__label">故障单状态</label> <span class="weui-form-preview__value">已派单</span></p></div><div class="weui-form-preview__ft"><a class="weui-form-preview__btn weui-form-preview__btn_primary" onclick="updateStatus('+this.id+')" href="javascript:">修改状态：已派单->在途中</a></div></div>');
								}else if(this.status==2){
									$('#problemsList').append('<div class="weui-form-preview"><div class="weui-form-preview__hd"><label class="weui-form-preview__label">网点名称</label> <em class="weui-form-preview__value">'+this.netName+'</em></div><div class="weui-form-preview__bd"><p><label class="weui-form-preview__label">机具编号</label><span class="weui-form-preview__value">'+this.machineId+'</span></p><p><label class="weui-form-preview__label">故障描述</label> <span class="weui-form-preview__value">'+this.questionDes+'</span></p><p><label class="weui-form-preview__label">客户电话</label> <span class="weui-form-preview__value">'+this.phone+'</span></p><p><label class="weui-form-preview__label">预约时间</label> <span class="weui-form-preview__value">'+timestampToTime(this.reserveTime)+'</span></p><p><label class="weui-form-preview__label">故障单状态</label> <span class="weui-form-preview__value">在途中</span></p></div><div class="weui-form-preview__ft"><a class="weui-form-preview__btn weui-form-preview__btn_primary" onclick="updateStatus('+this.id+')" href="javascript:">修改状态：在途中->维修中</a></div></div>');
								}else if(this.status==3){
									$('#problemsList').append('<div class="weui-form-preview"><div class="weui-form-preview__hd"><label class="weui-form-preview__label">网点名称</label> <em class="weui-form-preview__value">'+this.netName+'</em></div><div class="weui-form-preview__bd"><p><label class="weui-form-preview__label">机具编号</label><span class="weui-form-preview__value">'+this.machineId+'</span></p><p><label class="weui-form-preview__label">故障描述</label> <span class="weui-form-preview__value">'+this.questionDes+'</span></p><p><label class="weui-form-preview__label">客户电话</label> <span class="weui-form-preview__value">'+this.phone+'</span></p><p><label class="weui-form-preview__label">预约时间</label> <span class="weui-form-preview__value">'+timestampToTime(this.reserveTime)+'</span></p><p><label class="weui-form-preview__label">故障单状态</label> <span class="weui-form-preview__value">维修中</span></p></div><div class="weui-form-preview__ft"><a class="weui-form-preview__btn weui-form-preview__btn_primary" onclick="updateStatus('+this.id+')" href="javascript:">修改状态：维修中->已完成</a></div></div>');
								}
							}); 
						    /* netName=data.netName;
							machineId=data.machineId;
							questionDes=data.questionDes;
							status=data.status;
							name=data.name;
							phone=data.phone;
							reserveTime=data.reserveTime; */
							wx.config({
								debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
								appId : appId, // 必填，公众号的唯一标识
								timestamp : timestamp, // 必填，生成签名的时间戳
								nonceStr : nonceStr, // 必填，生成签名的随机串
								signature : signature,// 必填，签名
								jsApiList : [ "chooseImage", "previewImage",
										"uploadImage", "downloadImage",
										"scanQRCode" ], // 必填，需要使用的JS接口列表
							});
						}
					});
		});

		function timestampToTime(timestamp) {
			var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
			var Y = date.getFullYear() + '-';
			var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1)
					: date.getMonth() + 1)
					+ '-';
			var D = date.getDate() + ' ';
			var h = date.getHours() + ':';
			var m = date.getMinutes();
			return Y + M + D + h + m;
		}
		
		
		function updateStatus(id) {
			$.ajax({
				type : 'get',
				url : 'http://aftersale.boyasafe.com/wechat/updateStatus?id='
						+ id,
				async : false,
				cache : false,
				dataType : 'JSON',
				success : function(data) {
					
					$.messager.alert('提示', data.msg, 'info');
					window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx17717cc913f8ddc7&redirect_uri=http://aftersale.boyasafe.com/wechat/problemsList&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
				}
			});
		}
	</script>
	<div id="problemsList">

	</div>
</body>
</html>