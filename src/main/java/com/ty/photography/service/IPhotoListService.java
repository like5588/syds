package com.ty.photography.service;

import java.util.List;

import com.ty.photography.common.Page;
import com.ty.photography.model.PhotoList;

public interface IPhotoListService {

	/**
	 * 查询首页更多佳作信息
	 * @param page
	 * @param photoInfoDto
	 * @param orderBy
	 * @return
	 */
	public List<PhotoList> findIndexPhotosByPage(Page page,PhotoList photoList);
	/**
	 * 查询首页轮播作品信息
	 * @param page
	 * @param photoInfoDto
	 * @param orderBy
	 * @return
	 */
	public List<PhotoList> findIndexPhotos(String listType);
	/**
	 * 保存佳作
	 * @param photoIds
	 * @param judgeId
	 */
	public void save(String[] photoIds,String judgeId);
	/**
	 * 佳作更新
	 * @param status
	 * @param comment
	 * @param photoId
	 * @param judgeId
	 * @return
	 */
	public boolean updateInfo(String status,String comment,String photoId,String judgeId) throws Exception;

}
