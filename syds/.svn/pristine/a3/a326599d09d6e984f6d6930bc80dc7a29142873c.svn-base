<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ty.photography.model.JudgeLogon" %>
<%@ page import="com.ty.photography.common.CommonUtils" %>
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
	$(".dropdown-menu").css({minWidth:$(".dropdown-toggle").width()+30});
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
	
	$("#changePw").click(function(){
		$("#password").val("");
		$("#newPassword").val("");
		$("#confirm").val("");
		$('#myModal').modal('show');
	});
	
	$("#confirmNewPwd").click(function(){
		$("#password")[0].focus(); 
		var param = $("#pwd_form").serialize();
		$.ajax({
			url : "${pageContext.request.contextPath}/sys/changePwd.do",
			type : 'POST',
			data : param,
			dataType:"json",
			cache : false,
			success : function(json) {
				if(json.result == 0){
					alert("修改完成");
					$('#myModal').modal('hide');
				}else if(json.result == 1){
					alert("系统错误");
					$("#password")[0].focus(); 
				}else if(json.result == 2){
					alert("密码有误");
					$("#password")[0].focus(); 
				}else if(json.result == 3){
					alert("重新输入密码不一致");
					$("#confirm")[0].focus(); 
				}else if(json.result == 4){
					alert("参数不能为空");
				}
			}
		});
	});
	
	$("#judgeInfo").click(function(){
		$("#petname").val("");
		$("#mobile").val("");
		$("#email").val("");
		$("#dept").val("");
		$("#comment").val("");
		$.ajax({
			url : "${pageContext.request.contextPath}/sys/judgeInfo.do",
			type : 'POST',
			dataType:"json",
			cache : false,
			success : function(json) {
				if(json.result == "0"){
					$("#petname").val(json.petname);
					$("#mobile").val(json.mobile);
					$("#email").val(json.email);
					$("#dept").val(json.dept);
					$("#comment").val(json.comment);
				}
			}
		});
		$('#judgeModal').modal('show');
	});
	
	$("#saveJudgeInfo").click(function(){
		var param = $("#judgeInfo_form").serialize();
		$.ajax({
			url : "${pageContext.request.contextPath}/sys/saveJudgeInfo.do",
			type : 'POST',
			data : param,
			dataType:"json",
			cache : false,
			success : function(json) {
				if(json.result == "0"){
					$(".dropdown-toggle").text(json.petname+"，您好");
					alert("修改成功");
					$('#judgeModal').modal('hide');
				}else{
					alert("修改失败");
				}
			}
		});
	});
});
</script>
<%
JudgeLogon judge = (JudgeLogon)request.getSession().getAttribute("judge");
if(judge == null){
	String basePath = CommonUtils.parseProperties("BASE_URL");
	response.setContentType("text/html; charset=utf-8");
	String redirectPath = basePath + "/pingxuan.jsp";
	response.sendRedirect(redirectPath);
}

%>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" style="color: #FFFFFF;" href="#">天翼2015摄影大赛评选平台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	          	<%if(judge != null) {%>
	            <li class="dropdown" style="border-right: 1px solid #eee;">
	            	<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button"
	            		 aria-haspopup="true" aria-expanded="false"><%=judge.getPetName()%>，您好</a>
	            	<ul class="dropdown-menu">
	            		<li><a href="#" id="judgeInfo">评委信息</a></li>
	            		<li><a href="#" id="changePw">修改密码</a></li>
	            	</ul>	
	            </li>
	            <li><a href="#" id="logout">注销</a></li>
	            <% }%>
	          </ul>
        	</div>
		</div>
	</nav>
	
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改密码</h4>
      </div>
      <div class="modal-body">
       	<form class="form-horizontal" id="pwd_form">
		  <div class="form-group">
		    <label for="password" class="col-sm-4 control-label">现在的密码</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" id="password" name="password" placeholder="现在的密码">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="newPassword" class="col-sm-4 control-label">设置新的密码</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="请输入新密码">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="confirm" class="col-sm-4 control-label">重新输入密码</label>
		    <div class="col-sm-8">
		      <input type="password" class="form-control" id="confirm" name="confirm" placeholder="确认新密码">
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="confirmNewPwd">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="judgeModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">评委信息</h4>
      </div>
      <div class="modal-body">
       	<form class="form-horizontal" id="judgeInfo_form">
		  <div class="form-group">
		    <label for="petname" class="col-sm-3 control-label">名称:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" style="width: 70%;" id="petname" name="petname" placeholder="名称">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="mobile" class="col-sm-3 control-label">手机号:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" style="width: 70%;" id="mobile" name="mobile" placeholder="手机号">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="email" class="col-sm-3 control-label">邮箱:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" style="width: 70%;" id="email" name="email" placeholder="邮箱">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="dept" class="col-sm-3 control-label">部门:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" style="width: 70%;" id="dept" name="dept" placeholder="部门">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="dept" class="col-sm-3 control-label">备注:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" style="width: 70%;" id="comment" name="comment" placeholder="50字以内">
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="saveJudgeInfo">保存</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->