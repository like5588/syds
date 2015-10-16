package com.ty.photography.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.UserCoin;
import com.ty.photography.model.UserCoinLog;

@Repository
public interface UserCoinMapper {
	/**
	 * 保存用户积分
	 * @param userCoin
	 */
	public void saveOrUpdate(UserCoin userCoin);
	/**
	 * 保存日志
	 * @param userCoinLog
	 */
	public void saveLog(UserCoinLog userCoinLog);
	/**
	 * 更新积分
	 * @param queryMap
	 */
	public void updateCoins(Map<String,Object> queryMap);
	/**
	 * 查看用户积分信息
	 * @param userId
	 * @return
	 */
	public UserCoin findByUserId(String userId);
	
}
