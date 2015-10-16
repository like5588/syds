package com.ty.photography.model;

import java.util.Date;

/**
 * 用户积分日志信息
 * @author chunxiang
 *
 */
public class UserCoinLog {
	private String id;
	private String userId;	//用户id
	private int earnCoin;	//获得积分
	private String userOperate;	//用户操作	
	private Date createTime;		//创建时间
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
	public int getEarnCoin() {
		return earnCoin;
	}
	public void setEarnCoin(int earnCoin) {
		this.earnCoin = earnCoin;
	}
	public String getUserOperate() {
		return userOperate;
	}
	public void setUserOperate(String userOperate) {
		this.userOperate = userOperate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
 
} 
 