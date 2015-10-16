<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath}/css/wheelSurf.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/a1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQueryRotate.2.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easing.min.js"></script>
<div style="width;100%;background-color:#c71a1a;">
    <div style="width:1000px; height:510px; padding-top:5px; margin:auto; background-color:#c71a1a;" id="lottery_d">
		<div class="ly-plate">
			<div class="rotate-bg"></div>
			<div class="lottery-star"><img src="images/rotate-static.png" id="lotteryBtn"></div>
		</div>
	    <div style=" float:right; width:450px; padding-top:100px; text-align:left;">
	        <h1 style="font-size:3em; color:#fff; font-family:'微软雅黑'">摄影抽奖赢手机！</h1>
	        <p style="color:#fff; margin-bottom:15px;">已经有<strong style="margin:0 5px; color:#FF9;">${winNum}</strong>位用户中奖了！赶快来参与</p>
			<div class="listbox1">
			    <h1 style="color:#F00">↓↓↓ 火热抽奖中 ↓↓↓</h1>
			    <div id="listbox1">
			       <ul id="userlist">
			       	<%@include file="recently_lottery.jsp" %>
				   </ul>
			     </div>
		  	</div>
		  	<a href="app/mh/lottery_history.jsp" target="view_window" style="width:119px; display:block; overflow:hidden; line-height:500px;height:42px;margin-top:20px;background:url(images/history.png)"></a>
	    </div>
    </div> 	
    <div style="padding-bottom: 15px;padding-top: 10px;">
    	<div style="color: #f3dfb1; width:1000px;margin:0 auto;font-size:14px;background-color:#9a1818; border:1px solid #de4f4f; padding: 10px;line-height: 28px;">
    	    	<h2>抽奖须知：</h1>
	    		<p>1、用户参与摄影赢大奖，每人每天最多进行五次抽奖操作，当天未使用的抽奖次数不会累加到第二天，请大家积极参与。</p>
	  			<p>2、照片点赞参与抽奖，用户可通过照片点赞的方式参与抽奖活动，其中每点十个赞获得一次抽奖机会。每人每天通过照片点赞最多可获得两次抽奖机会。</p>
	  			<p>3、上传照片参与抽奖，用户可通过上传照片的方式参与抽奖活动，其中每上传五张照片可获得一次抽奖机会， 每人每天通过上传照片最多可获得三次抽奖机会。</p>
	  			<p>4、中奖用户，需要完善个人信息，会有工作人员在3-5个工作日内将奖品邮寄到您的手中，请耐心等候。
	  			<p>还等什么，快来参与吧！<p>
    	</div>
    </div>
    <div class="dst" id="dst">
        <div style="float:left; width:110px;"><img src="images/love.png" style="width: 110px;"></div>
        <div style="float:right; width:340px;">
            <h1 style="font-family:'微软雅黑'; font-size:1.8em; color:#f00; margin-bottom:5px; margin-top: 0px;" id="h1_content"></h1>
            <p>已经有<strong style="margin:0 5px; color:#f00;">${winNum}</strong>位用户中奖了！</p>
            <p style="margin-top:8px;margin-bottom: 7px;"><a href="javascript:hide_dst0();" id="dst_a" style="width:119px; display:block; overflow:hidden; line-height:500px;height:42px; background:url(images/but.png)"></a></p>
      		<p>获奖奖品会有工作人员在接下来通过邮寄的方式投递到您的手中。</p>
        </div>
    </div>
    
    <div class="dst" id="dst0">
        <div style="float:left; width:110px;"><img src="images/cry.png" style="width: 110px;"></div>
        <div style="float:right; width:340px;">
            <h1 style="font-family:'微软雅黑'; font-size:1.8em; color:#f00; margin-bottom:5px;" id="h1_content">很遗憾，没有中奖，请再接再厉！</h1>
            <p style="margin-top:15px;"><a href="javascript:hide_dst0();" style="width:119px; display:block; overflow:hidden; line-height:500px;height:42px; background:url(images/sure.png)"></a></p>
        </div>
    </div>
    
    <div class="dst" id="dst1">
        <div style="text-align:center;width:480px;">
            <h1 style="font-family:'微软雅黑'; font-size:1.8em; color:#f00; margin-bottom:5px;">参与抽奖，请先登录！</h1>
            <p style="margin-top:50px;padding-left:180px;"><a href="http://www.hicdma.com/mh/views/pages/login.jsp?redirect=http://yxhd.hicdma.com/syds/mh_index.jsp#lottery_d" style="width:119px; display:block; overflow:hidden; line-height:500px;height:42px; background:url(images/login.png)"></a></p>
        </div>
    </div>
    
    <div class="dst" id="dst2">
        <div style="text-align:center;width:480px;">
            <h1 style="font-family:'微软雅黑'; font-size:1.8em; color:#f00; margin-bottom:5px;" id="dst2_h"></h1>
            <p style="margin-top:50px;padding-left:180px;"><a href="javascript:hide_dst2();" style="width:119px; display:block; overflow:hidden; line-height:500px;height:42px; background:url(images/sure.png)"></a></p>
        </div>
    </div>
    
    <div class="dst" id="edit_info" style="padding: 15px;height: 300px;">
        	<h1 style="font-family:'微软雅黑'; font-size:1.4em; color:#f00; margin:5px 0;">请完善用户信息</h1>
        	<form class="form-horizontal" style="margin-top: 10px;" id="form1">
			  <div class="form-group">
			    <label for="userName" class="col-xs-2 control-label">用户名称<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="userName" name="userName" placeholder="用户名称">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="mobile" class="col-xs-2 control-label">手机号<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="mobile" name="mobile" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="postcode" class="col-xs-2 control-label">邮编<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="postcode" name="postcode" placeholder="邮编">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="address" class="col-xs-2 control-label">地址<span style="color: red;">*</span>：</label>
			    <div class="col-xs-8">
			      <input type="text" class="form-control" style="width: 260px;height:30px;" id="address"  name="address" placeholder="地址">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-xs-offset-2 col-xs-8" style="margin-left: 115px;">
			      <a href="javascript:save();" style="width:119px; display:block; overflow:hidden; line-height:500px;height:42px; background:url(images/sure.png)"></a>
			    </div>
			  </div>
			</form>
    </div>
 </div> 
    <script type="text/javascript">
  //ajax拦截session失效
    $.ajaxSetup({   
        contentType:"application/x-www-form-urlencoded;charset=utf-8",   
        cache:false ,   
        complete:function(data,TS){
        	var res=eval('('+data.responseText+')');  
           if(res.sessionState == "timeout"){
			   $("#dst1").fadeIn();
           }
        }   
    });
  	function hide_dst2(){
  		$("#lotteryBtn").on("click",lottery);
  		$("#dst2").hide();
  	}
  	function hide_dst0(){
  		$("#lotteryBtn").on("click",lottery);
  		$("#dst0").hide();
  	}
  	function hide_dst(){
  		$("#lotteryBtn").on("click",lottery);
  		$("#dst").hide();
  	}
  	function edit_info(){
  		$("#dst").hide();
  		$("#edit_info").show();
  	}
  	function save(){
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
			url : "mh/saveLotteryInfo.do",
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
				$("#lotteryBtn").on("click",lottery);
			}
  		});
  	}
	function init_Scroll(){
	     var scrollPics = new scrollVertical('listbox1','userlist');
	         scrollPics.speed = 10; /* 调节speed值可以改变滚动间隙 */
	         scrollPics.isPause = true;
	 
	     var timer_start = setTimeout(function(){clearTimeout(timer_start);scrollPics.isPause = false;},1000);
	
	     Event.addEvent(scrollPics.scrollArea,"mouseover",function(){scrollPics.isPause = true;});
	     Event.addEvent(scrollPics.scrollArea,"mouseout",function(){scrollPics.isPause = false;});
	}
	Event.addEvent(window,'load',init_Scroll);
	
 		var timeOut = function(){  //超时函数
 			$("#lotteryBtn").rotate({
 				angle:0, 
 				duration: 10000, 
 				animateTo: 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
 				callback:function(){
 					alert('亲！没有中奖哦！！')
 				}
 			}); 
 		}; 
 		var rotateFunc = function(angle,dur,info,status){ 
 			$('#lotteryBtn').stopRotate();
 			$("#lotteryBtn").rotate({
 				angle:0, 
 				duration: dur, 
 				animateTo: angle,
 				callback:function(){
 					var text = "";
					if(info == 1){
						text = "恭喜你获得：千元手机大奖！";
					}else if(info == 2){
						text = "恭喜你获得：100元充值卡奖品";
					}else if(info == 3){
						text = "恭喜你获得：50元充值卡奖品";
					}else if(info == 4){
						text = "恭喜你获得：20元充值卡奖品";
					}else if(info == 0){
						text = "很遗憾，没有中奖，请再接再厉";
	 					$("#dst0").fadeIn();
	 					return;
					}
					$("#h1_content").text(text);
					if(status == 1){
						$("#dst_a").css("background","url(images/but.png)")
						$("#dst_a").on("click",edit_info);
					}else if(status == 2){
						$("#dst_a").css("background","url(images/sure.png)")
						$("#dst_a").on("click",hide_dst);
					}
 					$("#dst").fadeIn();
 				}
 			}); 
 		};
 		
 		var lottery = function(){
			$("#lotteryBtn").off("click");
			var time = [1];
			time = time[Math.floor(Math.random()*time.length)];
			if(time==0){
				timeOut(); //网络超时
			}
			if(time==1){
				$.ajax({
					url : "mh/lottery.do",
					type : 'POST',
					dataType:"json",
					cache : false,
					success: function(msg){
						if(msg.result == 0){
							rotateFunc(msg.angle,msg.duration,msg.info,msg.status);
						}else if(msg.result == 2){
							$("#dst2_h").text("今天抽奖已满5次，请明天再来");
							$("#dst2").fadeIn();
						}else if(msg.result == 3){
							$("#dst2_h").text("请参与点赞或者上传照片以获取更多抽奖机会");
							$("#dst2").fadeIn();
						}
					}
				})
			}
		};
		$("#lotteryBtn").on("click",lottery);
     </script>  	
  	