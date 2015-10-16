//图片显示
var myPhoto_div = "<div class=\"panel panel-default image-panel\">" +
				  "<div class=\"panel-body image-body\">" +
				  "<img class=\"lazy\" src=\"../images/placeholder.gif\" imgurl=\"\${compress}\${photoInfo.photoUrl}\"" +
				  "data-original=\"\${thumbnail}\${photoInfo.photoUrl}\" style=\"width: 100%;\" onclick=\"previewImage(this);\">" +
				  "</div>" +
				  "<div  class=\"image-title\">\${photoInfo.photoTitle}" +
				  	"<div class=\"laud-unchecked\"></div>" +
				  	"<div class=\"laud-num\">\${photoInfo.laudNum}</div>" +
				  "</div>" +
				  "<div class=\"botton\">" +
				  	"<div class=\"author\">\${photoInfo.userName}" +
				  	 	"<div class=\"image-tm\">\${photoInfo.uploadDateStr}</div>" +
				  	"</div>" +
				  "</div>" +
				"</div>";

var pageIndex = 1;	//页码
var stat = true;
var images = new Array();
var imageIndex = 0;
$(document).ready(function(){
	for(var o in photoInfoDtoList){
		images[imageIndex++] = compress+photoInfoDtoList[o].photoUrl;
		loadImage(photoInfoDtoList[o]);
	};
	$("img.lazy").lazyload();
	$(window).scroll(function (){
	        if ($(document).height() <= $(window).scrollTop() + $(window).height() && stat && scroll) {
	        	stat = false;
	        	morePhotos();
	        }
	});
});

function loadImage(photoInfoDto){
	var html = myPhoto_div.replace(/\${photoInfo.photoUrl}/g, photoInfoDto.photoUrl);
	html = html.replace(/\${photoInfo.photoUrl}/g, photoInfoDto.photoUrl);
	html = html.replace(/\${photoInfo.photoTitle}/g, photoInfoDto.photoTitle);
	html = html.replace(/\${photoInfo.laudNum}/g, photoInfoDto.laudNum);
	html = html.replace(/\${photoInfo.uploadDateStr}/g, photoInfoDto.uploadDateStr);
	html = html.replace(/\${photoInfo.userName}/g, photoInfoDto.userName);
	html = html.replace(/\${thumbnail}/g, thumbnail);
	html = html.replace(/\${compress}/g, compress);
	$("#photo_content").append(html);
}

function morePhotos(){
	pageIndex++;
	$("#loadMore").show();
	$.ajax({
		url : "morePhotos.do",
		type : 'POST',
		data : "pageIndex="+pageIndex,
		dataType : "json",
		cache : false,
		success : function(msg) {
			if(msg.listSize > 0){
				for(var o in msg.photoInfoDtoList){  
					images[imageIndex++] = compress+msg.photoInfoDtoList[o].photoUrl;
					loadImage(msg.photoInfoDtoList[o]);
				};
				$("img.lazy").lazyload();
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

function previewImage(_this) {
	wx.previewImage({
		current : $(_this).attr("imgurl"),
		urls : images
	});
};




