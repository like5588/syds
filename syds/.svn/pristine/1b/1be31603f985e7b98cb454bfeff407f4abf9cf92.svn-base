package com.ty.photography.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.Page;
import com.ty.photography.common.SearchPageUtil;
import com.ty.photography.dao.DictionaryMapper;
import com.ty.photography.dao.UserCoinMapper;
import com.ty.photography.dao.UserLotteryMapper;
import com.ty.photography.model.Dictionary;
import com.ty.photography.model.LotteryModel;
import com.ty.photography.model.UserCoin;
import com.ty.photography.model.UserLottery;
import com.ty.photography.monitor.LotteryInfo;
import com.ty.photography.service.IDictionaryService;
import com.ty.photography.service.ILotteryService;

@Service
public class LotteryServiceImpl implements ILotteryService{

	@Autowired
	private IDictionaryService dictionaryService;
	@Autowired
	private UserLotteryMapper userLotteryMapper;
	@Autowired
	private UserCoinMapper userCoinMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public Map<String,Integer> lottery(String userId) {
		Map<String,Integer> result = new HashMap<String,Integer>();
		int lotteryResult = 0;
		try{
			UserCoin userCoin = userCoinMapper.findByUserId(userId);
			if(userCoin != null && userCoin.getCurrentCoin()>=10){
				LotteryInfo lotteryInfo = LotteryInfo.getInstance(dictionaryService);
				lotteryResult = lotteryInfo.lottery(userId);
				if(lotteryResult != -2){
					//扣除积分
					Map<String,Object> queryMap = new HashMap<String,Object>();
					queryMap.put("userId", userId);
					queryMap.put("coin", 10);
					queryMap.put("modifyTime", new Date());
					userCoinMapper.updateCoins(queryMap);
					//保存用户抽奖信息
					UserLottery userLottery = new UserLottery();
					userLottery.setId(CommonUtils.getUUID());
					userLottery.setUserId(userId);
					userLottery.setLotteryTime(new Date());
					userLottery.setLotteryResult(lotteryResult);
					userLottery.setStatus(lotteryResult == 0 ? 0 : 1);
					result.put("status", 1);
					if("1,2,3,4".contains(lotteryResult+"")){
						List<Dictionary> dicList = dictionaryService.findByType("lottery_award");
						for (Dictionary dictionary : dicList) {
							if(dictionary.getDateValue().equals(lotteryResult+"")){
								userLottery.setLotteryName(dictionary.getDataShowValue());
								break;
							}
						}
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("userId", userId);
						map.put("status", 2);
						UserLottery user = userLotteryMapper.findByStatus(map);
						if(user != null){
							userLottery.setUserName(user.getUserName());
							userLottery.setMobile(user.getMobile());
							userLottery.setAddress(user.getAddress());
							userLottery.setPostcode(user.getPostcode());
							userLottery.setStatus(2);
							result.put("status", 2);
						}
					}
					userLotteryMapper.insert(userLottery);
				}
			}else{
				lotteryResult = -3;	//当前积分不够
			}
		}catch(Exception e){
			throw e;
		}
		result.put("lotteryResult", lotteryResult);
		return result;
	}
	@Override
	public List<LotteryModel> getRecentlyInfo(){
		List<LotteryModel> lotteryModelList = new ArrayList<LotteryModel>();
		List<UserLottery> list = userLotteryMapper.queryRecentlyWinInfo();
		for (UserLottery userLottery : list) {
			LotteryModel lotteryModel = new LotteryModel();
			if(userLottery.getUserName() != null && !userLottery.getUserName().equals("")){
				lotteryModel.setName(userLottery.getUserName().substring(0,1)+"***");
			}else{
				lotteryModel.setName("***");
			}
			lotteryModel.setTime(convertTime(userLottery.getLotteryTime()));
			lotteryModel.setAward(userLottery.getLotteryName());
			lotteryModelList.add(lotteryModel);
		}
		return lotteryModelList;
	}

	private String convertTime(Date date){
		String result="";
		Date now = new Date();
		long between = (now.getTime()-date.getTime())/1000;
		if(between<60){
			result = between+"秒前";
		}else if(between>=60 && between<3600){
			result = between/60+"分钟前";
		}else if(between>=3600 && between<3600*24){
			result = between/3600+"小时前";
		}else if(between>=3600*24){
			result = between/(3600*24)+"天前";
		}
		return result;
	}
	@Override
	public Integer getTotalWin() {
		return userLotteryMapper.getTotalWin();
	}
	
	@Override
	public void saveUserLotteryInfo(UserLottery userLottery) {
		userLotteryMapper.saveUserLotteryInfo(userLottery);
	}
	@Override
	public List<UserLottery> queryHistoryByPage(Page page ,UserLottery userLottery) {
		SearchPageUtil sp = new SearchPageUtil();
		sp.setObject(userLottery);
		sp.setPage(page);
		
		return userLotteryMapper.queryByCondition(sp);
	}
}
