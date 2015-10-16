package com.ty.photography.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ty.photography.common.SearchPageUtil;
import com.ty.photography.model.PhotoList;

@Repository
public interface PhotoListMapper {
	/**
	 * 查询首页更多佳作图片信息
	 * @param searchPageUtil
	 * @return
	 */
	public List<PhotoList> findIndexPhotosByPage(SearchPageUtil searchPageUtil);
	
	/**
	 * 查询首页佳作轮播图片信息
	 * @param 
	 * @return
	 */
	public List<PhotoList> findIndexPhotos(String listType);
	/**
	 * 保存
	 * @param photoList
	 */
	public void save(PhotoList photoList);
	/**
	 * 更新
	 * @param map
	 * @return
	 */
	public Integer update(PhotoList photoList);
}
