package com.ty.photography.service;

import java.util.List;

import redis.clients.jedis.Jedis;

import com.ty.photography.common.Page;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.model.PhotoInfoDto;

public interface IPhotoInfoService {
	/**
	 * 分页查询我的照片
	 * @param page
	 * @param photoInfo
	 * @return
	 */
	public List<PhotoInfo> findMyPhotosByPage(Page page,PhotoInfo photoInfo);

	/**
	 * 查询更多作品
	 * @param page
	 * @param photoInfoDto
	 * @param orderBy
	 * @return
	 */
	public List<PhotoInfoDto> findAllPhotosByPage(Page page,PhotoInfoDto photoInfoDto,String orderBy);
	/**
	 * 保存照片信息
	 * @param photoInfo
	 */
	public void savePhotoInfo(PhotoInfo photoInfo);
	/**
	 * 查询最后一次编译但并未上传图面的信息
	 * @param sourceId
	 * @param userSource
	 * @param competitionType
	 * @return
	 */
	public PhotoInfo findLastEditPhotoInfo(String sourceId,String userSource,String competitionType);
	/**
	 * 更新图片信息
	 * @param sourceId
	 * @param photoUrl
	 * @param userSource 0 手机网用户，1微信用户，2易信用户
	 * @param competitionType 9 全国摄影大赛 ，5 电信摄影大赛
	 * @return
	 */
	public boolean updatePhotoStatus(String sourceId,String photoUrl,String userSource,String competitionType);

	/**
	 * 审核
	 * @param photoIds
	 * @param stat
	 * @return
	 */
	public int checkPhotos(String stat, String... photoIds);
	/**
	 * 评审操作
	 * @param photoIds
	 * @param newStats,oldStats
	 * @return
	 */
	public int updateIsSelect(String newStats, String oldStats, String... photoIds); 
	/**
	 * 更新点赞数
	 * @param JEDIS
	 */
	public void updateLaud(Jedis JEDIS);
	/**
	 * 查询
	 * @param photoInfoDto
	 * @return
	 */
	public List<PhotoInfoDto> findAllPhotos(PhotoInfoDto photoInfoDto);

}
