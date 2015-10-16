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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<title>天翼2015摄影大赛评审平台</title>
<script type="text/javascript">
$(document).ready(function(){
	//ajax拦截session失效
	$.ajaxSetup({   
	    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
	    cache:false ,   
	    complete:function(data,TS){
	    	var res=eval('('+data.responseText+')');  
	       if(res.sessionState == "timeout"){
	    	   window.location.href = res.redirect; 
	       }
	    }   
	});
	$.ajax({
		url : "${pageContext.request.contextPath}/sys/lotteryInfo.do",
		type : 'POST',
		dataType:"html",
		cache : false,
		success : function(msg) {
			$("#main").html(msg);
		}
	});
	
	$("#logout").click(function(){
		if(confirm("确认注销？")){
			$.ajax({
				url : "${pageContext.request.contextPath}/logout.do",
				type : 'POST',
				data : "",
				dataType:"json",
				cache : false,
				success : function(json) {
					if(json.result ==0){
						window.location.href = "${pageContext.request.contextPath}/pingxuan.jsp";
					}
				}
			});
		}
	});
	
	$("#lottery_list").click(function(){
		 $("#lottery_list").attr("class","active");
		 $("#sysParams").removeAttr("class");
		 loadPage("lotteryInfo.do");
	});
	
	$("#sysParams").click(function(){
		 $("#sysParams").attr("class","active");
		 $("#lottery_list").removeAttr("class");
		 loadPage("paramsSetting.do");
	});
	
});

function loadPage(_page){
	  $.ajax({  
		  type: "get",  
		  url: _page,  
		  cache: false,  
		  dataType:"html",
	  	  success: function(msg) {  
	  		  $("#main").html(msg);
		  }
	  }); 
}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" style="color: #FFFFFF;" href="#">天翼2015摄影大赛管理后台</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
	        <ul class="nav navbar-nav navbar-right">
	          <li class="dropdown" style="border-right: 1px solid #eee;">
	          	<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button"
	          		 aria-haspopup="true" aria-expanded="false">${judgeLogon.petName}，您好</a>
	          </li>
	          <li><a href="#" id="logout">注销</a></li>
	          </ul>
	       	</div>
		</div>
	</nav>
	
		<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li id="lottery_list" class="active"><a href="#">中奖信息查询</a></li>
					<li id="sysParams"><a href="#">参数设置</a></li>
				</ul>
			</div>

			<div id="main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			</div>
		</div>
	</div>
</body>
</html>


