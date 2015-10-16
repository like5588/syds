package com.ty.photography.model;

import java.util.Date;

public class UserBindDto {
	
	private String id;	
	private String userId;	//用户id
	private String userName; //用户名称
	private String sourceId;	//来源id
	private int userSource;		//0 手机网用户，1微信用户，2易信用户
	private int competitionType;	//9 全国摄影大赛 ，5 电信摄影大赛
	private Date createTime;	
	private Date modifyTime;
	private int status;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public int getUserSource() {
		return userSource;
	}
	public void setUserSource(int userSource) {
		this.userSource = userSource;
	}
	public int getCompetitionType() {
		return competitionType;
	}
	public void setCompetitionType(int competitionType) {
		this.competitionType = competitionType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public UserBindDto copy(UserBindInfo userBindInfo){
		this.id = userBindInfo.getId();
		this.userId = userBindInfo.getUserId();
		this.sourceId = userBindInfo.getSourceId();
		this.userSource = userBindInfo.getUserSource();
		this.competitionType = userBindInfo.getCompetitionType();
		this.createTime = userBindInfo.getCreateTime();
		this.modifyTime = userBindInfo.getModifyTime();
		this.status = userBindInfo.getStatus();
		return this;
	}

}
