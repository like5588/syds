package com.ty.photography.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.dao.UserBindInfoMapper;
import com.ty.photography.model.UserBindDto;
import com.ty.photography.model.UserBindInfo;
import com.ty.photography.service.IUserBindInfoService;

@Service
public class UserBindInfoServiceImpl implements IUserBindInfoService{
	@Autowired
	private UserBindInfoMapper userBindInfoMapper;

	@Override
	public UserBindInfo findUserBindInfo(String sourceId, String userSource,
			String competitionType) {
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("sourceId", sourceId);
		queryMap.put("userSource", userSource);
		queryMap.put("competitionType", competitionType);
		List<UserBindInfo> list = userBindInfoMapper.findUserBindInfo(queryMap);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public UserBindDto findUserBindDto(String sourceId, String userSource,String competitionType) {
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("sourceId", sourceId);
			queryMap.put("userSource", userSource);
			queryMap.put("competitionType", competitionType);
			List<UserBindDto> list = userBindInfoMapper.findUserBindDto(queryMap);
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
	}

}
