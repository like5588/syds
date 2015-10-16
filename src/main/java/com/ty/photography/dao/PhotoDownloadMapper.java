package com.ty.photography.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.PhotoDownload;

@Repository
public interface PhotoDownloadMapper {

	/**
	 * 保存图片下载信息
	 * @param photoDownload
	 */
	public void save(PhotoDownload photoDownload);
	/**
	 * 待下载图片
	 * 查询规则 优先查询待下载图片，然后查询下载失败的图片需要重新下载的图片，时间按照升序。
	 * @return
	 */
	public List<PhotoDownload> needDownLoadPhotos();
	/**
	 * 更新图片下载信息
	 * @param photoDownload
	 */
	public void updatePhotoDownload(PhotoDownload photoDownload);
}
