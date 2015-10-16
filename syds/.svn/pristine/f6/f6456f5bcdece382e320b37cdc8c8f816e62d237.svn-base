<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1 style="font-size:1.8em; color:#f00; margin:5px 0;">个人抽奖历史信息</h1>
		<table class="table">
			<thead>
				<tr>
					<th width="10%">序号</th>
					<th width="35%">抽奖时间</th>
					<th width="40%">中奖情况</th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${userLotteryList ne null}">
					<c:if test="${listSize>0}">
						<c:forEach var="lotteryHistory" items="${userLotteryList}" varStatus="xh">
							<tr>
								<td>${xh.count}</td>
								<td>${lotteryHistory.lotteryTimeStr}</td>
								<td><c:if test="${lotteryHistory.lotteryName ne null}">${lotteryHistory.lotteryName}</c:if>
									<c:if test="${lotteryHistory.lotteryName eq null}">未中奖</c:if>
								</td>
								<td><c:if test="${lotteryHistory.status eq 1}">
									<button type="button" onclick="save_button()" class="btn btn-primary" style="padding: 4px 10px;">请完善个人信息</button></td>
									</c:if>
							</tr>
						</c:forEach>
					</c:if>
				</c:if>
				<c:if test="${userLotteryList eq null}">
					<tr>
						<td colspan="4">暂无纪录</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	<div style="clear:both;"><%@ include file="/paging.jsp"%></div>