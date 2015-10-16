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
<title>上传状态</title>
</head>
<body>
	<div class="top">上传状态</div>
	<div style="margin:10px;">
		<div class="alert alert-success" role="alert">
			<strong>图片上传成功，请等待工作人员进行初步审核！</strong>
		</div>
		 <div class="search">
		 <!-- 
		 		<a href="#" class="search-btn" id="myPhoto" style="width: 40% ;">
				   我的图片
				</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 -->
				<a href="#" class="search-btn" id="back" style="width: 80%;background-color: gray;border:1px solid gray; ">
				   关闭
				</a>
		  </div>
	</div>
	<div align="center" style="font-size: 9px;margin-bottom: 5px;postion:absolute;">注：在法律允许范围内本活动最终解释权归天翼电信终端有限公司所有。</div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	wx.ready(function(){
		wx.hideOptionMenu();
	});
	
	document.querySelector('#back').onclick = function() {
		wx.closeWindow();
	}
	document.querySelector('#myPhoto').onclick = function() {
		window.location.href = "../wx/myPhotos.do?userSource=1&competitionType=9&openid=${param.openid}";
	}
</script>
</html>