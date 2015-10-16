	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
var r = /^\+?[0-9][0-9]*$/; 
/**
 * 更新抽奖天数
 */
function updateDays(){
	var params= $("#updateDays_form").serialize();
	var lottery_days = $("#lottery_days").val();
    if(!r.test(lottery_days)){
    	alert("请输入数字");
    	return;
    }
	$.ajax({
		url : "updateDays.do",  
		type : 'POST',
		data : params,
		dataType:"json",
		cache : false,
		success : function(msg){
			if(msg.result=="0"){
				alert("保存成功");
			}else if(msg.result=="1"){
				alert("保存失败");
			}
		}
	});
}
/**
 * 更新抽奖次数
 */
function updateTimes(){
	var params= $("#times_form").serialize();
	var lottery_times = $("#lottery_times").val();
    if(!r.test(lottery_times)){
    	alert("请输入数字");
    	return;
    }
	$.ajax({
		url : "updateTimes.do",  
		type : 'POST',
		data : params,
		dataType:"json",
		cache : false,
		success : function(msg){
			if(msg.result=="0"){
				alert("保存成功");
			}else if(msg.result=="1"){
				alert("保存失败");
			}
		}
	});
}
/**
 * 更新抽奖概率
 */
function updateProbability(){
	var params= $("#probability_form").serialize();
	var probability1 = $("#probability1").val();
	var probability2 = $("#probability2").val();
	var probability3 = $("#probability3").val();
	var probability4 = $("#probability4").val();
	if(isNaN(probability1) || isNaN(probability2) || isNaN(probability3) || isNaN(probability4)){
    	alert("请输入数字或小数");
    	return;
    }
	$.ajax({
		url : "updateProbability.do",  
		type : 'POST',
		data : params,
		dataType:"json",
		cache : false,
		success : function(msg){
			if(msg.result=="0"){
				alert("保存成功");
			}else if(msg.result=="1"){
				alert("保存失败");
			}
		}
	});
}
/**
 * 更新奖品个数
 */
function updatelotteryNum(){
	var params= $("#lotteryNum_form").serialize();
	var lotteryNum1 = $("#lotteryNum1").val();
	var lotteryNum2 = $("#lotteryNum2").val();
	var lotteryNum3 = $("#lotteryNum3").val();
	var lotteryNum4 = $("#lotteryNum4").val();
	if(!r.test(lotteryNum1) || !r.test(lotteryNum2) || !r.test(lotteryNum3) || !r.test(lotteryNum4)){
    	alert("请输入数字");
    	return;
    }
	$.ajax({
		url : "updatelotteryNum.do",  
		type : 'POST',
		data : params,
		dataType:"json",
		cache : false,
		success : function(msg){
			if(msg.result=="0"){
				alert("保存成功");
			}else if(msg.result=="1"){
				alert("保存失败");
			}
		}
	});
}
</script>
		<div class="panel panel-default">
	  <div class="panel-body">
	   		<form class="form-inline" style="margin-right: 20px;" id="updateDays_form">
			  <div class="form-group">
			    <label for="lottery_days">抽奖天数：</label>
			    <input type="text" class="form-control" id="lottery_days" name="lottery_days" value="${lottery_days}">
			  </div>
			  <div class="form-group">
			    <label for="lottery_start">抽奖开始时间：</label>
			    <input type="text" class="Wdate1 form-control" style="clear:both;width: 200px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
			    id="lottery_start" name="lottery_start" value="${lottery_start}" readonly="readonly">
			  </div>
			  <button type="reset" class="btn btn-default ">重置</button>
			  <button type="button" class="btn btn-primary " onclick="updateDays()">确定</button>
			</form>
	  </div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
	   		<form class="form-inline" style="margin-right: 20px;" id="times_form">
			  <div class="form-group">
			    <label for="lottery_times">每人每天抽奖次数：</label>
			    <input type="text" class="form-control" id="lottery_times" name="lottery_times" value="${lottery_times}">
			  </div>
			  <button type="reset" class="btn btn-default ">重置</button>
			  <button type="button" class="btn btn-primary" onclick="updateTimes()">确定</button>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
	   		<form class="form-horizontal" id="probability_form">
  			  <div class="form-group">
			    <label for="probability1" class="col-sm-2 control-label">一等奖概率：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="probability1" name="probability1" value="${lottery_probability_map.lottery_probability_1}">%
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="probability2" class="col-sm-2 control-label">二等奖概率：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="probability2" name="probability2" value="${lottery_probability_map.lottery_probability_2}">%
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="probability3" class="col-sm-2 control-label">三等奖概率：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="probability3" name="probability3" value="${lottery_probability_map.lottery_probability_3}">%
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="probability4" class="col-sm-2 control-label">四等奖概率：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="probability4" name="probability4" value="${lottery_probability_map.lottery_probability_4}">%
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="reset" class="btn btn-default ">重置</button>
			      <button type="button" class="btn btn-primary" onclick="updateProbability()">确定</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
		<div class="panel panel-default">
		<div class="panel-body">
	   		<form class="form-horizontal" id="lotteryNum_form">
  			  <div class="form-group">
			    <label for="lotteryNum1" class="col-sm-2 control-label">一等奖个数：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="lotteryNum1" name="lotteryNum1" value="${lottery_num_map.lottery_num_1}">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="lotteryNum2" class="col-sm-2 control-label">二等奖个数：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="lotteryNum2" name="lotteryNum2" value="${lottery_num_map.lottery_num_2}">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="lotteryNum3" class="col-sm-2 control-label">三等奖个数：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="lotteryNum3" name="lotteryNum3" value="${lottery_num_map.lottery_num_3}">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="lotteryNum4" class="col-sm-2 control-label">四等奖个数：</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="lotteryNum4" name="lotteryNum4" value="${lottery_num_map.lottery_num_4}">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="reset" class="btn btn-default ">重置</button>
			      <button type="button" class="btn btn-primary" onclick="updatelotteryNum()">确定</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
