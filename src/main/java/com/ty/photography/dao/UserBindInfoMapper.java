package com.ty.photography.dao;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.UserBindDto;
import com.ty.photography.model.UserBindInfo;

@Repository
public interface UserBindInfoMapper {
	/**
	 * 新增
	 * @param userBindInfo
	 * @return
	 */
	public void insert(UserBindInfo userBindInfo);
	/**
	 * 查询用户绑定信息
	 * @param queryMap
	 * @return
	 */
	public List<UserBindInfo> findUserBindInfo(Map<String,Object> queryMap);
	
	/**
	 * 查询用户绑定信息
	 * @param queryMap
	 * @return
	 */
	public List<UserBindDto> findUserBindDto(Map<String,Object> queryMap);
	
}
