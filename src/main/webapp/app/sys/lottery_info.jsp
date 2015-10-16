	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
var params = "";
$(document).ready(function(){
	$("#query").click(function(){
		params = $("#query_form").serialize();
		$.ajax({
			url : "lotteryListInfo.do",  
			type : 'POST',
			data : params,
			dataType:"html",
			cache : false,
			success : function(msg){
				$("#listInfo").html(msg);
			}
		});
		
	});
})

	function gotoPage(_page){
		var data = params+"&pageIndex="+_page;
		$.ajax({
			url : "lotteryListInfo.do",  
			type : 'POST',
			data : data,
			dataType:"html",
			cache : false,
			success : function(msg){
				$("#listInfo").html(msg);
			}
		});
	}
</script>
	<div id="main" style="margin: 20px;">
		<div style="border-bottom: 1px solid #eee; margin-bottom: 20px;margin-top: 20px;">
			<form class="form-inline" id="query_form" style="margin-bottom: 15px;">
				<div class="form-group" style="padding-right: 15px;">
					<label for="userName">用户：</label> 
					<input class="form-control" id="userName" name="userName">
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="mobile">手机号：</label> 
					<input class="form-control" id="mobile" name="mobile">
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="address">地址：</label> 
					<input class="form-control" id="address" name="address">
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="lotteryResult">奖项：</label> 
					<select class="form-control" id="lotteryResult" name="lotteryResult">
						<option value="">请选择</option>
						<option value="0">未中奖</option>
						<option value="1">一等奖</option>
						<option value="2">二等奖</option>
						<option value="3">三等奖</option>
						<option value="4">四等奖</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="status">用户信息状态：</label> 
					<select class="form-control"  id="status" name="status">
						<option value="">请选择</option>
						<option value="2">已完善</option>
						<option value="1">未完善</option>
					</select>
				</div>
				<div class="form-group" style="padding-right:5px;padding-top: 5px;">
				    <label >抽奖时间 从：</label>
				    <input type="text" class="Wdate1 form-control" style="clear:both;width: 200px;" name="fromTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
				</div>
				<div class="form-group" style="padding-right:150px;padding-top: 5px;">
				    <label >到：</label>
				    <input type="text" class="Wdate1 form-control" style="clear:both;width: 200px;" name="toTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
				</div>
				<button type="button" id="query" class="btn btn-primary" style="margin: 0 10px;">查询</button>
			</form>
		</div>
		<div class="tab-content">
    		<div role="tabpanel" class="tab-pane active" id="listInfo">
    			<%@ include file="lottery_list.jsp"%>
    				
    		</div>
		</div>
	</div>