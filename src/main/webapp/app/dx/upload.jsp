<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/swfupload-default.css" rel="stylesheet"type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/handlers.js"></script>
<script type="text/javascript">
window.location.hash = "#title"; 
var contextPath="${pageContext.request.contextPath}";
var code;
function startLoad(){
	var url=contextPath+"/dx/fileUploadServlet"; //处理上传的servlet
	var sizeLimit="10 MB";// 文件的大小  注意: 中间要有空格
	var types="*.jpg;*.jpeg;*.gif;*.png"; //注意是 " ; " 分割 
	var typesdesc="web image file"; //这里可以自定义
	var uploadLimit=8;  //上传文件的 个数
	$.ajax({
		url : "${pageContext.request.contextPath}/validateLogin.do",
		type : 'POST',
		dataType:"json",
		cache : false,
		success: function(text){
			if(text.result == "0"){
				initSwfupload(url,sizeLimit,types,typesdesc,uploadLimit);
			}else{
				if(confirm("用户已过期，请重新登陆")){
					window.location.href="http://www.hicdma.com/mh/views/pages/login.jsp?redirect=http://yxhd.hicdma.com/syds/mh_index.jsp";
				}
			}
		}
	});
}

function submit_photos(_form){
	$("#"+_form).prop("action","saveImages.do");
	$("#"+_form).submit();
	
};
</script>
<title>上传照片</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
</head>

<body>
<%@include file="../../teleCom_header.jsp"%>
<!--内容开始-->

	<div class="yg_33province_bg" style="min-height: 575px;">
		<div id="form_div">
			<form id="form1">
				<div class="yg_registered">
					<ul>
						<li>&nbsp;&nbsp;&nbsp;&nbsp; <input name="simpleGroup" type="radio" value="0" checked="checked"/>单图
						</li>
						<li></li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp; <input name="simpleGroup" type="radio" value="1" />组图
						</li>
					</ul>
					<ul>
						<li>&nbsp;&nbsp;&nbsp;&nbsp; <input name="photoGroup" type="radio" value="1" checked="checked"/>手机组
						</li>
						<li></li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp; <input name="photoGroup" type="radio" value="0" />相机组
						</li>
					</ul>
	
				</div>
	
				<a href="javascript:void(0);" onclick="startLoad()">
					<div class="yg_pro_button2"></div>
				</a>
			</form>
		</div>
		<div id="photo_single" class="yg_single" style="display: none;">
			<form id="image_form" method="post">
				<input type="hidden" name="simpleGroup" id="s_simpleGroup"/>
				<input type="hidden" name="photoGroup" id="s_photoGroup"/>
			</form>
		</div>
		
		<div id="photo_group" class="yg_group" style="display: none;">
			<form id="image_form1" method="post">
				<input type="hidden" name="simpleGroup" id="g_simpleGroup"/>
				<input type="hidden" name="photoGroup" id="g_photoGroup"/>
				<ul id="ul_content">
					
				</ul>
				<div class="yg_group_1">
			      	作品名称：<input name="photoTitle" type="text"  class="yg_group_text"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="photoType" type="radio" value="1" checked="checked"/>纪实&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="photoType" type="radio" value="2" />风景<br><br/>
			     	 作品简介：<textarea name="photoDesc" rows="2" class="yg_group_text1"></textarea>
			    </div>
			    <div class="yg_pro_button" style="margin:0 auto; clear:both;" onclick="submit_photos('image_form1')"></div>
    			<br />
			</form>
		</div>
	</div>
</body>
</html>