package com.ty.photography.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ty.photography.common.SearchPageUtil;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.model.PhotoInfoDto;

@Repository
public interface PhotoInfoMapper {
	/**
	 * 分页查询我的图片
	 * @param searchPageUtil
	 * @return
	 */
	public List<PhotoInfo> findMyPhotosByPage(SearchPageUtil searchPageUtil);
	/**
	 * 保存照片信息
	 * @param photoInfo
	 */
	public void savePhotoInfo(PhotoInfo photoInfo);
	/**
	 * 查询更多图片
	 * @param searchPageUtil
	 * @return
	 */
	public List<PhotoInfoDto> findAllPhotosByPage(SearchPageUtil searchPageUtil);
	/**
	 * 更新
	 * @param photoInfo
	 */
	public void update(PhotoInfo photoInfo);
	
	/**
	 * 查找最后一次编辑但未上传图片的记录
	 * @param userId
	 * @param competitionType
	 * @return
	 */
	public PhotoInfo findLastEditPhotoInfo(Map<String,Object> queryMap);
	/**
	 * 更新图片状态
	 * @param photoInfo
	 */
	public void updatePhotoStatus(PhotoInfo photoInfo);
	/**
	 * 审核
	 * @param paramsMap
	 */
	public int checkPhotos(Map<String,Object> paramsMap);
	/**
	 * 评审
	 * @param paramsMap
	 */
	public int updateIsSelect(Map<String,Object> paramsMap);
	/**
	 * 更新点赞数
	 * @param paramsMap
	 */
	public void updateLaud(Map<String,Object> paramsMap);
	/**
	 * 分配图片
	 * @param paramMap
	 */
	public void photoDistribution(Map<String,Object> paramMap);
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	public PhotoInfo findById(String id);
	/**
	 * 佳作
	 * @param id
	 */
	public void updateExcellent(String id);
	/**
	 * 查询
	 * @param photoInfoDto
	 * @return
	 */
	public List<PhotoInfoDto> findAllPhotos(PhotoInfoDto photoInfoDto);
}
