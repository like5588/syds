var isShowMask=false;
$(document).ready(function(){

	$(window).scroll(function (){
	        if ($(document).height() <= $(window).scrollTop() + $(window).height() && stat && scroll && !isShowMask) {
	        	stat = false;
	        	morePhotos();
	        }
	});
});

var myPhoto_div = "<div class=\"panel panel-default image-panel\">" +
				  "<div class=\"panel-body image-body\">" +
				  "<img class=\"lazy\" src=\"../images/placeholder.gif\" imgurl=\"\${compress}\${photoInfo.photoUrl}\"" +
				  "data-original=\"\${thumbnail}\${photoInfo.photoUrl}\" style=\"width: 100%;\" onclick=\"image_show(this);\">" +
				  "</div>" +
				  "<div  class=\"image-title\">\${photoInfo.photoTitle}" +
				  	"<div class=\"laud-unchecked\"></div>" +
				  	"<div class=\"laud-num\">\${photoInfo.laudNum}</div>" +
				  "</div>" +
				  "<div class=\"botton\">" +
				  	"<div class=\"author\">" +
				  	 	"<div class=\"image-tm\">\${photoInfo.uploadDateStr}</div>" +
				  	"</div>" +
				  "</div>" +
				"</div>";

var pageIndex = 1;	//页码
var stat = true;
function morePhotos(){
	pageIndex++;
	$("#loadMore").show();
	$.ajax({
		url : "moreMyPhotos.do",
		type : 'POST',
		data : "pageIndex="+pageIndex+"&userSource=2&competitionType=5&openid="+$("#openid").val(),
		dataType : "json",
		cache : false,
		success : function(msg) {
			if(msg.listSize > 0){
				for(var o in msg.photoInfoList){  
					var html = myPhoto_div.replace(/\${photoInfo.photoUrl}/g, msg.photoInfoList[o].photoUrl);
					html = html.replace(/\${photoInfo.photoUrl}/g, msg.photoInfoList[o].photoUrl);
					html = html.replace(/\${photoInfo.photoTitle}/g, msg.photoInfoList[o].photoTitle);
					html = html.replace(/\${photoInfo.laudNum}/g, msg.photoInfoList[o].laudNum);
					html = html.replace(/\${photoInfo.uploadDateStr}/g, msg.photoInfoList[o].uploadDateStr);
					html = html.replace(/\${compress}/g, msg.compress);
					html = html.replace(/\${thumbnail}/g, msg.thumbnail);
					$("#photo_content").append(html);
				};
				
				if(msg.listSize < 10){
					$("#bottom").show();
					scroll=false;
					pageIndex--;
				}
			}else{
				$("#bottom").show();
				scroll=false;
				pageIndex--;
			}
			$("#loadMore").hide();
			stat = true;
		}
	}); 
}

function image_show(_this){
	showMask();
	$("#swiper_div").find("img").eq(0).attr("src","");
	var ch=$(window).height();
	var w = $(window).width();
	/*去掉iphone手机滑动默认行为*/
	$('body').on('touchmove', function (event) {
	    event.preventDefault();
	});
	/* 去掉页面滚动条 */
	 $(document.body).css({
	    "overflow-y":"hidden"
	  });
	var imgUrl = $(_this).attr("imgurl");
	$("#swiper_div").find("img").eq(0).attr("src",imgUrl);
	var img = new Image(); 
	img.src = $("#swiper_div").find("img").eq(0).attr("src") ; 
	var iw = img.width; 
	var ih = img.height;
	if(iw/ih>=w/ch){
		$("#swiper_div").find("img").eq(0).css("width",w);
		$("#swiper_div").find("img").eq(0).css("height","auto");
	}
	if(iw/ih<w/ch){
		$("#swiper_div").find("img").eq(0).css("height",ch);
		$("#swiper_div").find("img").eq(0).css("width","auto");
	}
//	$("#swiper_div").find("img").eq(0).css("height",ch-40);
}

//显示蒙版
function showMask() {
	var dh = $(document).scrollTop();
	var ch = $(window).height();
	isShowMask =true;
	$("#mask").css("height",ch+dh);
	$("#mask").css("padding-top",dh+"px");
	$("#mask").show();
}
	
//隐藏蒙版
function hideMask() {
  $("#mask").hide();
  /* 添加页面滚动条 */
  $(document.body).css({
    "overflow-x":"auto",
    "overflow-y":"auto"
  });
  $('body').off('touchmove');
  isShowMask =false;
}

function laud(_this){
	alert("点赞成功");
	$(_this).prop("class","laud-checked");
	$(_this).removeProp("onclick");
}