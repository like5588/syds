function publish(_this){
	var stat = $(_this).attr("stat");
	if(stat == 0){
		stat =1;
	}else if(stat == 1){
		stat =0;
	}
	var data = $(_this).parent().parent().parent().serialize(); 
	$.ajax({
		  type: "post", 
		  data : data+"&status="+stat,
		  url: "updateInfo.do",  
		  cache: false,  
		  dataType: "json",
	  	  success: function(msg) {
	  		if(msg.result == "0"){
	  			if(stat == 1){
	  				alert("发布成功");
	  				$(_this).attr("stat","1");
	  				$(_this).text("取消发布");
	  				$(_this).prev().text("已发布");
	  				$(_this).prev().css("color","green");
	  			}else{
	  				alert("取消发布成功");
	  				$(_this).attr("stat","0");
	  				$(_this).text("发布");
	  				$(_this).prev().text("未发布");
	  				$(_this).prev().css("color","red");
	  			}
	  		}
		  }
	});
}
function updateInfo(_this){
	var data = $(_this).parent().parent().parent().serialize(); 
	$.ajax({
		  type: "post", 
		  data : data,
		  url: "updateInfo.do",  
		  cache: false,  
		  dataType: "json",
	  	  success: function(msg) {
	  		if(msg.result == "-1"){
	  			alert("保存失败");
	  		}else{
	  			alert("保存成功");
	  		}
		  }
	});
}
var params = "";
function gotoPage(_page){
	var data = params+"&pageIndex="+_page;
	$.ajax({
		url : "excellentPage.do", 
		type : 'POST',
		data : data,
		dataType:"html",
		cache : false,
		success : function(msg){
			$("#home").html(msg);
		}
	});
}
$(document).ready(function(){
	
	params = $("#query_form").serialize();
	$.ajax({  
		  type: "POST",  
		  data : params,
		  url: "excellentPage.do",  
		  cache: false,  
	  	  success: function(msg) {  
	  		  $("#home").html(msg);
		  }
	  }); 
	
	$("#query").click(function(){
		params = $("#query_form").serialize();
		$.ajax({
			url : "excellentPage.do",
			type : 'POST',
			data : params,
			dataType:"html",
			cache : false,
			success : function(msg){
				$("#home").html(msg);
			}
		});
	});
	
});