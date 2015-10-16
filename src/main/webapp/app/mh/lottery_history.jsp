<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/swiper.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.jquery.js"></script>
<style type="text/css">
body{background: #fffad1;  background: url(${pageContext.request.contextPath}/mh_images/cen_bg.jpg);}
.table>tbody>tr>td{padding: 5px;line-height:1.2;}
	.dst{
		width:480px;
		height:200px;
		background:#fff;
		position:fixed;
		top:40%;
		left:50%;
		margin:-100px 0 0 -255px;
		display:none;
		padding:50px 15px 15px 15px;
		text-align:left;
		border:solid 2px #ffd889;
		box-shadow:0 0 15px #ce9d39;
		background:#fffad1;
	}
	.form-control {
	    padding: 2px 12px;
	}
</style>
<script type="text/javascript">
window.location.hash = "#title"; 
$(document).ready(function(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mh/showHistory.do",
		type : 'POST',
		dataType:"html",
		cache : false,
		success : function(text){
			$("#history_div").html(text);
		}
	});
	
	$("#save").click(function(){
		var params = $("#form1").serialize();
  		var userName = $("#userName").val();
  		var mobile = $("#mobile").val();
  		var postcode = $("#postcode").val();
  		var address = $("#address").val();
  		if(userName.length == 0){
  			alert("用户名不能为空");
  			return;
  		}
  		if(mobile.length == 0){
  			alert("手机号不能为空");
  			return;
  		}
  		if(postcode.length == 0){
  			alert("邮编不能为空");
  			return;
  		}
  		if(address.length == 0){
  			alert("地址不能为空");
  			return;
  		}
  		$.ajax({
			url : "${pageContext.request.contextPath}/mh/saveLotteryInfo.do",
			data : params,
			type : 'POST',
			dataType:"json",
			cache : false,
			success: function(msg){
				if(msg.result=="0"){
					alert("保存成功");
				}else if(msg.result=="1"){
					alert("保存失败");
				}
				$("#edit_info").hide();
				gotoPage(1);
			}
  		});
	});
	
	$("#close").click(function(){
		$("#edit_info").hide();
	});
});
function gotoPage(_page){
	$.ajax({
		url : "${pageContext.request.contextPath}/mh/showHistory.do",
		type : 'POST',
		data : "pageIndex="+_page,
		dataType:"html",
		cache : false,
		success : function(text){
			$("#history_div").html(text);
		}
	});
}
function save_button(){
	$("#edit_info").show();
}
</script>
</head>
<body>
<%@include file="../../mh_top.jsp"%>
<div style="margin: 20px;" id="history_div">
		<%@include file="history_list.jsp"%>
</div>

   <div class="dst" id="edit_info" style="padding: 15px;height: 350px;">
        	<h1 style="font-family:'微软雅黑'; font-size:1.4em; color:#f00; margin:5px 0;">请完善用户信息</h1>
        	<form class="form-horizontal" style="margin-top: 10px;" id="form1">
			  <div class="form-group">
			    <label for="userName" class="col-xs-3 control-label">用户名称<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="userName" name="userName" placeholder="用户名称">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="mobile" class="col-xs-3 control-label">手机号<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="mobile" name="mobile" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="postcode" class="col-xs-3 control-label">邮编<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="postcode" name="postcode" placeholder="邮编">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="address" class="col-xs-3 control-label">地址<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="address"  name="address" placeholder="地址">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-xs-offset-2 col-xs-8" style="margin-left: 115px;">
			    	<button type="button" id="close" class="btn btn-default">取消</button>
			    	<button type="button" id="save" class="btn btn-primary">确定</button>
			    </div>
			  </div>
			</form>
    </div>
</body>
</html>