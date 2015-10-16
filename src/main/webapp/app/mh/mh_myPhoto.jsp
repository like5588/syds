<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/swiper.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.jquery.js"></script>
<script type="text/javascript">
function request(paras){        
	var url = location.href;  
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");        
	var paraObj = {}         
	for (i=0; j=paraString[i]; i++){        
		paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);     
	}         
	var returnValue = paraObj[paras.toLowerCase()];        
	if(typeof(returnValue)=="undefined"){         
		return "";        
	}else{  
		return returnValue;   
	}  
}
var params ="";
function gotoPage(_page){
	$.ajax({
		url : "${pageContext.request.contextPath}/mh/getMyPhoto.do",
		type : 'POST',
		data : params+"&pageIndex="+_page,
		dataType:"html",
		cache : false,
		success: function(text){
			$("#content").html(text);
		}
	});
}
$(document).ready(function(){
		//读取当前用户的所有通过审核的照片信息   通过审核 	photo_status =’1‘
		params = $("#query_form").serialize();
		$.ajax({
			url: "${pageContext.request.contextPath}/mh/getMyPhoto.do",
			type: "post",
			data : params,
			cache: false,  
			dataType:"html",
			success: function(text){  
				$("#content").html(text);
			},
			error:function(){
			}
		});	
		
		$("#query").click(function(){
			params = $("#query_form").serialize();
			$.ajax({
				url : "${pageContext.request.contextPath}/mh/getMyPhoto.do",
				type : 'POST',
				data : params,
				dataType:"html",
				cache : false,
				success: function(text){
				$("#content").html(text);
				}
			});
			
		});
});
//分类为全部时其它的单选框置为false
function checkbox(){ 
	document.getElementById('photoType').checked = false;
	document.getElementById('photoType1').checked = false;
	document.getElementById('simpleGroup').checked = false;
	document.getElementById('simpleGroup1').checked = false;
	document.getElementById('photoGroup').checked = false;
	document.getElementById('photoGroup1').checked = false; 
} 

function checkboxList(){
	document.getElementById('all').checked = false;
}
</script>
<title>我的作品</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
</head>
<body>
<%@include file="../../mh_top.jsp"%>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
 <div class="yg_33province_bg" style="min-height: 575px;">
	 <div style="yg_works_se"  align="center"><br>
	 <form class="form-inline" id="query_form">
		<div class="form-group" style="padding-right:15px;">
		<label for="openid">&nbsp;&nbsp;&nbsp;全部分类：</label>
		<input type="radio" name="all" id="all" value="" onclick="checkbox()" >
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		摄影类别：
		<div class="form-group" style="padding-right:15px;">
			<label for="desc">纪实</label>
			<input type="radio" name="photoType" id="photoType" value="1" onclick="checkboxList()">
		</div>
		<div class="form-group" style="padding-right:15px;">
			<label for="openid">风景</label>
			<input type="radio" name="photoType" id="photoType1" value="2" onclick="checkboxList()">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		照片类别：
		<div class="form-group" style="padding-right:15px;">
			<label for="desc">单图</label>
			<input type="radio" name="simpleGroup" id="simpleGroup" value="0" onclick="checkboxList()">
		</div>
		<div class="form-group" style="padding-right:15px;">
			<label for="openid">组图</label>
			<input type="radio" name="simpleGroup" id="simpleGroup1" value="1" onclick="checkboxList()">
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		摄影器材：
		<div class="form-group" style="padding-right:15px;">
			<label for="desc">相机</label>
			<input type="radio" name="photoGroup" id="photoGroup" value="0" onclick="checkboxList()">
		</div>
		<div class="form-group" style="padding-right:15px;">
			<label for="openid">手机</label>
			<input type="radio" name="photoGroup" id="photoGroup1" value="1" onclick="checkboxList()">
		</div>
		<br/>
		<div class="form-group" style="padding-right:15px;">
			<label for="desc">作品标题：</label>
			<input type="text" class="form-control input1" style="clear:both;width: 150px;" name="photoTitle" id="photoTitle">
		</div>
		<div class="form-group" style="padding-right:15px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   <input name="" type="button" class="yg_works_button" id="query"/>
		</div>
	 </form>
	 </div>
	<div id="content" style="margin: 0 auto;">
	    <%@ include file="photo_list.jsp"%>
	</div>
</div>

</body>
</html>