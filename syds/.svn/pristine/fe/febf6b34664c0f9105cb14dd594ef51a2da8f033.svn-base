<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/wx_common.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/imgReady.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/edit_photo.js"></script>

<title>上传图片</title>
</head>
<body>
	<div class="top">编辑图片信息</div>
	<div style="margin: 10px;">
		<div class="search">
			<button class="search-btn" id="chooseImage">选择照片</button>
		</div>
	</div>
	<div>
		<form id="photo_list_form" style="display: none;">
			<div id="photo_list_div"></div>
			<div class="search">
				<a href="#" class="search-btn" id="uploadImage"> 提交 </a>
			</div>
		</form>
		<!-- 
		<div class="imgContent">
      		<img src="" id="img_id" class="img">
      		<div class="closeLayer"  onClick="delcfm(this)">
         				<img src="../images/close.png" style="width: 14px;">
       		</div>
	        <div class="imgDesc">
				<div class="form-group" style="padding-top: 15px;">
					<label for="exampleInputPassword1">作品名称：</label>
					<input type="text" class="form-control" id="photo_name" name="photo_name" placeholder="请输入作品名称">
				</div>
	        </div>
		</div>
	 -->
	</div>
	<div align="center" style="font-size: 9px;margin-bottom: 5px;postion:absolute;">注：在法律允许范围内本活动最终解释权归天翼电信终端有限公司所有。</div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	wx.config({
		debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId : '${appId}', // 必填，公众号的唯一标识
		timestamp : '${timestamp}', // 必填，生成签名的时间戳
		nonceStr : '${nonceStr}', // 必填，生成签名的随机串
		signature : '${signature}',// 必填，签名，见附录1
		jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
				'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo',
				'hideMenuItems', 'showMenuItems', 'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem', 'translateVoice', 'startRecord',
				'stopRecord', 'onRecordEnd', 'playVoice', 'pauseVoice',
				'stopVoice', 'uploadVoice', 'downloadVoice', 'chooseImage',
				'previewImage', 'uploadImage', 'downloadImage',
				'getNetworkType', 'openLocation', 'getLocation',
				'hideOptionMenu', 'showOptionMenu', 'closeWindow',
				'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
				'addCard', 'chooseCard', 'openCard' ]

	});
	wx.ready(function(){
		wx.hideOptionMenu();
	});
</script>
<script type="text/javascript">
	// 5 图片接口
	// 5.1 拍照、本地选图
	var images = {
		localId : [],
		serverId : [],
		photoName : [],
		photoType: []
	};
	var photoNum=0;
	document.querySelector('#chooseImage').onclick = function() {
//		images.localId = [ "../images/IMG_1321.JPG", "../images/IMG_1325.JPG" ];
		document.querySelector('#chooseImage').style.backgroundColor="#a60000";
	    wx.chooseImage({
			 success: function (res) {
				 images.localId = res.localIds;
				 var image;
					for (image in images.localId) {
						imgReady(images.localId[image], function() {
							//alert('size ready: width=' + this.width + '; height=' + this.height); 
							var width = 160;
							var height = this.height * width / this.width;
							var rect_top = 0;
							var rect_botton = height;
							var rect_left = 0;
							var rect_right = width;
							if (height > 120) {
								rect_top = (height - 120) / 2;
								rect_botton = height - rect_top;
							}
							if (width > 120) {
								rect_left = (width - 120) / 2;
								rect_right = width - rect_left;
							}
							var text = image_div_content.replace("\${imgSrc}",
									this.src);
							text = text.replace("\${rect_top}", rect_top);
							text = text.replace("\${rect_right}", rect_right);
							text = text.replace("\${rect_botton}", rect_botton);
							text = text.replace("\${rect_left}", rect_left);
							text = text.replace("\${marginTop}", 10 - (height - 120) / 2
									+ "px");
							text = text.replace("\${num}", ++photoNum);
							text = text.replace("\${num}", photoNum);
							$("#photo_list_form").show();
							$("#photo_list_div").append(text);
						});
					}
			 }
		 });  
	    document.querySelector('#chooseImage').style.backgroundColor="#dd0000";
	};

	// 5.3 上传图片
	document.querySelector('#uploadImage').onclick = function() {
		var status = false;
		var num = 0;
		$(".img").each(function(i, n){
			var photoType;
			var img = $(n).attr("src").replace("l","L").replace("r","R");
			var photoName = $(n).parent().find("input").eq(0).val();
			if(photoName.length <=0){
				status = true;
				return false;
			}
			var jsh =$(n).parent().find("input[type=radio]").eq(0).prop("checked");
			var ysh =$(n).parent().find("input[type=radio]").eq(1).prop("checked");
			if(jsh){
				photoType = "1";
			}else if(ysh){
				photoType = "2";
			}
			images.localId[i]=img;
			images.photoName[i]=photoName;
			images.photoType[i]=photoType;
			
			num++;
		});
		if(status){
			alert("作品名称不能为空");
			return;
		}
		var j=0;
	    function upload(){
	    	wx.uploadImage({
				localId : images.localId[j],
				success : function(res) {
					$.ajax({
						url : "upload.do",
						type : 'POST',
						data : "serverId=" + encodeURIComponent(res.serverId)+"&photoName="+encodeURIComponent(images.photoName[j])+"&photoType="+images.photoType[j],
						dataType : "html",
						cache : false,
						success : function(result) {
							if(result=="0"){
								alert(images.photoName[j]+"上传成功");
							}else{
								alert(images.photoName[j]+"上传失败");
							} 
							if (++j < num){
					            upload();
							}else{
								window.location.href="../app/upload_status.jsp?status=OK&openid=${openid}";
							}
						}
					});  
				},
				fail : function(res) {
					alert(JSON.stringify(res));
				}
			});
	    }
	    if(num >0){
			upload();
	    }
	};
	
</script>
</html>