<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${lotteryModelList ne null}">
	<c:if test="${listSize>0}">
		<c:forEach var="lotteryModel" items="${lotteryModelList}">
			<li>
				<div class="titme">${lotteryModel.time}</div>
				<div class="uername">${lotteryModel.name}</div>
				<div class="yh">获得${lotteryModel.award}</div>
			</li>
		</c:forEach>
	</c:if>
</c:if>