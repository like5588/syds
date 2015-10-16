package com.ty.photography.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ty.photography.common.SearchPageUtil;
import com.ty.photography.model.UserLottery;

@Repository
public interface UserLotteryMapper {
	/**
	 * 保存抽奖信息
	 * @param userLottery
	 */
	public void insert(UserLottery userLottery);
	/**
	 * 条件查询
	 * @param searchPageUtil
	 * @return
	 */
	public List<UserLottery> queryByCondition(SearchPageUtil searchPageUtil);
	/**
	 * 查询最近的中奖信息
	 * @return
	 */
	public List<UserLottery> queryRecentlyWinInfo();
	/**
	 * 获取总中奖数
	 * @return
	 */
	public Integer getTotalWin();
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public UserLottery findByStatus(Map<String,Object> map);
	/**
	 * 保存用户信息
	 * @param userLottery
	 */
	public void saveUserLotteryInfo(UserLottery userLottery);
}
