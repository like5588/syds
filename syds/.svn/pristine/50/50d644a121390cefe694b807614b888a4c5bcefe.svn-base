<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/wx_common.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/swiper.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/yx_all_photos.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.lazyload.min.js"></script>
<script type="text/javascript">
var listSize = ${listSize};
var scroll=true;
if(listSize <10){
	scroll=false;
}
</script>
<title>更多作品</title>
</head>
<body style="background-color: #efefef;">
	<div class="top">更多作品</div>
	<input type="hidden" id="openid" value="${openid}">
	<div style="margin:10px;" id="photo_content">
		<c:if test="${listSize eq 0}">
			<div style="text-align: center;">暂无作品</div>
		</c:if>
		<c:if test="${listSize ne 0}">
			<c:forEach items="${photoInfoDtoList}" var="photoInfoDto">
				<div class="panel panel-default image-panel">
				  <div class="panel-body image-body">
				  	<img class="lazy" src="${pageContext.request.contextPath}/images/placeholder.gif" imgurl="${compress}${photoInfoDto.photoUrl}"
				  	 data-original="${thumbnail}${photoInfoDto.photoUrl}" style="width: 100%;" onclick="image_show(this);">
				  </div>
				  <div  class="image-title">
				  	${photoInfoDto.photoTitle}
				  	<div class="laud-unchecked"></div>
				  	<div class="laud-num">${photoInfoDto.laudNum}</div>
				  </div>
				  <div class="botton">
				  	<div class="author">
				  		${photoInfoDto.userName}
				  	 	<div class="image-tm">${photoInfoDto.uploadDateStr}</div>
				  	 </div>
				  </div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<div id="loadMore" style="text-align: center;display:none;">
		<a style="color:gray; background: url('${pageContext.request.contextPath}/images/loading.gif');background-repeat:no-repeat;background-size: 14px;background-position:0px 1px;">
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正在加载...
	    </a>
	</div>
	<div id="bottom" style="text-align: center;display:none;">
		<a style="color:gray;">
	            	---&nbsp;&nbsp;已到最底部&nbsp;&nbsp;---
	    </a>
	</div>
	
	<div id="mask">
		<div id="swiper_div" class="swiper-container">
	        <div class="swiper-wrapper">
	            <div class="swiper-slide" style="z-index:99;" onclick="close(hideMask())">
	                <img data-src="" class="swiper-lazy">
	                <div class="swiper-lazy-preloader swiper-lazy-preloader-white"></div>
	            </div>
	        </div>
	    </div>
	</div>
	
<!--  	<div align="center" style="font-size: 9px;margin-bottom: 5px;postion:absolute;">注：在法律允许范围内本活动最终解释权归天翼电信终端有限公司所有。</div>
-->
</body>
<script type="text/javascript">
$("img.lazy").lazyload();
document.addEventListener('YixinJSBridgeReady', function onBridgeReady() {
    YixinJSBridge.call('hideOptionMenu');
});
var swiper = new Swiper('.swiper-container', {
    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    pagination: '.swiper-pagination',
    paginationClickable: true,
    // Disable preloading of all images
    preloadImages: false,
    // Enable lazy loading
    lazyLoading: true
});
</script>
</html>