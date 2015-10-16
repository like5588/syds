var params = "";
function gotoPage(_page){
	var data = params+"&pageIndex="+_page;
	$.ajax({
		url : baseUrl+"/sys/dx_pingxuan.do",  
		type : 'POST',
		data : data,
		dataType:"html",
		cache : false,
		success : function(msg){
			$("#home").html(msg);
			$("#checkAll").prop('checked',false);
		}
	});
}
$(document).ready(function(){
	
	params = $("#query_form").serialize();
	$.ajax({  
		  type: "POST",  
		  data : params,
		  url: baseUrl+"/sys/dx_pingxuan.do",  
		  cache: false,  
	  	  success: function(msg) {  
	  		  $("#home").html(msg);
		  }
	  }); 
	
	$("#query").click(function(){
		params = $("#query_form").serialize();
		$.ajax({
			url : baseUrl+"/sys/dx_pingxuan.do",
			type : 'POST',
			data : params,
			dataType:"html",
			cache : false,
			success : function(msg){
				$("#home").html(msg);
				$("#checkAll").prop('checked',false);
			}
		});
	});
	
	$("#checkAll").click(function(){
		if($(this).prop('checked')){
			$("input[type='checkbox']").each(function(){
				if($(this).attr("id") != "invertCheck"){
					this.checked =true;
				}
			});
		}else{
			$("input[type='checkbox']").each(function(){
				if($(this).attr("id") != "invertCheck"){
					this.checked =false;
				}
			});
		}
	});
	
	$("#invertCheck").click(function(){
		var checkSize=0;
		var arrChk= $("input[type='checkbox']");
		$("input[type='checkbox']").each(function(){
			if($(this).attr("id") != "checkAll" && $(this).attr("id") != "invertCheck"){
				if(!$(this).prop("checked")){
						$(this).prop('checked',true);
						checkSize++;
						if(checkSize==arrChk.length-2){
							if(!$("#checkAll").prop('checked')){
								$("#checkAll").prop('checked',true);
								return false;
							}
						}
				}else{
					$(this).prop('checked',false);
					$("#checkAll").prop('checked',false);
				}
			}
		});
	});
	
	$(document).on("click", "input[type='checkbox']", function() {
		var checkSize=0;
		var arrChk= $("input[type='checkbox']");
		if($(this).attr("id") != "checkAll" && $(this).attr("id") != "invertCheck"){
			$("input[type='checkbox']").each(function(){
				if($(this).attr("id") != "checkAll" && $(this).attr("id") != "invertCheck"){
					if(!$(this).prop("checked")){
						if($("#checkAll").prop('checked')){
							$("#checkAll").prop('checked',false);
							return false;
						}
					}else{
						checkSize++;
						if(checkSize==arrChk.length-2){
							if(!$("#checkAll").prop('checked')){
								$("#checkAll").prop('checked',true);
								return false;
							}
						}
					}
				}
			});
		}
	});


});

function pass(newStats,oldStats){
	var data = $("#photos_form").serialize(); 
	var p = "";
	$("input[type='checkbox']").each(function(){
		if($(this).attr("id") != "checkAll" && $(this).attr("id") != "invertCheck"){
			if($(this).prop("checked")){
				p +=$(this).val();
				return;
			}
		}
	});
	if(p.length <=0){
		alert("请选择作品！");
		return;
	}
	$.ajax({
		  type: "post", 
		  data : data+"&newStats="+newStats+"&oldStats="+oldStats,
		  url: baseUrl+"/sys/updateIsSelect.do",  
		  cache: false,  
		  dataType: "json",
	  	  success: function(msg) {
	  		$("#query").trigger("click");
		  }
	});
}

function excellent(){
	var data = $("#photos_form").serialize(); 
	var p = "";
	$("input[type='checkbox']").each(function(){
		if($(this).attr("id") != "checkAll" && $(this).attr("id") != "invertCheck"){
			if($(this).prop("checked")){
				p +=$(this).val();
				return;
			}
		}
	});
	if(p.length <=0){
		alert("请选择作品！");
		return;
	}
	$.ajax({
		  type: "post", 
		  data : data,
		  url: baseUrl+"/sys/excellent.do",  
		  cache: false,  
		  dataType: "json",
	  	  success: function(msg) {
	  		gotoPage($("#_pageIndex").val());
		  }
	});
}
function batchDownload(){
	$.ajax({
		  type: "post", 
		  data : data,
		  url: baseUrl+"/sys/validateDown.do",  
		  cache: false,  
		  dataType: "json",
	  	  success: function(msg) {
	  		if(msg.result=="0"){
	  			window.location.href=baseUrl+"/sys/batchDownload.do";
	  		}else if(msg.result=="1"){
	  			alert("您无权进行此操作");
	  		}else if(msg.result=="2"){
	  			alert("目前还没有入围作品");
	  		}
		  }
	});
	
}