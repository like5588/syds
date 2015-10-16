package com.ty.photography.model;

import java.util.Date;

/**
 * 照片审批信息
 * @author chunxiang
 *
 */
public class PhotoVerify {
	private String id;
	private String userId;	//作者id	
	private String photoId;	//照片id
	private String juryId;	//评委id
	private String status;			//审批状态
	private Date createTime;		//审批时间
	private String comment;	//点评
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
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getJuryId() {
		return juryId;
	}
	public void setJuryId(String juryId) {
		this.juryId = juryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
 