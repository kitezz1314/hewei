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
<title>自助报障</title>
<!-- 引入 WeUI -->
<link rel="stylesheet" href="//res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body>
	<script type="text/javascript">
		var appId="";
		var timestamp="";
		var nonceStr="";
		var signature="";
		var openid="";
		$(function(){
			var url = location.href;
			$.ajax({
				type:'get',
				url:'http://aftersale.boyasafe.com/wechat/addPage2?url='+url,
				async:false,
				cache:false,
				dataType:'JSON',
				success:function(data){
					/* alert(location.href.split('#')[0]); */
					timestamp = data.timestamp;
					nonceStr = data.nonceStr;
					signature = data.signature;
					appId = data.appId;
					openid = data.openid;
					wx.config({
						 	debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						    appId: appId, // 必填，公众号的唯一标识
						    timestamp: timestamp, // 必填，生成签名的时间戳
						    nonceStr: nonceStr, // 必填，生成签名的随机串
						    signature: signature,// 必填，签名
						    jsApiList: ["chooseImage","previewImage","uploadImage","downloadImage","scanQRCode"], // 必填，需要使用的JS接口列表
					});
				}
			});
		});
		
		function makesure(){
			var machineId=$("#machineId").val();
			var netName = $("#netName").val();
			var questionDes=$("#questionDes").val();
			var phone = $("#phone").val();
			var reserveTimemid = document.querySelector('input[type="datetime-local"]');
			var reserveTime = reserveTimemid.value;
			var image1 = $('input[name="1"]').val();
			var image2 = $('input[name="2"]').val();
			var image3 = $('input[name="3"]').val();
			progressLoad();
			$.ajax({
				type:'POST',
				url:'http://aftersale.boyasafe.com/wechat/add',
				timeout:30000,
				async:true,
				cache:false,
				dataType:'JSON',
				data:{
					machineId : machineId,
					netName:netName,
					questionDes:questionDes,
					phone:phone,
					reserveTime1:reserveTime,
					image1:image1,
					image2:image2,
					image3:image3,
					openid:openid,
				},
				success:function(result){
					if(result.success){
						progressClose();
						$("#machineId").val('');
						$("#netName").val('');
						$("#questionDes").val('');
						$("#phone").val('');
						document.querySelector('input[type="datetime-local"]').value='';
						$(".up-box").remove();
						$.messager.alert('提示', result.msg, 'info');
						i = 0;
					}else{
						progressClose();
						$("#machineId").val('');
						$("#netName").val('');
						$("#questionDes").val('');
						$("#phone").val('');
						document.querySelector('input[type="datetime-local"]').value='';
						$(".up-box").remove();
						$.messager.alert('错误', result.msg, 'error');
						i = 0;
					}
					
				},
				error:function(result){
					
				}
			});
		}
		

	
	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		alert("123");
	});
	
	var i=0;
   function wxChooseImage(){
        if(i<4){
            var html = '';
            wx.chooseImage({
                count: 3-i,
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            	sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds;
                    syncUpload(localIds);
                }
            });
            
            var syncUpload = function(localIds){
                var localId = localIds.shift();
                 wx.uploadImage({
                    localId: localId,
                    isShowProgressTips: 1,
                    success: function (res) {
                        i++;
                        var serverId = res.serverId; // 返回图片的服务器端ID
                        html ='';
                        html += '<div class="up-box" style="float:left;"><img src="'+localId+'" class="img" height="100" width="100" onclick="enlarge(this)"/><div class="close" style="float:right;"><img src="${staticPath }/static/style/images/close.png" height="20" width="20" onclick="del_img(this)"> </div><input type="hidden" name="'+i+'" value="'+serverId+'"></div>';
                        //其他对serverId做处理的代码
                        var $dom = $(html);
                        
                        $("#upImg").before($dom);
                        
                        if(localIds.length > 0){
                            setTimeout(function(){
                                syncUpload(localIds);
                            },500);
                            
                        }
                        if(i > 2){
                            $("#upImg").hide();
                        }

                    }
                });
            };
        }
    };

/*图片删除*/
function del_img(dom){
    $(dom).parents('.up-box').remove();
    i--;
    if(i < 6){
        $("#upImg").show();
    }
}

/*扫描机具号*/
function scan(){
	wx.scanQRCode({
		needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		success: function (res) {
		var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		$("#machineId").val(result);
		var index = result.indexOf(',');
		
		if(index==-1){
			$("#machineId").val(result);
		}else{
			$("#machineId").val(result.substring(index+1));
		}
		
		}
		});
}

/*图片放大*/
function enlarge(dom){
    var nowImgurl = $(dom).attr("src");
    var imgs = [];
    var imgObj = $(".up-box .img");//这里改成相应的对象
    $.each(imgObj,function(index,el){
        imgs.push(imgObj.eq(index).attr("src"));
    });
     wx.ready(function(){ 
        wx.previewImage({
            current: nowImgurl, // 当前显示图片的http链接
            urls: imgs // 需要预览的图片http链接列表
        });
     }); 
}
/*调用微信预览图片的方法*/
/* $('body').on("click",".weui-cell weui-uploader__bd .upload-mod .up-box .img",function(){
	alert(123);
    var nowImgurl = $(this).attr("src");
    var imgs = [];
    var imgObj = $(".up-box .img");//这里改成相应的对象
    $.each(imgObj,function(index,el){
        imgs.push(imgObj.eq(index).attr("src"));
    });
     wx.ready(function(){ 
        wx.previewImage({
            current: nowImgurl, // 当前显示图片的http链接
            urls: imgs // 需要预览的图片http链接列表
        });
     }); 
}) */

	</script>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">机具号</label>
			</div>
			<div class="weui-cell__bd">
				<input id="machineId" class="weui-input" type="text" placeholder="请输入机具号" />
			</div>
			<div class="weui-cell__ft">
            <img class="weui-vcode-img" src="${staticPath }/static/style/images/scan3.png"  onclick="scan()"/>
        </div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">网点名称</label>
			</div>
			<div class="weui-cell__bd">
				<input id="netName" class="weui-input" type="text" placeholder="请输入网点名称" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">故障现象</label>
			</div>
			<div class="weui-cell__bd">
				<input id="questionDes" class="weui-input" type="text" placeholder="请描述故障现象" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">联系电话</label>
			</div>
			<div class="weui-cell__bd">
				<input id="phone" class="weui-input" type="tel" placeholder="请输入手机号">
			</div>
		</div>

		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label for="" class="weui-label">预约时间</label>
			</div>
			<div class="weui-cell__bd">
				<input name="reserveTime" class="weui-input" type="datetime-local" value=""
					placeholder="" />
			</div>
		</div>

		<div class="weui-cell">
			<div class="weui-uploader__hd">
				<p class="weui-uploader__title">图片上传</p>
				<div class="weui-uploader__info">&nbsp;&nbsp;&nbsp;请不要超过3张图片</div>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-uploader__bd">
				<!-- <ul class="weui-uploader__files">

				</ul>
				<div class="weui-uploader__input-box">
					 
					<input id="uploaderInput" class="weui-uploader__input js_file"
						type="file" accept="image/*" multiple />
				</div> -->
				<!-- <ul class="weui-uploader__files">
 
				</ul>
				<div class="weui-uploader__input-box" onclick="wxChooseImage()">
					 
					
				</div> -->
				
				 <div class="upload-mod" id="upload-mod">
                     <!-- <div class="up-box" id="upImg">
                        <img src="__HOME__/images/03-02-01.png " alt="" class="btn_dianji"/>
                     </div> -->
                     
                    <%--  
                     <div class="up-box" style="float:left;margin-left:10px;">
                     	<img src="'+localId+'" class="img" height="100" width="100"/>
                     <div class="close" style="float:right;">
                     	<img src="${staticPath }/static/style/images/close.png" height="20" width="20" onclick="del_img(this)"> 
                     </div>
                     <input type="hidden" name="'+i+'" value="'+serverId+'">
                     </div> 
                     --%>
                     
                     <div id="upImg" class="weui-uploader__input-box" onclick="wxChooseImage()">
                     
                     </div>
			    </div>
		   </div>
	 </div>
	<div class="weui-btn-area">
		<a class="weui-btn weui-btn_primary" href="javascript:"
			onclick="makesure()">确定</a>
	</div>
</body>
</html>