<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/my_photos.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript">
var photoInfoList = ${photoInfoList};
var thumbnail = "${thumbnail}";
var compress = "${compress}";
var listSize = ${listSize};
var scroll=true;
if(listSize <10){
	scroll=false;
}
</script>
<title>我的作品</title>
</head>
<body style="background-color: #efefef;">
	<div class="top">我的作品</div>
	<input type="hidden" id="openid" value="${openid}">
	<input type="hidden" id="userSource" value="${userSource}">
	<input type="hidden" id="competitionType" value="${competitionType}">
	<div style="margin:10px;" id="photo_content">
		<c:if test="${listSize eq 0}">
			<div style="text-align: center;">您还没有上传作品</div>
		</c:if>
	</div>
	<div id="loadMore" style="text-align: center;display:none;">
		<a style="color:gray; background: url('../images/loading.gif');background-repeat:no-repeat;background-size: 14px;background-position:0px 1px;">
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正在加载...
	    </a>
	</div>
	<div id="bottom" style="text-align: center;display:none;">
		<a style="color:gray;">
	            	---&nbsp;&nbsp;已到最底部&nbsp;&nbsp;---
	    </a>
	</div>
<!--  	<div align="center" style="font-size: 9px;margin-bottom: 5px;postion:absolute;">注：在法律允许范围内本活动最终解释权归天翼电信终端有限公司所有。</div>
-->
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
</html>