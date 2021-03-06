package com.ty.photography.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.Dictionary;
import com.ty.photography.model.UserInfo;

@Repository
public interface UserInfoMapper {
	
	public void saveUser(UserInfo userInfo);

	public UserInfo findById(String id);
	
	public Integer allPerson(String competitionType);
	
	public Integer allPhoto(String competitionType);

	public List<Map<String,Object>> allTelecomInfo();
}
