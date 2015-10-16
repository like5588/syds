<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
var allPerson = 0,allPhoto = 0;
$(document).ready(function(){ 

	//读取当前用户的所有通过审核的照片信息   通过审核 	photo_status =’1‘
		$.ajax({
			url: "${pageContext.request.contextPath}/teleComPhotos.do",
			type: "post",
			cache: false,  
			contentType: "application/json", 
			dataType:"json",
			success: function(msg){  
				$.each(msg.info, function (n, key) {
					allPerson += key.unum ;
					allPhoto  += key.pnum ;
					$("#main").find("li").eq(n).find(".yg_33province_color").eq(0).text(key.unum);
					$("#main").find("li").eq(n).find(".yg_33province_color").eq(1).text(key.pnum);
					$("#main").find("li").eq(n).find(".yg_33province_head").eq(0).text("赛区负责人："+key.data_value);
					//var m = n+1;
					$("#main").find("li").eq(n).find("a").prop("href","teleCom_photos.jsp?competitionArea="+ encodeURIComponent(key.data_showvalue));
				}); 
				$.each(msg.sortInfo, function (n, key) {
					var m = n+1;
					$("#"+key.id).find("a").before("<div class=\"yg_33province_list jz\" style=\"background:url(img/jz1.png) no-repeat;\"><span>"+m+"</span></div>");					
				}); 
			}
		});
});
</script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
<title>天翼4G 分享美好</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
</head>

<body>
<%@include file="teleCom_header.jsp"%>
</div>
<!--33省内容开始-->
<div class="yg_33province_bg">
	<div class="yg_33province" id="main">
    	
        	<li>
            	<div class="yg_33province_list" style="background:url(img/jituan.jpg) no-repeat;" id="1">
                
                	<a href=""><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/beijing.jpg) no-repeat;" id="2">
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/tianjin.jpg) no-repeat;" id="3">
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
        
        
       
        	<li>
            	<div class="yg_33province_list" style="background:url(img/hebei.jpg) no-repeat;" id="4">
         
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/shanxi.jpg) no-repeat;" id="5">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/neimeng.jpg) no-repeat;" id="6">
              
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
        
        	<li>
            	<div class="yg_33province_list" style="background:url(img/liaoning.jpg) no-repeat;" id="7">
               
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/jilin.jpg) no-repeat;" id="8">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/heilongjiang.jpg) no-repeat;" id="9">
            
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
        
        	<li>
            	<div class="yg_33province_list" style="background:url(img/shanghai.jpg) no-repeat;" id="10">
                
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/jiangsu.jpg) no-repeat;" id="11">
            
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/zhejiang.jpg) no-repeat;" id="12">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
        
        	<li>
            	<div class="yg_33province_list" style="background:url(img/anhui.jpg) no-repeat;" id="13">
             
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/fujian.jpg) no-repeat;" id="14">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/jiangxi.jpg) no-repeat;" id="15">
             
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
       
        
       
        	<li>
            	<div class="yg_33province_list" style="background:url(img/shandong.jpg) no-repeat;" id="16">
              
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/henan.jpg) no-repeat;" id="17">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/hubei.jpg) no-repeat;" id="18">
              
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
        	<li>
            	<div class="yg_33province_list" style="background:url(img/hunan.jpg) no-repeat;" id="19">
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/guangdong.jpg) no-repeat;" id="20">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/guangxi.jpg) no-repeat;" id="21">
              
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
       
        	<li>
            	<div class="yg_33province_list" style="background:url(img/hainan.jpg) no-repeat;" id="22">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/chongqing.jpg) no-repeat;" id="23">
              
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/sichuan.jpg) no-repeat;" id="24">
             
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
        
       
        	<li>
            	<div class="yg_33province_list" style="background:url(img/guizhou.jpg) no-repeat;" id="25">
              
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/yunnan.jpg) no-repeat;" id="26">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/xizang.jpg) no-repeat;" id="27">
             
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
       
        
        
        	<li>
            	<div class="yg_33province_list" style="background:url(img/sanxi.jpg) no-repeat;" id="28">
              
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/ganshu.jpg) no-repeat;" id="29">
                
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/qinghai.jpg) no-repeat;" id="30">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
       
        
        
        	<li>
            	<div class="yg_33province_list" style="background:url(img/ningxia.jpg) no-repeat;" id="31">
                
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/xinjiang.jpg) no-repeat;" id="32">
              
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
            
            
            <li>
            	<div class="yg_33province_list" style="background:url(img/haobai.jpg) no-repeat;" id="33">
               
                
                	<a href="#"><div class="yg_33province_number">参赛人数：<span class="yg_33province_color">300</span>  参赛作品：<span class="yg_33province_color">399559</span></div></a>
                    
                    <div class="yg_33province_head">赛区负责人：XXX</div>
                    
                </div>
            </li>
        
    </div>

</div>

<!--33省内容结束-->

<%@include file="teleCom_foot.jsp"%>
</body>
</html>