package com.ty.photography.service;

import java.util.List;
import java.util.Map;

import com.ty.photography.common.Page;
import com.ty.photography.model.LotteryModel;
import com.ty.photography.model.UserLottery;

public interface ILotteryService {
	/**
	 *  -3 当前积分不够 ，-2超过最大次数 ， -1 不再抽奖时间 ，0未中奖，1一等奖，2二等奖，3三等奖，4四等奖
	 * @param userId
	 * @return
	 */
	public Map<String,Integer> lottery(String userId);
	/**
	 * 获取最近15条中奖信息
	 * @return
	 */
	public List<LotteryModel> getRecentlyInfo();
	/**
	 * 获取总中奖数
	 * @return
	 */
	public Integer getTotalWin();
	/**
	 * 保存用户信息
	 * @param userLottery
	 */
	public void saveUserLotteryInfo(UserLottery userLottery);
	/**
	 * 查询用户抽奖历史
	 * @param page
	 * @param userLottery
	 * @return
	 */
	public List<UserLottery> queryHistoryByPage(Page page,UserLottery userLottery);
}
