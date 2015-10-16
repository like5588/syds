package com.ty.photography.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.FileUtil;
import com.ty.photography.common.Page;
import com.ty.photography.common.SearchPageUtil;
import com.ty.photography.dao.PhotoInfoMapper;
import com.ty.photography.dao.PhotoListMapper;  
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.model.PhotoList;
import com.ty.photography.service.IPhotoListService; 

@Service
public class PhotoListServiceImpl implements IPhotoListService{
	
	@Autowired
	private PhotoListMapper photoListMapper;
	@Autowired
	private PhotoInfoMapper photoInfoMapper;
	
	@Override
	public List<PhotoList> findIndexPhotosByPage(Page page,PhotoList photoList) {
		SearchPageUtil sp = new SearchPageUtil();
		sp.setObject(photoList);

		sp.setPage(page);

		return photoListMapper.findIndexPhotosByPage(sp);
	}
	@Override
	public List<PhotoList> findIndexPhotos(String listType) {
		return photoListMapper.findIndexPhotos(listType);
	}
	
	public void save(String[] photoIds,String judgeId){
		Date now = new Date();
		for (String photoId : photoIds) {
			try{
				PhotoInfo photoInfo = photoInfoMapper.findById(photoId);
				PhotoList photoList = new PhotoList();
				photoList.setId(CommonUtils.getUUID());
				photoList.setUserId(photoInfo.getUserId());
				photoList.setPhotoId(photoId);
				photoList.setListType(photoInfo.getPhotoGroup() == 0 ? "1":"2");
				photoList.setCreater(judgeId);
				photoList.setCreateTime(now);
				photoList.setStatus(0);
				photoListMapper.save(photoList);
				
				photoInfoMapper.updateExcellent(photoId);
				
			}catch(Exception e){
				if(!e.getMessage().contains("Duplicate entry")){
					throw e;
				}
			}
			
		}
		
	}
	@Override
	public boolean updateInfo(String status,String comment, String photoId,String judgeId) throws Exception {
		boolean result = false;
		try {
			PhotoList photoList = new PhotoList();
			if(StringUtils.isNoneBlank(status)){
				photoList.setStatus(Integer.parseInt(status));
			}
			photoList.setPhotoId(photoId);
			photoList.setComment(comment);
			photoList.setModifyTime(new Date());
			photoList.setModifyUser(judgeId);
			int num = photoListMapper.update(photoList);
			if(StringUtils.isNoneBlank(status) && status.equals("1") && num == 1){
				PhotoInfo photoInfo = photoInfoMapper.findById(photoId);
				String path = CommonUtils.parseProperties("file_path");
				File file = new File(path+"/excellent/"+photoInfo.getPhotoUrl());
				if(!file.exists()){
					String dir = photoInfo.getPhotoUrl().substring(0,photoInfo.getPhotoUrl().lastIndexOf("/"));
					File dateDir = new File(path+"/excellent/"+dir);
					if(!dateDir.exists()){
						dateDir.mkdirs();
					}
					FileUtil.zoomImage(path+"/compress/"+photoInfo.getPhotoUrl(),path+"/excellent/"+photoInfo.getPhotoUrl(),1000,414);
					FileUtil.cutCenterImage(path+"/excellent/"+photoInfo.getPhotoUrl(),path+"/excellent/"+photoInfo.getPhotoUrl(),1000, 414);
				}
			}
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
