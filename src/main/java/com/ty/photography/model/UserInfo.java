package com.ty.photography.model;

import java.util.Date;

public class UserInfo {
	
	private String id;
	private String acconut;	//账号
	private String userName;	//用户名称
	private String workUnit;	//工作单位
	private String idCard;		//身份证号
	private String mobile;		//电话
	private String competitionArea;		//赛区
	private String petname;		//昵称
	private String province;	//省份
	private String address;		//地址
	private String postcode;	//邮编
	private Date createTime;
	private Date modifyTime;
	private int status;		//0 正常 1失效
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAcconut() {
		return acconut;
	}
	public void setAcconut(String acconut) {
		this.acconut = acconut;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCompetitionArea() {
		return competitionArea;
	}
	public void setCompetitionArea(String competitionArea) {
		this.competitionArea = competitionArea;
	}
	public String getPetname() {
		return petname;
	}
	public void setPetname(String petname) {
		this.petname = petname;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	
}
