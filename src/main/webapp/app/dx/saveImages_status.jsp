<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
window.location.hash = "#title"; 
</script>
<title>上传照片状态</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
</head>

<body>
<%@include file="../../teleCom_header.jsp"%>
<!--内容开始-->
	<div class="yg_33province_bg" style="min-height: 575px;">
		<c:if test="${result eq 0}">
		<div style="text-align:center"> 
			<div class="alert alert-success" role="alert" style="text-align: center;width: 300px;height: 100px;margin: 80px auto;padding-top: 30px;margin-left:auto ; margin-right:auto;">
				<strong>图片上传成功，请等待工作人员进行初步审核！</strong>
			</div>
		</div>
		</c:if>
		<c:if test="${result eq -1}">
		<div style="text-align:center"> 
			<div class="alert alert-success" role="alert" style="text-align: center;width: 300px;height: 100px;margin: 80px auto; padding-top: 40px;margin-left:auto ; margin-right:auto;">
				<strong>图片上传失败！</strong>
			</div>
		</div>
		</c:if>		
	</div>
</body>
</html>