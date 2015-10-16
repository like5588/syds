<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.swiper-container-horizontal>.swiper-pagination {bottom: 40px;}
.laud-checked{height: 20px;width:18px; float: right;margin-right: 7px;background: url(../syds/images/laud.png) no-repeat scroll -18px 0;background-size:36px;}
.laud-unchecked{height: 20px;width:18px; float: right;margin-right: 7px;background: url(../syds/images/laud.png) no-repeat scroll;background-size:36px;}
.laud-num{height: 20px;float: right;padding-right: 10px;color:gray;font-family: Century Gothic;font-weight:600;}
</style>
<div class="mh_works">
<div class="mh_works1_list">
		<c:if test="${PhotoListDto ne null}">
			<c:if test="${listSize>0}">
				<c:forEach var="photoInfoDto" items="${PhotoListDto}" varStatus="xh">
	    				<ul>
						<li style="background:none;width: auto;height: auto;">
							<div class="listbox" style="width: auto;height: auto;padding:0;">
								<div class="listimg" style="width: auto;height: auto;">
									<div class="panel panel-default" style="margin-bottom:0;">
										 <div style="width: 220px;height: 245px;padding: 5px 5px 0 5px;">
										 	<div class="img_div" style="width: 210px;height: 210px;position: relative;overflow:hidden;">
												<img src="${thumbnail}${photoInfoDto.photoUrl}" style="width: 210px;height:auto;cursor:pointer;" onclick="image_show(${xh.count}-1);"/>
												<div class="summary" style="top:210px;">
													<div class="summarytxt" style="margin:5px 10px;">
														<p>
															作品名称：${photoInfoDto.photoTitle}<br/> 
															作者：${photoInfoDto.userName}<br/> 
															时间：${photoInfoDto.uploadDateStr}
														</p>
													</div>
												</div>
											</div>
											<div style="padding-top: 7px;">
												<span style="font-family: '宋体'">点赞:</span><span style="font-family: '宋体'">${photoInfoDto.laudNum}</span>
												<c:if test="${photoInfoDto.laud eq true}">
													<div class="laud-checked"></div>
												</c:if>
												<c:if test="${photoInfoDto.laud eq false}">
													<div class="laud-unchecked" style="cursor:pointer;" attr="${photoInfoDto.id}" onclick="laud(this);"></div>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</li> 
						</ul>
	    		</c:forEach>
			</c:if>
    	</c:if>
		<c:if test="${PhotoListDto eq null || listSize==0}">
			<div style="text-align: center;">暂无记录</div>
		</c:if> 
		</div>
</div> 
<div id="mask">

<script type="text/javascript">
$(document).ready(function()
		{$('.listimg_big').hover(function()
				{$(".summary_big",this).stop().animate({top:'400px'},
													{queue:false,duration:680});},
													function(){$(".summary_big",this).stop().animate({top:'510px'},{queue:false,duration:680});});
		
		$('.img_div').hover(function(){
			$(".summary",this).stop().animate({top:'140px'},{queue:false,duration:180});},
			function(){$(".summary",this).stop().animate({top:'210px'},{queue:false,duration:180});});
		
		});
</script>
   <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper" onclick="hideMask();">
        	<c:if test="${PhotoListDto ne null}">
				<c:if test="${listSize>0}">
					<c:forEach var="photoInfoDto" items="${PhotoListDto}" varStatus="xh">
				    <div class="swiper-slide" style="width: 1301px;">
				    	<div class="listimg_big">
				    	<img src="${compress}${photoInfoDto.photoUrl}">
				    			<div class="summary_big">
									<div class="summary_bigtxt" style="margin:5px 10px;">
										<p>
											作品名称：${photoInfoDto.photoTitle}<br /> 
											作者：${photoInfoDto.userName}<br /> 
											时间：${photoInfoDto.uploadDateStr}<br />
											作品介绍：${photoInfoDto.photoTitle} 
											<c:if test="${param.ListType=='1' || param.ListType == '2'}">
								    			<br />作品评价：${photoInfoDto.comment} 
								    		</c:if>
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
var swiper;
function image_show(_num){
	var scrt = $(document).scrollTop() 
	$("#mask").css("margin-top",scrt);
	$("#mask").show();
	/* 去掉页面滚动条 */
	 $(document.body).css({
	    "overflow-x":"hidden",
	    "overflow-y":"hidden"
	  });
	swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 30
    });
	 if(_num == 0){
		 swiper.slideTo(1, 0);
		 swiper.slidePrev(false,0);
	 }else{
		 swiper.slideTo(_num, 0);
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
<div style="clear:both;"><%@ include file="/paging_mini.jsp"%></div>
