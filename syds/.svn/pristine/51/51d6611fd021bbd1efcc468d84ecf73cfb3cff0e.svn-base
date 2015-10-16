package com.ty.photography.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.common.Command;
import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.DownImage;
import com.ty.photography.common.UrlUtil;
import com.ty.photography.common.WxUtil;
import com.ty.photography.dao.PhotoDownloadMapper;
import com.ty.photography.dao.PhotoInfoMapper;
import com.ty.photography.model.PhotoDownload;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.service.IPhotoDownloadService;

@Service
public class PhotoDownloadServiceImpl implements IPhotoDownloadService{
	
	@Autowired
	private PhotoDownloadMapper photoDownloadMapper;
	@Autowired
	private PhotoInfoMapper photoInfoMapper;
	
	public void uploadPhoto(String userId,String serverId,String photoTitle,int competitionType,String photoType,String photoSource){
		try{
			Date now = new Date();
			PhotoInfo photoInfo = new PhotoInfo();
			String photoId = CommonUtils.getUUID();
			photoInfo.setId(photoId);
			photoInfo.setUserId(userId);
			photoInfo.setPhotoTitle(photoTitle);	//照片名称
			photoInfo.setPhotoStatus(-1);	//照片未上传
			photoInfo.setUploadDate(now);	//照片上传时间
			photoInfo.setIsSelect("0");	//未入选
			photoInfo.setPhotoGroup(1);	//手机组
			photoInfo.setPhotoType(photoType);	//照片分类
			photoInfo.setPhotoSource(photoSource);	//照片来源
			photoInfo.setLaudNum(0);    	//点赞数
			photoInfo.setSimpleGroup(0);	//单图
			photoInfo.setCompetitionType(competitionType);   //参赛类型
			photoInfoMapper.savePhotoInfo(photoInfo);
			
			PhotoDownload photoDownload = new PhotoDownload();
			photoDownload.setId(CommonUtils.getUUID());
			photoDownload.setUserSource(photoSource=="WX"?1:0);	//来源
			photoDownload.setUploadTime(now);	//上传时间
			photoDownload.setStatus(0);	//未下载
			photoDownload.setPhotoInfoId(photoId);	//照片id
			photoDownload.setServerId(serverId);	//移动端照片id
			photoDownloadMapper.save(photoDownload);
		}catch(Exception e){
			throw e;
		}
	}
	
	public void downLoadPhotos() throws Exception{
		try{
			WxUtil wxUtils = WxUtil.getInstance();
			Command downImage=null;
			boolean idDown=false;
			String targetFileName="";
			List<PhotoDownload> needDownLoadList = photoDownloadMapper.needDownLoadPhotos();
			String nowStr = CommonUtils.dateToString(new Date())+"/";
			for (PhotoDownload photoDownload : needDownLoadList) {
				if(photoDownload.getUserSource() == 1){
					downImage = new DownImage(wxUtils,photoDownload.getServerId());
					idDown = downImage.action();
					targetFileName = photoDownload.getServerId();
				}else if(photoDownload.getUserSource() == 2){
					String file_path =CommonUtils.parseProperties("file_path");
					targetFileName= CommonUtils.getUUID();
					File compress = new File(file_path+"/compress/"+nowStr);
					if(!compress.exists()){
						compress.mkdir();
					}
					idDown = UrlUtil.download(photoDownload.getImageUrl(), file_path+"/compress/"+nowStr, targetFileName);
				}
				photoDownload.setDownloadTime(new Date());
				photoDownload.setStatus(idDown?1:-1);
				photoDownloadMapper.updatePhotoDownload(photoDownload);
				PhotoInfo photoInfo = new PhotoInfo();
				if(idDown){
					photoInfo.setPhotoUrl(nowStr+targetFileName+".jpg");
				}
				photoInfo.setPhotoStatus(idDown?0:-1);
				photoInfo.setId(photoDownload.getPhotoInfoId());
				photoInfoMapper.update(photoInfo);
			}
		}catch(Exception e){
			throw e;
		}
	
		
	}

}
