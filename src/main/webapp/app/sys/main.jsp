<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/swiper.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.jquery.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
function loadPage(_this,_stat){
	if(_this != null){
		$(_this).parent().parent().find("li").each(function(){
			$(this).prop("class","");
		})
		$(_this).parent("li").prop("class","active");
	}
	var url = "daipingxuan.do";
	if(_stat == 0){
		url = "daipingxuan.do";
	}else if(_stat == 1){
		url = "dairuxuan.do";
	}else if(_stat == 2){
		url = "chuping.do";
	}
	else if(_stat == 3){
		url = "jiazuo.do";
	}
	$.ajax({ 
		type: "GET",  
		url : url,
		cache: false, 
		success: function(msg) {  
			$("#div_content").html(msg);
		}
	});
}
</script>
<title>天翼2015摄影大赛评审平台</title>
</head>
<body>
	<%@include file="head.jsp"%>
	<div id="main" style="margin: 20px;">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#" onclick="loadPage(this,0)">待评区</a></li>
			<li role="presentation"><a href="#" onclick="loadPage(this,1)">待入选</a></li>
			<li role="presentation"><a href="#" onclick="loadPage(this,2)">入围初评</a></li>
			<li role="presentation" ><a href="#" onclick="loadPage(this,3)">佳作发布</a></li>
		</ul>
		<div id="div_content">
			<%@include file="daipingxuan.jsp"%>
		</div>
	</div>
</body>
</html>


