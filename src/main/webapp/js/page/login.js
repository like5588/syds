$(document).ready(function() {
	$("#error_close").click(function(){
		$("#error_info").html("");
		$("#error_div").hide();
	});
	
	$.validate = function(){
		var info = "";
		var userName = $.trim($("#userName").val());
		var password = $.trim($("#password").val());
		var randVal = $.trim($("#randVal").val());
		if(userName.length <= 0){
			info = "请输入账号信息";
			$("#error_info").html(info);
			$("#error_div").show();
			$("#userName")[0].focus();  
			return false;
		}
		if(password.length <= 0){
			info = "请输入密码";
			$("#error_info").html(info);
			$("#error_div").show();
			$("#password")[0].focus();  
			return false;
		}
		if(randVal.length <= 0){
			info = "请输入验证码";
			$("#error_info").html(info);
			$("#error_div").show();
			$("#randVal")[0].focus();  
			return false;
		}
		return true;
	}
	
	$("#logon").click(function(){
		if($.validate()){
			var params = $("#form1").serialize();
			$.ajax({
				url : "logon.do",
				type : 'POST',
				data : params,
				dataType:"json",
				cache : false,
				success : function(result) {
					if(result.result == 0){
						window.location.href = result.redirect;
					}else if(result.result == 1){
						info = "账号或密码错误";
						$("#error_info").html(info);
						$("#error_div").show();
						$("#userName")[0].focus(); 
						$("#img").prop("src","randImageServlet?tmp="+Math.random());
					}else if(result.result == 2){
						info = "验证码错误";
						$("#error_info").html(info);
						$("#error_div").show();
						$("#userName")[0].focus(); 
						$("#img").prop("src","randImageServlet?tmp="+Math.random());
					}else if(result.result == 3){
						info = "参数不能为空";
						$("#error_info").html(info);
						$("#error_div").show();
						$("#randVal")[0].focus(); 
						$("#img").prop("src","randImageServlet?tmp="+Math.random());
					}else if(result.result == -1){
						info = "系统错误";
						$("#error_info").html(info);
						$("#error_div").show();
						$("#userName")[0].focus(); 
						$("#img").prop("src","randImageServlet?tmp="+Math.random());
					}
				},
				error: function(result){
					info = "系统错误";
					$("#error_info").html(info);
					$("#error_div").show();
					$("#userName")[0].focus(); 
				}
			});
		}
	});
});

document.onkeydown=keyDownSearch;  

function keyDownSearch(e) {    
    // 兼容FF和IE和Opera    
    var theEvent = e || window.event;    
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
    if (code == 13) {    
    	$("#logon").trigger("click");
        return false;    
    }    
    return true;    
}  

function refresh(_this){
	$(_this).prop("src","randImageServlet?tmp="+Math.random());
}