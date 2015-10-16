<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
<form id="photos_form">
		<c:if test="${photoInfoDtoList ne null}">
			<c:if test="${listSize>0}">
				<c:forEach var="photoInfoDto" items="${photoInfoDtoList}" varStatus="xh" begin="0" end="4">
					<c:if test="${xh.count == 1}">
							<%="<div class=\"row\">"%>
					</c:if>
						<div class="col-lg-2dot4">
							<div class="panel panel-default image-panel">
							  <div class="panel-body image-body">
							  	<img class="lazy" src="${thumbnail}${photoInfoDto.photoUrl}"
							  style="width: 100%;" onclick="image_show(${xh.count}-1);">
							  </div>
							  <div class="image-title">
							  	<div class="checkbox" style="margin-top:0;margin-bottom: 0; float: left;">
								  <label>
								    <input type="checkbox" name="photoIds" value="${photoInfoDto.id}" id="${photoInfoDto.id}">
								   ${photoInfoDto.photoTitle}
								  </label>
								</div>
							  	<div class="laud-num">${photoInfoDto.laudNum}</div>
							  </div>
							  <div class="botton">
							  	<div class="author">
							  		${photoInfoDto.userName}
							  	 	<div class="image-tm">${photoInfoDto.uploadDateStr}</div>
							  	 </div>
							  </div>
							</div>					
						</div>
					<c:if test="${xh.count == 5 || listSize == xh.count}">
							<%="</div>"%>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${listSize>5}">
				<c:forEach var="photoInfoDto" items="${photoInfoDtoList}" varStatus="xh" begin="5" end="9">
					<c:if test="${xh.count == 1}">
							<%="<div class=\"row\">"%>
					</c:if>
						<div class="col-lg-2dot4">
							<div class="panel panel-default image-panel">
							  <div class="panel-body image-body">
							  	<img class="lazy" src="${thumbnail}${photoInfoDto.photoUrl}"
							  style="width: 100%;" onclick="image_show(${xh.count}+4);">
							  </div>
							  <div class="image-title">
							  	<div class="checkbox" style="margin-top:0;margin-bottom: 0; float: left;">
								  <label>
								    <input type="checkbox" name="photoIds" value="${photoInfoDto.id}" id="${photoInfoDto.id}">
								   ${photoInfoDto.photoTitle}
								  </label>
								</div>
							  	<div class="laud-num">${photoInfoDto.laudNum}</div>
							  </div>
							  <div class="botton">
							  	<div class="author">
							  		${photoInfoDto.userName}
							  	 	<div class="image-tm">${photoInfoDto.uploadDateStr}</div>
							  	 </div>
							  </div>
							</div>					
						</div>
					<c:if test="${xh.count == 5 || listSize - xh.count == 5}">
							<%="</div>"%>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>
		<c:if test="${listSize==0}">
			<div align="center">暂无作品</div>
		</c:if>
</form>
</div>

	<div id="mask">
    <!-- Swiper -->
	    <div class="swiper-container">
	        <div class="swiper-wrapper" onclick="hideMask();">
	        	<c:if test="${photoInfoDtoList ne null}">
					<c:if test="${listSize>0}">
						<c:forEach var="photoInfoDto" items="${photoInfoDtoList}" varStatus="xh">
					    <div class="swiper-slide">
					    	<div class="listimg_big">
						    	<img src="${compress}${photoInfoDto.photoUrl}">
						    	<div class="summary_big">
										<div class="summary_bigtxt" style="margin:5px 10px;">
											<p>
												作品名称：${photoInfoDto.photoTitle}<br /> 
												作者：${photoInfoDto.userName}<br /> 
												时间：${photoInfoDto.uploadDateStr}<br />
												作品介绍：${photoInfoDto.photoTitle} 
											</p>
										</div>
								</div>
							</div>
					    </div>
						</c:forEach>
					</c:if>
				</c:if>
	        </div>
	        <!-- Add Pagination -->
	        <div class="swiper-pagination swiper-pagination-white"></div>
	        <!-- Add Arrows -->
	        <div class="swiper-button-next swiper-button-white"></div>
	        <div class="swiper-button-prev swiper-button-white"></div>
	    </div>
	</div>
<script type="text/javascript">
$('.listimg_big').hover(function()
		{$(".summary_big",this).stop().animate({top:'400px'},
											{queue:false,duration:680});},
											function(){$(".summary_big",this).stop().animate({top:'510px'},{queue:false,duration:680});});
var mySwiper;
function image_show(_num){
	var scrt = $(document).scrollTop() 
	$("#mask").css("margin-top",scrt);
	$("#mask").show();
	/* 去掉页面滚动条 */
	 $(document.body).css({
	    "overflow-x":"hidden",
	    "overflow-y":"hidden"
	  });
	mySwiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        paginationClickable: true,
	        spaceBetween: 30
	    });
	 if(_num == 0){
		 mySwiper.slideTo(1, 0);
		 mySwiper.slidePrev(false,0);
	 }else{
		 mySwiper.slideTo(_num, 0);
	 }
}

function hideMask(){
	$("#mask").hide();
	 $(document.body).css({
		    "overflow-x":"auto",
		    "overflow-y":"auto"
		  });
}

</script>
<%@ include file="/paging.jsp"%>