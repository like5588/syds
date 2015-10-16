<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/swiper.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript" ></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.jquery.js"></script>
<script type="text/javascript">
//ajax拦截session失效
$.ajaxSetup({   
    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
    cache:false ,   
    complete:function(data,TS){
    	var res=eval('('+data.responseText+')');  
       if(res.sessionState == "timeout"){
    	 //window.location.href = "http://www.hicdma.com/mh/views/pages/login.jsp?redirect="+res.redirect;  
    	 alert("点赞操作，请先登录");
       }
    }   
});
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
var url = "";
if(request("ListType")=="3"){
	url = "exhibition.do";
}else{
	url = "moreCameraCellphone.do?ListType="+request("ListType");
}
function gotoPage(_page){
	$.ajax({
		url : url,
		type : 'POST',
		data : params+"&pageIndex="+_page,
		dataType:"html",
		cache : false,
		success: function(text){
		$("#content").html(text);
		}
	});
}
function laud(_this){
	$(_this).attr("disabled","disabled");
	var _photoId = $(_this).attr("attr");
	$.ajax({
		url : "mh/"+_photoId+"/laud.do",
		type : 'POST',
		dataType:"json",
		cache : false,
		success : function(result) {
			if(result.result=="0"){
				$(_this).prop("class","laud-checked");
				$(_this).removeAttr("onclick");
				var num = $(_this).prev().text();
				$(_this).removeAttr("style");
				$(_this).prev().text(++num);
			}else if(result.result=="2"){
				alert("每天点赞最多10次，请明天再来！");
				$(_this).removeAttr("onclick");
				$(_this).removeAttr("style");
			}else if(result.result=="1"){
				alert("已点赞");
				$(_this).removeAttr("onclick");
				$(_this).removeAttr("style");
			}else if(result.result=="-1"){
				alert("点赞失败");
				$(_this).removeAttr("disabled");
			}
		}
	});
}
$(document).ready(function(){ 		
	if(request("ListType")=="3"){
		//读取全国通过审核的作品信息    	photo_status =’1‘  ListType=1为相机，2为手机  3为全国参赛作品
		params = $("#query_form").serialize();
		$.ajax({
			url: "exhibition.do",
			type: "post",
			data : params,
			dataType:"html",
			cache : false,
			success: function(text){
			$("#content").html(text);
			}
		});
		
		$("#query").click(function(){
			params = $("#query_form").serialize();
			$.ajax({
				url : "exhibition.do",
				type : 'POST',
				data : params,
				dataType:"html",
				cache : false,
				success: function(text){
				$("#content").html(text);
				}
			});
			
		});
	}	
	
	
	
	if(request("ListType")=="1"||request("ListType")=="2"){
		//读取全国赛中相机佳作和手机佳作作品信息    	ListType=1为相机，2为手机  3为全国参赛作品
		params = $("#query_form").serialize();
		$.ajax({
			url: "moreCameraCellphone.do?ListType="+request("ListType"),
			type: "post",
			data : params,
			dataType:"html",
			cache : false,
			success: function(text){
			$("#content").html(text);
			}
		});
		
		$("#query").click(function(){
			params = $("#query_form").serialize();
			$.ajax({
				url : "moreCameraCellphone.do?ListType="+request("ListType"),
				type : 'POST',
				data : params,
				dataType:"html",
				cache : false,
				success: function(text){
				$("#content").html(text);
				}
			});
		});
	}
	//分类为全部时默认选中
	document.getElementById('all').checked = true;
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
<title>全国作品列表</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
</head>
<body>
<%@include file="mh_top.jsp"%>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<div class="yg_33province_bg" style="min-height: 575px;">
	<div class="yg_works_se" align="center">
		<form class="form-inline" id="query_form" style="margin-bottom: 15px;" method="post">
			<input type="hidden" name="competitionType" id="competitionType" value="9" />
			<div class="form-group" style="padding-right:15px;">
				<label for="openid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;全部分类：</label>
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
			<label for="desc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：</label>
			<input type="text" class="form-control input1" style="clear:both;width: 150px;" name="userName" id="userName">
			</div>
			<div class="form-group" style="padding-right:15px;">
			<label for="desc">作品名称：</label>
			<input type="text" class="form-control input1" style="clear:both;width: 150px;" name="photoTitle" id="photoTitle">
			</div>
			<div class="form-group" style="padding-right:15px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   <input name="" type="button" class="yg_works_button" id="query"/>
			</div>
		</form>

	</div>
	<br>
	<div id="content">
	    <%@ include file="/teleCom_photo_list.jsp"%>
	</div>
</div>

</body>
</html>