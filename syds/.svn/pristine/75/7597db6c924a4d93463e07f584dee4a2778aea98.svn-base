package com.ty.photography.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.dao.UserBindInfoMapper;
import com.ty.photography.dao.UserInfoMapper;
import com.ty.photography.model.Dictionary;
import com.ty.photography.model.UserBindInfo;
import com.ty.photography.model.UserInfo;
import com.ty.photography.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserBindInfoMapper userBindInfoMapper;

	@Override
	public UserBindInfo saveUser(UserInfo userInfo, String sourceId,String userSource,String competitionType,String dxArea) {
		UserBindInfo userBindInfo =null;
		try{
			Date now = new Date();
			if(competitionType.equals("5")){
				userInfo.setCompetitionArea(dxArea);
			}
			userInfo.setCreateTime(now);
			userInfoMapper.saveUser(userInfo);
			userBindInfo = new UserBindInfo();
			userBindInfo.setId(CommonUtils.getUUID());
			userBindInfo.setUserId(userInfo.getId());
			userBindInfo.setSourceId(sourceId);
			userBindInfo.setUserSource(Integer.parseInt(userSource));
			userBindInfo.setCompetitionType(Integer.parseInt(competitionType));
			userBindInfo.setCreateTime(now);
			userBindInfo.setStatus(0);
			userBindInfoMapper.insert(userBindInfo);
		}catch(Exception e){
			throw e;
		}
		return userBindInfo;
	}
	
	
	@Override
	public Integer allPerson(String competitionType) {
		return userInfoMapper.allPerson(competitionType);
	}
	@Override
	public Integer allPhoto(String competitionType) {
		return userInfoMapper.allPhoto(competitionType);
	}
	
	public List<Map<String,Object>> allTelecomInfo(){
		return userInfoMapper.allTelecomInfo();
	}
 
}
