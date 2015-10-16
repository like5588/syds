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
<style type="text/css">
.summary_big{
		width:230px;
		height:210px;
		top:510px;
		left:0;
		background:url(../mh_images/summarybg.png);
		position:absolute;}		
.summary_bigtxt{
		margin:15px 10px;
		width:210px;
		height:auto;
		line-height:22px;
		font-size:14px;
		color:#fff;
		text-align:left;
		word-wrap:break-word;
		word-break:break-all;}	
</style>
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
	loadPage("checkPhotos.do");
	$("#logout").click(function(){
		if(confirm("确认注销？")){
			$.ajax({
				url : "../logout.do",
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
});
function loadPage(_page){
	  $.ajax({  
		  type: "get",  
		  url: _page,  
		  cache: false,  
	  	  success: function(msg) {  
	  		  $("#main").html(msg);
		  }
	  }); 
}
</script>
<title>天翼2015摄影大赛审核平台</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" style="color: #FFFFFF;" href="#">天翼2015摄影大赛审核平台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li style="border-right: 1px solid #eee;"><a href="#">${judge.petName}，您好</a></li>
	            <li><a href="#" id="logout">注销</a></li>
	          </ul>
        	</div>
		</div>
	</nav>
	
	<div id="main" style="margin: 20px;">
	</div>
</body>
</html>


