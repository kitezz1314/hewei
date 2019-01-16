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
<title>我的报障</title>
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
		var netName = "";
		var machineId = "";
		var questionDes = "";
		var status = "";
		var name = "";
		var phone = "";
		var reserveTime = "";
		$(function() {
			var url = location.href;
			$
					.ajax({
						type : 'get',
						url : 'http://aftersale.boyasafe.com/wechat/addPage3?url='
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
							if (data.net_name == undefined) {
								document.getElementById('netName').innerHTML = '无';
							} else {
								document.getElementById('netName').innerHTML = data.net_name;
							}
							if (data.machine_id == undefined) {
								document.getElementById('machineId').innerHTML = '无';
							} else {
								document.getElementById('machineId').innerHTML = data.machine_id;
							}
							if (data.question_des == undefined) {
								document.getElementById('questionDes').innerHTML = '无';
							} else {
								document.getElementById('questionDes').innerHTML = data.question_des;
							}
							if (data.status == undefined) {
								document.getElementById('status').innerHTML = '无';
							} else {
								document.getElementById('status').innerHTML = data.status;
							}
							if (data.name == undefined) {
								document.getElementById('name').innerHTML = '未派单';
							} else {
								document.getElementById('name').innerHTML = data.name;
							}
							if (data.phone == undefined) {
								document.getElementById('phone').innerHTML = '未派单';
							} else {
								document.getElementById('phone').innerHTML = data.phone;
							}
							if (data.reserve_time == undefined) {
								document.getElementById('reserveTime').innerHTML = '无';
							} else {
								document.getElementById('reserveTime').innerHTML = timestampToTime(data.reserve_time);
							}

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
	</script>
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<label class="weui-form-preview__label">网点名称</label> <em id="netName"
				class="weui-form-preview__value"></em>
		</div>
		<div class="weui-form-preview__bd">
			<p>
				<label class="weui-form-preview__label">机具号</label> <span
					id="machineId" class="weui-form-preview__value"></span>
			</p>
			<p>
				<label class="weui-form-preview__label">故障描述</label> <span
					id="questionDes" class="weui-form-preview__value"></span>
			</p>
			<p>
				<label class="weui-form-preview__label">故障单状态</label> <span
					id="status" class="weui-form-preview__value"></span>
			</p>
			<p>
				<label class="weui-form-preview__label">上门工程师</label> <span
					id="name" class="weui-form-preview__value"></span>
			</p>
			<p>
				<label class="weui-form-preview__label">工程师联系方式</label> <span
					id="phone" class="weui-form-preview__value"></span>
			</p>
			<p>
				<label class="weui-form-preview__label">预计到达时间</label> <span
					id="reserveTime" class="weui-form-preview__value"></span>
			</p>

		</div>
	</div>
</body>
</html>