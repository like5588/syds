package com.ty.photography.service;

import java.util.List;
import java.util.Map;

import com.ty.photography.model.UserBindInfo;
import com.ty.photography.model.UserInfo;

public interface IUserInfoService {
	/**
	 * 保存用户信息
	 * @param userInfo
	 * @param sourceId
	 * @param userSource 0 手机网用户，1微信用户，2易信用户
	 * @param competitionType 9 全国摄影大赛 ，5 电信摄影大赛
	 * @param dxArea 电信赛区
	 * @return
	 */
	public UserBindInfo saveUser(UserInfo userInfo,String sourceId,String userSource,String competitionType,String dxArea);
	
	/**
	 * 所有全国参与人数，显示在首页
	 * @param competitionType  参赛类型，如果为null则为全部
	 * @return
	 */
	public Integer allPerson(String competitionType);
	
	/**
	 * 所有全国参与作品数，显示在首页
	 * @param competitionType  参赛类型，如果为null则为全部
	 * @return
	 */
	public Integer allPhoto(String competitionType);
	/**
	 * 获取电信赛区参赛人数，作品数，负责人名称
	 * @return
	 */
	public List<Map<String,Object>> allTelecomInfo();
}
