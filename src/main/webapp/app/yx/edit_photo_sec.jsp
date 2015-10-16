<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/wx_common.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<title>上传作品</title>
</head>
<body>
	<div class="top">上传作品</div>
	<div style="margin: 10px;">
		<form id="form1">
			<div class="form-group">
			    <label for="photoName">第五步：点击易信页面的"+"，选择图片进行上传，示意图如下：</label>
			    <div style="text-align: center;margin-top: 10px;">
			    	<img style="width: 200px;" src="../images/yx_upload.png">
			    </div>
			</div>
		<!--
			<div id="photo_list_div">
			</div>
		-->
			<div class="search">
				<a href="#" class="search-btn" id="close"> 确定 </a>
			</div>
		</form>
	</div>
	<div align="center" style="font-size: 9px;margin-bottom: 5px;postion:absolute;">注：在法律允许范围内本活动最终解释权归天翼电信终端有限公司所有。</div>
</body>
<script type="text/javascript">
document.addEventListener('YixinJSBridgeReady', function onBridgeReady() {
    YixinJSBridge.call('hideOptionMenu');
});

document.querySelector("#close").onclick = function(){
	YixinJSBridge.call('closeWebView');
};
/*
var list = [];
var i=0;
document.querySelector("#pickImage").onclick = function(){
	alert(i);
//	list[i++] = result.id;
//	 var text = image_content.replace("\${num}",i);
//	 $("#photo_list_div").append(text);
//	 $("#img_id_"+i).prop("src","../../images/IMG_1321.JPG");
	 	YixinJSBridge.invoke("pickImage",
            {type: "album", //从相册
             width:"400",
             quality:"100"}, // 1~100 
             function(result) {
            	list[i] = result.id;
            	var text = image_content.replace("\${num}",i);
            	$("#photo_list_div").append(text);
          	 	$("#img_id_"+i).prop("src","data:"+result.mime+";base64,"+result.data);
              	var width = 200;
				var height = $("#img_id_"+i).height() * width / $("#img_id_"+i).width();
				var rect_top = 0;
				var rect_botton = height;
				var rect_left = 0;
				var rect_right = width;
				if (height > 200) {
					rect_top = (height - 200) / 2;
					rect_botton = height - rect_top;
				}
				if (width > 200) {
					rect_left = (width - 200) / 2;
					rect_right = width - rect_left;
				}
            	 $("#img_id_"+i).css("clip","rect("+rect_top+"px,"+rect_right+"px,"+rect_botton+"px,"+rect_left+"px)");
            	 $("#img_id_"+i).css("marginTop", 10 - (height - 200) / 2 + "px");
            	// alert(result.id);
            	 //alert(result.mime);
            	 //alert(result.data);
            	 i++;
            	 
             }) 
};
document.querySelector('#uploadImage').onclick = function() {
	YixinJSBridge.invoke("uploadImage",
            {ids: list}, 
            function(re) {
          	  alert(re.urls);
            });
};
*/
var image_content="<div style=\"position:relative;height: 200px;\"><img src=\"\" id=\"img_id_\${num}\" style=\"position:absolute;width: 200px;\"></div>"
</script>
</html>