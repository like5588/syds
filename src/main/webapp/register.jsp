<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
window.location.hash = "#title"; 

/*--获取网页传递的参数--*/    
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
$(document).ready(function(){ 
	//电信大赛获取32个赛区
	if(request("p").length>0 && request("p") == '5'){
		$.ajax({
			url: "${pageContext.request.contextPath}/getAreas.do",
			type: "post",
			cache: false,  
			contentType: "application/json", 
			dataType:"json",
			success: function(msg){ 
				$.each(msg, function (n, value) { 
					var option = "<option value=\""+value.value+"\">" +value.value+ "</option>";  
					$("#dx_area").append(option);
				}); 
			}
		});
	}
	
	$("#saveUserInfo").click(function(){
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		
		var param = $("#userInfo_form").serialize();
		if(name.length <=0){
			alert("请输入姓名！");
			return;
		}
		var sMobile = document.mobileform.mobile.value 
		if(!(/^1[0-9]{10}$/.test(sMobile))){ 
		        alert("不是正确的11位手机号！"); 
		        document.mobileform.mobile.focus(); 
		        return false; 
		} 
		if(request("p").length>0 && request("p") == '5'){
			var dxArea = $("#dx_area").val();
			if(dxArea.length <=0){
				alert("请选择赛区！");
				return;
			}
		}
		$.ajax({
			type: "post", 
			  data : param+"&p="+request("p"),
			  url: "${pageContext.request.contextPath}/pageSaveUser.do",  
			  cache: false,  
			  dataType: "json",
		  	  success: function(msg) {
		  		  if(msg.result=="0"){
		  			  alert("保存成功");
		  			  var referer = decodeURIComponent(request("referer")).replace("#title","");
		  			  window.location.href=referer;
		  		  }else if(msg.result=="1" || msg.result=="-1"){
		  			  alert("保存失败");
		  		  }else if(msg.result=="2"){
		  			  alert("输入信息有误");
		  		  }else if(msg.result=="3"){
		  			  alert("用户已存在,请直接登录");
		  			  if(request("p").length>0 && request("p") == '5'){
		  				window.location.href="http://www.hicdma.com/views/pages/login.jsp?redirect=http://localhost:8080/syds/teleCom_index.jsp";
		  			  }else{
		  				window.location.href="http://www.hicdma.com/views/pages/login.jsp?redirect=http://localhost:8080/syds/mh_index.jsp";
		  			  }
		  			  
		  		  }
			  }
		});
	});
});
</script>
<title>注册新用户</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
</head>
<body>
<c:if test="${param.p == '5'}">
	<%@include file="/teleCom_header.jsp"%>
</c:if>
<c:if test="${param.p == '9'}">
	<%@include file="/mh_top.jsp"%>
</c:if>
<!--内容开始-->

<div class="yg_33province_bg">

<form id="userInfo_form" style="margin: 10px 30px; border: 1px solid #dedede;-moz-border-radius: 5px;-webkit-border-radius: 5px;" name="mobileform">
	<div class="yg_registered">
    	<ul>
        	<li>姓名：</li><li class="textwid" ><input id="name" name="name" type="text" class="textbox" /></li>
        </ul>
        <ul>
        	<li>电话号码：</li><li class="textwid" ><input id="mobile" name="mobile" type="text" class="textbox" /></li>
        </ul>
        <c:if test="${param.p == '5'}">
        <ul>
        	<li>赛区：</li>
        	<li class="textwid" >
        		<select id="dx_area" class="textbox" name="dxArea">
        		</select>
        	</li>
        </ul>
    	</c:if>
    </div>
    
    <div class="yg_promise">
    	<ul>
        	<li style="text-align: center;">摄影大展承诺书</li>
        </ul>
    </div>
    <div class="yg_promisebook">本承诺书是您就参与“2015 “天翼手机杯”全国摄影大展”（以下称“摄影大展”，公众号：摄影大赛）而中国电信及关联公司作出的承诺，请您请您仔细阅读下列条款。一旦您点击“同意”，即表明您已阅读并接受下列条款作为您向中国电信作出的承诺，愿意按照本承诺书的内容履行您的义务，并以此作为解决双方间纠纷的依据。
一、本人保证为参加摄影大展作品的唯一创作者，对参展作品拥有完整、排他的著作权。摄影大展期间，本人同意中国电信及关联公司为开展摄影大展需要而无偿使用参赛作品，并保证在摄影大展评选结果揭晓前未曾且不得许可他人使用、开发该作品。
二、本人保证参展作品未侵犯任何第三方的合法权益（包括但不限于知识产权、肖像权、隐私权等各项权益），如因该等作品侵犯第三方合法权益或因本人的其他过错而使中国电信或其关联公司（包括但不限于中国电信的分公司、子公司、下属机构）遭受任何名誉或经济上的损失，本人将根据中国电信的要求采取相关措施，以保证中国电信免受上述损失。中国电信同时保留向本人追究和索赔的权利。
三、本人许可中国电信及其关联公司使用本人参加摄影大展并获奖的摄影作品（以下简称“获奖作品” ，）用于其定制手机的壁纸图片、各项业务宣传活动及其他商业用途。本人同意中国电信及其关联公司根据经营需要及业务要求可以适当修改获奖作品（包括但不限于对作品进行必要的筛选、重新编排、编辑、修改、技术处理及相应的文字诠释、注释等），以及为设计、制作相关主题图片和进行各项业务及宣传活动而许可他人使用上述作品，以满足设计、制作及业务宣传的要求。
三、本人许可中国电信及其关联公司使用本人参加摄影大展并获奖的摄影作品（以下简称“获奖作品” ，）用于其定制手机的壁纸图片、各项业务宣传活动及其他商业用途。本人同意中国电信及其关联公司根据经营需要及业务要求可以适当修改获奖作品（包括但不限于对作品进行必要的筛选、重新编排、编辑、修改、技术处理及相应的文字诠释、注释等），以及为设计、制作相关主题图片和进行各项业务及宣传活动而许可他人使用上述作品，以满足设计、制作及业务宣传的要求。
</div>
	<div class="yg_pro_button_box">
    	<ul>
        	<a href="javascript:void(0);" id="saveUserInfo"><li class="yg_pro_button"></li></a>
            <a href="javascript:void(0);"><li class="yg_pro_button1" style="margin-left:160px;"></li></a>
        
    	</ul>
    </div>
</form>
</div>


<!--#include file="foot.html"-->
</body>
</html>
