package com.ty.photography.model;

import java.util.Date;

import com.ty.photography.common.CommonUtils;

public class UserLottery {
	
	private String id;
	private String userId;	//用户名称
	private Date lotteryTime;	//抽奖时间
	private Integer lotteryResult;	//抽奖结果  0 未中奖 1一等奖 2二等奖 3三等奖 4四等奖
	private String lotteryName;	//奖项名称
	private String userName;	//用户名
	private String mobile;		//手机号
	private String postcode;	//邮编
	private String address;		//地址
	private Integer status;		//0未中奖 1中奖但未填写用户信息 2中奖
	
	private Integer lotteryResult_notZero;
	private Date fromTime;
	private Date toTime;
	private String lotteryTimeStr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLotteryTime() {
		return lotteryTime;
	}
	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	public Integer getLotteryResult() {
		return lotteryResult;
	}
	public void setLotteryResult(Integer lotteryResult) {
		this.lotteryResult = lotteryResult;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLotteryName() {
		return lotteryName;
	}
	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}
	public String getLotteryTimeStr() {
		return CommonUtils.timeToString(this.lotteryTime);
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	public Integer getLotteryResult_notZero() {
		return lotteryResult_notZero;
	}
	public void setLotteryResult_notZero(Integer lotteryResult_notZero) {
		this.lotteryResult_notZero = lotteryResult_notZero;
	}

}
