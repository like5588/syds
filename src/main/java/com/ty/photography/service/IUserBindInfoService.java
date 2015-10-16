package com.ty.photography.service;

import com.ty.photography.model.UserBindDto;
import com.ty.photography.model.UserBindInfo;

public interface IUserBindInfoService {
	/**
	 * 查询用户绑定信息
	 * @param sourceId	
	 * @param userSource 0 手机网用户，1微信用户，2易信用户
	 * @param competitionType 9 全国摄影大赛 ，5 电信摄影大赛
	 * @return
	 */
	public UserBindInfo findUserBindInfo(String sourceId,String userSource,String competitionType);

	/**
	 * 查询用户绑定信息
	 * @param sourceId	
	 * @param userSource 0 手机网用户，1微信用户，2易信用户
	 * @param competitionType 9 全国摄影大赛 ，5 电信摄影大赛
	 * @return
	 */
	public UserBindDto findUserBindDto(String sourceId,String userSource,String competitionType);
}
