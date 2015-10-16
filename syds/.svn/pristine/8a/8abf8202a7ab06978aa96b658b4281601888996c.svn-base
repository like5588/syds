<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/page/dx_pingxuan.js"></script>
		<div style="border-bottom: 1px solid #eee; margin-bottom: 20px;margin-top: 20px;">
			<form class="form-inline" id="query_form" style="margin-bottom: 15px;">
				<input type="hidden" name="isSelect" id="isSelect" value="2">
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoStatus">分组：</label> 
					<select class="form-control" name="photoGroup" id="photoGroup">
					  <option value="">请选择</option>
					  <option value="1">手机组</option>
					  <option value="0">相机组</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoStatus">单图/组图：</label> 
					<select class="form-control" name="simpleGroup" id="simpleGroup">
					  <option value="">请选择</option>
					  <option value="0">单图</option>
					  <option value="1">组图</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoStatus">类型：</label> 
					<select class="form-control" name="photoType" id="photoType">
					   <option value="">请选择</option>
					  <option  value="1" >纪实</option>
					  <option  value="2" >风景</option>
					</select>
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoStatus">作者：</label> 
					<input class="form-control" id="userName" name="userName">
				</div>
				<div class="form-group" style="padding-right: 15px;">
					<label for="photoStatus">作品名称：</label> 
					<input class="form-control" id="photoTitle" name="photoTitle">
				</div>
				<button type="button" id="query" class="btn btn-primary" >查询</button>
			</form>
			<div align="right">
				<div class="form-group" style="margin: 10px;">
					<button type="button" class="btn btn-primary" onclick="excellent()"
						style="margin-right: 10px;margin-top: -7px;float: right;">提交佳作</button>
					<button type="button" class="btn btn-primary" onclick="batchDownload();"
						style="margin-right: 10px;margin-top: -7px;float: right;">下载入围作品</button>
					<div class="form-group" style="margin: 10px;">
						<label class="checkbox-inline">
						  <input type="checkbox" id="checkAll" value="all"> 全选
						</label>
						<label class="checkbox-inline" style="margin-right: 40px;">
						  <input type="checkbox" id="invertCheck" value="no"> 反选
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-content">
    		<div role="tabpanel" class="tab-pane active" id="home">
    				<%@include file="photos_list.jsp"%>
    		</div>
		</div>
