package com.ty.photography.model;

import java.util.Date;

/**
 * 照片点赞日志信息
 * @author chunxiang
 *
 */
public class LaudLog {
	private String id;
	private String photoId;	//照片id
	private String userId;	//点赞用户id
	private Date creatTime;	//创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

}