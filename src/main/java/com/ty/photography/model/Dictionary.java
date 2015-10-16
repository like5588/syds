package com.ty.photography.model;

import java.util.Date;
/**
 * 字典表
 * @author liyan
 *
 */
public class Dictionary {
	private String id;
	private String dataType;	//字典类别
	private String dateValue;	//字典值
	private String dataShowValue;	//字典值显示
	private int orderNumber;	//显示排序
	private Date createTime;	
	private Date modifyTime;
	private Integer status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDateValue() {
		return dateValue;
	}
	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}
	public String getDataShowValue() {
		return dataShowValue;
	}
	public void setDataShowValue(String dataShowValue) {
		this.dataShowValue = dataShowValue;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
