<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/dx_pingxuan.js"></script>
<script type="text/javascript">
var baseUrl = "${pageContext.request.contextPath}";
</script>
<title>天翼2015摄影大赛评审平台</title>
</head>
<body>
<%@include file="head.jsp"%>
	<div id="main" style="margin: 20px;">
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home">待评区</a></li>
			<li role="presentation"><a href="dx_dairuxuan.jsp">待入选</a></li>
			<li role="presentation"><a href="dx_chuping.jsp">入围初评</a></li>
		</ul>
		<div style="border-bottom: 1px solid #eee; margin-bottom: 20px;margin-top: 20px;">
			<form class="form-inline" id="query_form" style="margin-bottom: 5px;">
				<div class="form-group" style="padding-right: 15px;">
				<input type="hidden" name="isSelect" id="isSelect" value="0">
					<label for="photoGroup">分组：</label> 
					<select class="form-control" name="photoGroup" id="photoGroup">
					  <option value="">请选择</option>
					  <option value="1">手机组</option>
					  <option value="0">相机组</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="simpleGroup">单图/组图：</label> 
					<select class="form-control" name="simpleGroup" id="simpleGroup">
					  <option value="">请选择</option>
					  <option value="0">单图</option>
					  <option value="1">组图</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoType">类型：</label> 
					<select class="form-control" name="photoType" id="photoType">
					  <option value="">请选择</option>
					  <option  value="1" >纪实</option>
					  <option  value="2" >风景</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="userName">作者：</label> 
					<input class="form-control" id="userName" name="userName">
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoTitle">作品名称：</label> 
					<input class="form-control" id="photoTitle" name="photoTitle">
				</div>
				<button type="button" id="query" class="btn btn-primary" style="margin: 0 10px;">查询</button>
			</form>
			<div align="right">
				<button type="button" id="daixuan" class="btn btn-primary" onclick="pass(1,0)"
				style="margin-right: 10px;margin-top: -7px;float: right;">待入选</button>
				<div class="form-group" style="margin: 10px">
					<label class="checkbox-inline">
					  <input type="checkbox" id="checkAll" value="all"> 全选
					</label>
					<label class="checkbox-inline" style="margin-right: 40px;">
					  <input type="checkbox" id="invertCheck" value="no"> 反选
					</label>
				</div>
			</div>
		</div>
		<div class="tab-content">
    		<div role="tabpanel" class="tab-pane active" id="home">
    				<%@include file="photos_list.jsp"%>
    		</div>
		</div>
	</div>
</body>
</html>

