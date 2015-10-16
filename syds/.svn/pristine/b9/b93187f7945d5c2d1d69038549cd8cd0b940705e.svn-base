<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th width="8%">序号</th>
					<th width="10%">用户名</th>
					<th width="10%">手机号</th>
					<th width="15%">邮编</th>
					<th>地址</th>
					<th width="10%">抽奖时间</th>
					<th width="10%">奖项</th>
					<th width="10%">信息状态</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${userLotteryList ne null}">
					<c:if test="${listSize>0}">
						<c:forEach var="userLottery" items="${userLotteryList}" varStatus="xh">
							<tr>
								<td>${xh.count}</td>
								<td>${userLottery.userName}</td>
								<td>${userLottery.mobile}</td>
								<td>${userLottery.postcode}</td>
								<td>${userLottery.address}</td>
								<td>${userLottery.lotteryTimeStr}</td>
								<td>${userLottery.lotteryName}</td>
								<td><c:if test="${userLottery.status eq 1}">未完善</c:if>  
									<c:if test="${userLottery.status eq 2}">已完善</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</c:if>
				<c:if test="${userLotteryList eq null}">
					<tr>
						<th colspan="7" style="text-align: center;">暂无记录</th>
					</tr>
				</c:if>
			</tbody>
	</table>
	<div style="clear:both;"><%@ include file="/paging.jsp"%></div>　
</div>