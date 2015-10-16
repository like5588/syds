 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${photoListDto ne null}">
			<c:if test="${listSize>0}">
				<c:forEach var="photoListDto" items="${photoListDto}" varStatus="xh">
	     			<div class="row" style="margin-left: 40px;">
		    			<div class="col-lg-2dot4">
		    				<div class="panel panel-default image-panel">
							  <div class="panel-body image-body">
							  	<img class="lazy" src="${thumbnail}${photoListDto.photoUrl}"
							  style="width: 100%;" onclick="image_show(${xh.count-1});">
							  </div>
							  <div class="image-title">
							  	${photoListDto.photoTitle}
								<div class="laud-unchecked" style="margin-right: 8px;"></div>
							  	<div class="laud-num">${photoListDto.laudNum}</div>
							  </div>
							  <div class="botton">
							  	<div class="author">
							  		${photoListDto.userName}
							  	 	<div class="image-tm">${photoListDto.uploadDateStr}</div>
							  	 </div>
							  </div>
							</div>		
						</div>
						<div class="col-sm-9" style="margin-top: 20px;">
							<form>
							  <input type="hidden" name="photoId" value="${photoListDto.photoId}">
							  <div class="form-group" >
							    <label for="comment">点评：</label>
							    <textarea class="form-control" style="width: 80%;display: block;" rows="5" name="comment" placeholder="点评信息不超过200字">${photoListDto.comment}</textarea>
							  </div>
							  <div class="form-group">
							    <div style="margin-right: 20%;text-align: right;">
							    	<c:if test="${photoListDto.status eq 1}"><span style="color:green;">已发布</span></c:if>
							    		  <c:if test="${photoListDto.status eq 0}"><span style="color:red;">未发布</span></c:if>
							    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    	<button type="button" class="btn btn-primary" onclick="publish(this)" stat="${photoListDto.status}">
							    		<c:if test="${photoListDto.status eq 1}">取消发布</c:if>
							    		<c:if test="${photoListDto.status eq 0}">发布</c:if>
							    	</button>
							      	&nbsp;&nbsp;&nbsp;&nbsp;
							      <button type="button" class="btn btn-primary" onclick="updateInfo(this)">保存</button>
							    </div>
							  </div>
							</form>
						</div>
					</div>
				</c:forEach>
 			</c:if>
 </c:if>
<c:if test="${listSize==0}">
	<div align="center">暂无作品</div>
</c:if>

	<div id="mask">
    <!-- Swiper -->
	    <div class="swiper-container">
	        <div class="swiper-wrapper" onclick="hideMask();">
	        	<c:if test="${photoListDto ne null}">
					<c:if test="${listSize>0}">
						<c:forEach var="photoListDto" items="${photoListDto}" varStatus="xh">
					    <div class="swiper-slide">
					    	<img src="${compress}${photoListDto.photoUrl}">
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

<div style="clear:both;"><%@ include file="/paging.jsp"%></div>　
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