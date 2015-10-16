package com.ty.photography.service;

public interface IPhotoDownloadService {
	/**
	 * 上传照片
	 * @param userId 用户id
	 * @param serverId  微信端照片id 
	 * @param photoTitle 照片名称
	 * @param competitionType 参赛类型
	 * @param photoType  1纪实类(工作，生产) ，2艺术类（风光、人物，生态）
	 * @param photoSource 照片来源 PC社区，YX易信，WX微信
	 */
	public void uploadPhoto(String userId,String serverId,String photoTitle,int competitionType,String photoType,String photoSource);
	/**
	 * 下载图片并更新状态
	 */
	public void downLoadPhotos() throws Exception;

}
