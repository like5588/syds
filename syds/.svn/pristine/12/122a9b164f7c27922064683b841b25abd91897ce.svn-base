<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ty.photography.monitor.TelecomInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>天翼4G 分享美好</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
<link href="css/bigfoucs.css" rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//读取当前用户的所有通过审核的照片信息   通过审核 	photo_status =’1‘
		$.ajax({
			url : "${pageContext.request.contextPath}/contryPhotos.do",
			type : "post",
			cache : false,
			contentType : "application/json",
			dataType : "json",
			success : function(msg) {
				//不要删除
				$.each(msg.Camera_index, function (n, key) { 
					$("#camera").find("p").eq(n).html(key.photoTitle+"\<br\/\>作者："+key.userName);
					$("#camera").find("img").eq(n).attr("src","http:\/\/yxsyds.hicdma.com\/upload\/syds2015\/thumbnail\/"+key.photoUrl);
					//$("#camera").find("a").eq(n).attr("href","files\/compress\/"+key.photoUrl);
					$("#KinSlideshow").find("img").eq(n).attr("src","http:\/\/yxsyds.hicdma.com\/upload\/syds2015\/excellent\/"+key.photoUrl);
					$("#KinSlideshow").find("img").eq(n).attr("alt",key.userName+" 《"+key.photoTitle+"》");
				}); 
				$.each(msg.cellphone_index, function (n, key) {
					$("#cellphone").find("p").eq(n).html(key.photoTitle+"\<br\/\>作者："+key.userName);
					$("#cellphone").find("img").eq(n).attr("src","http:\/\/yxsyds.hicdma.com\/upload\/syds2015\/thumbnail\/"+key.photoUrl)
					//$("#cellphone").find("a").eq(n).attr("href","files\/compress\/"+key.photoUrl);
					$("#KinSlideshow1").find("img").eq(n).attr("src","http:\/\/yxsyds.hicdma.com\/upload\/syds2015\/excellent\/"+key.photoUrl);
					$("#KinSlideshow1").find("img").eq(n).attr("alt",key.userName+" 《"+key.photoTitle+"》");
				}); 
				
				$("#KinSlideshow").KinSlideshow();
				$("#KinSlideshow1").KinSlideshow();
			}
		});
	});
	//显示佳作轮播
	$(function() {
		
	})
</script>
</head>
<body>
	<%@include file="mh_top.jsp"%>
	<div class="nav1">
		<a href="#top" class="back_to_top">顶部</a>
  		<a href="#jiazuo" class="back_to_top">佳作欣赏</a> 
  		<a href="#pinpai" class="back_to_top">品牌专区</a> 
    	<a href="#lottery_d" class="back_to_top" style="border-bottom: 0px;">活动抽奖</a> 
	</div>
	<div class="yg_33province_bg">
		<div class="mh_picup">
			<a href="${pageContext.request.contextPath}/mh/upload.do"><img
				src="${pageContext.request.contextPath}/mh_images/index_up.png" /></a>
		</div>
		<div class="mh_number">
			<ul id="photos">
				<li class="number_name">参展作品：</li>
			</ul>
			<ul id="users">
				<li class="number_name">参与人数：</li>
			</ul>
		</div>
		<!--佳作内容开始-->
		<!--作品列表开始-->
		<div class="mh_works">
			<div class="mh_works_title" id="jiazuo">
				<ul>

					<li class="mh_works_title1"></li>
					<li class="mh_works_titletext" style="width: 785px;">佳作欣赏(相机组)</li>
					<li><a href="mh_exhibition.jsp?ListType=1"class="mh_works_more" style="width: 97px;"></a></li>
				</ul>
			</div>
			<div class="mh_works1">
				<div id="KinSlideshow" style="visibility: hidden;">
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a> 
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a> 
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a>
				</div>
			</div>

			<div class="mh_works1_list" id="camera">
				<ul>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p> 
									</div>
								</div>
							</div>
						</div>
					</li>

					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>

						</div>

					</li>

					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>

						</div>

					</li>

					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p> </p>
									</div>
								</div>
							</div>

						</div>

					</li>


				</ul>
				<ul>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>

						</div>

					</li>

					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>

						</div>

					</li>

					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img /></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>

			<div class="mh_works_title">
				<ul>
					<li class="mh_works_title2" style="width: 98px;"></li>
					<li class="mh_works_titletext" style="width: 767px;">佳作欣赏(手机组)</li>
					<li><a href="mh_exhibition.jsp?ListType=2"class="mh_works_more" style="width: 97px;"></a></li>
				</ul>
			</div>
			<div class="mh_works1">
				<div id="KinSlideshow1" style="visibility: hidden;">
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a> 
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a> 
					<a><img  width="1000" height="414" /></a>
					<a><img  width="1000" height="414" /></a>
				</div>
			</div>
			<div class="mh_works1_list" id="cellphone">
				<ul>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
				<ul>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="listbox">
							<div class="listimg">
								<a ><img/></a>
								<div class="summary">
									<div class="summarytxt">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="mh_works_title" id="pinpai">
				<ul>
					<li class="mh_works_title3" style="width: 90px;"></li>
					<li class="mh_works_titletext" style="width: 775px;">品牌专区</li>
				</ul>
			</div>
			<div class="mh_logo">
				<div class="mh_logo1"></div>
				<div class="mh_works1_list" style="width: 750px; float: left;">
					<ul>
						<li><div class="listbox">
								<a><img src="mh_images/m_01.jpg" /></a>
							</div></li>
						<li><div class="listbox">
								<a><img src="mh_images/m_02.jpg" /></a>
							</div></li>
						<li><div class="listbox">
								<a><img src="mh_images/m_03.jpg" /></a>
							</div></li>
					</ul>
					<ul>
						<li><div class="listbox">
								<a><img src="mh_images/m_04.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/m_05.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/m_06.jpg" /></a>
							</div></li>
					</ul>
				</div>
			</div>
			<div class="mh_logo">
				<div class="mh_logo2"></div>
				<div class="mh_works1_list" style="width: 750px; float: left;">
					<ul>
						<li><div class="listbox">
								<a><img src="mh_images/x_01.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/x_02.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/x_03.jpg" /></a>
							</div></li>

					</ul>

					<ul>
						<li><div class="listbox">
								<a><img src="mh_images/x_04.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/x_05.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/x_06.jpg" /></a>
							</div></li>
					</ul>


				</div>

			</div>
			<div class="mh_logo">
				<div class="mh_logo3"></div>
				<div class="mh_works1_list" style="width: 750px; float: left;">
					<ul>
						<li><div class="listbox">
								<a><img src="mh_images/q_01.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/q_02.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/q_03.jpg" /></a>
							</div></li>

					</ul>

					<ul>
						<li><div class="listbox">
								<a><img src="mh_images/q_04.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/q_05.jpg" /></a>
							</div></li>

						<li><div class="listbox">
								<a><img src="mh_images/q_06.jpg" /></a>
							</div></li>
					</ul>
				</div>
			</div>

		</div>
		<!--作品列表结束-->
	</div>

	<!--佳作内容结束
	<%@include file="/mh_foot.jsp"%>
	-->
	<jsp:include page="wheelSurf.do"/>
</body>
<script type="text/javascript">
	//参展作品数赋值方法
	var userNum =
<%=TelecomInfo.allUserNum.intValue()%>
	;
	var photoNum =
<%=TelecomInfo.allPhotosNum.intValue()%>
	;

	var photoNumString = photoNum.toString();
	var start = 0;
	var number = "";
	if (photoNumString.length < 5) {
		for (var i = 1; i <= 5 - photoNumString.length; i++) {
			var x = document.createElement("LI");
			var t = document.createTextNode("0");
			x.appendChild(t);
			document.getElementById("photos").appendChild(x);
		}
	}
	for (var end = 1; end <= photoNumString.length; end++) {
		number = photoNumString.substr(start, 1);
		var x = document.createElement("LI");
		var t = document.createTextNode(number);
		x.appendChild(t);
		document.getElementById("photos").appendChild(x);
		start = end;
	}
	//参与人数赋值方法
	var userNumString = userNum.toString();
	start = 0;
	number = "";
	if (userNumString.length < 5) {
		for (var i = 1; i <= 5 - userNumString.length; i++) {
			var x = document.createElement("LI");
			var t = document.createTextNode("0");
			x.appendChild(t);
			document.getElementById("users").appendChild(x);
		}
	}
	for (var end = 1; end <= userNumString.length; end++) {
		number = userNumString.substr(start, 1);
		var x = document.createElement("LI");
		var t = document.createTextNode(number);
		x.appendChild(t);
		document.getElementById("users").appendChild(x);
		start = end;
	}
</script>
</html>
