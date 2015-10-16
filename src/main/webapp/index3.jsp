<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>文件上传工具V2.1</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />

<link href="${pageContext.request.contextPath}/css/swfupload-default.css" rel="stylesheet"type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/handlers.js"></script>

<script type="text/javascript">
	var contextPath="${pageContext.request.contextPath}";
	function startLoad(){
		var url=contextPath+"/servlet/fileUploadServlet"; //处理上传的servlet
		var sizeLimit="1 MB";// 文件的大小  注意: 中间要有空格
		var types="*.jpg;*.jpeg;*.gif;*.png"; //注意是 " ; " 分割 
		var typesdesc="web iamge file"; //这里可以自定义
		var uploadLimit=20;  //上传文件的 个数
		initSwfupload(url,sizeLimit,types,typesdesc,uploadLimit);
	}
</script>
</head>
<body>
<center><input type="button" onclick="startLoad()" value="批量图片上传"/></center>
<div>
	<table>
		<tr>
			<td>
			</td>
			<td>
			</td>
		</tr>
	</table>
</div>
</body>
</html>