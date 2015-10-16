<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lrtk.js"></script>
<div class="mh_works">
<div class="mh_works1_list">
	<c:if test="${photoList ne null}">
		<c:if test="${listSize>0}">
			<ul>
				<c:forEach var="photoInfo" items="${photoList}" varStatus="xh">
					<li>
						<div class="listbox">
							<div class="listimg">
								<a><img src="${thumbnail}${photoInfo.photoUrl}" style="width:210px;cursor:pointer;" onclick="image_show(${xh.count-1});"/></a>
								<div class="summary">
									<div class="summarytxt">
										<p>
											${photoInfo.photoTitle}<br /> 
											时间：<fmt:formatDate value='${photoInfo.uploadDate}' pattern='yyyy-MM-dd' />
										</p>
									</div>
								</div>
							</div>

						</div>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</c:if>
	<c:if test="${photoList eq null || listSize==0}">
		<div style="text-align: center;">暂无记录</div>
	</c:if>
</div>
</div>

<div id="mask">
   <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper" onclick="hideMask();">
        	<c:if test="${photoList ne null}">
				<c:if test="${listSize>0}">
					<c:forEach var="photoInfo" items="${photoList}" varStatus="xh">
				    <div class="swiper-slide">
				    	<img src="${compress}${photoInfo.photoUrl}">
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
<div style="clear: both;">
<%@ include file="../../paging.jsp"%>
</div>
<script type="text/javascript">
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