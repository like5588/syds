package com.ty.photography.model;

import java.util.Date;

/**
 * 照片下载信息
 * @author chunxiang
 *
 */
public class PhotoDownload {
	private String id;
	private Integer userSource;	//用户来源 1 微信 2易信	
	private String openId;	//易信和微信openID
	private Date uploadTime;	//上传时间
	private String imageUrl;	//照片地址	
	private Date downloadTime;		//下载时间	
	private Integer status;			//下载状态 0 未下载 1已下载 -1下载失败
	private String photoInfoId;	//照片id
	private String serverId;	//移动端照片id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUserSource() {
		return userSource;
	}
	public void setUserSource(Integer userSource) {
		this.userSource = userSource;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getDownloadTime() {
		return downloadTime;
	}
	public void setDownloadTime(Date downloadTime) {
		this.downloadTime = downloadTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPhotoInfoId() {
		return photoInfoId;
	}
	public void setPhotoInfoId(String photoInfoId) {
		this.photoInfoId = photoInfoId;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
}
