package com.ty.photography.model;

import java.util.Date;

import com.ty.photography.common.CommonUtils;

/**
 * 榜单信息
 * @author chunxiang
 *
 */
public class PhotoList {
	private String id;
	private String userId;	
	private String photoId;	//照片id	 
	private String listType;	//榜单类型
	private String comment;	//点评
	private String creater;	//创建人
	private Date createTime;		//创建时间	
	private String modifyUser;			//修改人
	private Date modifyTime;	//修改时间
	private Integer status;			//状态	
	private String userName;	//作者名称user_info表引用过来
	private String photoType;	//从photo_info表引用过来
	private String isIndex;		//判断是否首页佳作请求  index=1 首页佳作请求
	private Date uploadDate;	
	private String photoTitle;
	private String uploadDateStr;
	private Integer laudNum;		//点赞数
	private String photoUrl;	//照片地址	
	private Integer simpleGroup;	//单图组图
	private Integer photoGroup;	//照片分组 0 相机组，1手机组，2未知
	
	private String judgeGroupId;
	private String judgeId;
	
	private boolean laud=false;	//是否已点赞
	
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
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
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
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public String getIsIndex() {
		return isIndex;
	}
	public void setIsIndex(String isIndex) {
		this.isIndex = isIndex;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	public String getUploadDateStr() {
		return CommonUtils.dateToString(uploadDate);
	}
	public Integer getLaudNum() {
		return laudNum;
	}
	public void setLaudNum(Integer laudNum) {
		this.laudNum = laudNum;
	}
	public boolean isLaud() {
		return laud;
	}
	public void setLaud(boolean laud) {
		this.laud = laud;
	}
	public Integer getSimpleGroup() {
		return simpleGroup;
	}
	public void setSimpleGroup(Integer simpleGroup) {
		this.simpleGroup = simpleGroup;
	}
	public Integer getPhotoGroup() {
		return photoGroup;
	}
	public void setPhotoGroup(Integer photoGroup) {
		this.photoGroup = photoGroup;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getJudgeGroupId() {
		return judgeGroupId;
	}
	public void setJudgeGroupId(String judgeGroupId) {
		this.judgeGroupId = judgeGroupId;
	}
	public String getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(String judgeId) {
		this.judgeId = judgeId;
	}
	
}
