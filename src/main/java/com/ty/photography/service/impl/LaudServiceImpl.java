package com.ty.photography.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.RedisUtil;
import com.ty.photography.dao.LaudLogMapper;
import com.ty.photography.dao.PhotoInfoMapper;
import com.ty.photography.dao.UserCoinMapper;
import com.ty.photography.model.LaudLog;
import com.ty.photography.model.UserCoin;
import com.ty.photography.model.UserCoinLog;
import com.ty.photography.service.ILaudService;

@Service
public class LaudServiceImpl implements ILaudService{
	@Autowired
	private LaudLogMapper laudLogMapper;
	@Autowired
	private UserCoinMapper userCoinMapper;
	@Autowired
	private PhotoInfoMapper photoInfoMapper;
	
	@Override
	public String laud(String imgId, String userId) {
		String result = "0";
		try{
			String dateStr = CommonUtils.dateToString(new Date());
			RedisUtil redisUtil = new RedisUtil();
			long imgNum = redisUtil.scard(userId+"_"+dateStr);
			boolean exist = redisUtil.sismember(userId+"_"+dateStr, imgId);		//是否以点赞
			if(exist){
				result = "1";	//已点赞
			}else{
				redisUtil.sadd(userId+"_"+dateStr, imgId);	//保存点赞
//					redisUtil.incr("laud_"+imgId);
				//保存日志
				LaudLog laudLog = new LaudLog();
				laudLog.setId(CommonUtils.getUUID());
				laudLog.setPhotoId(imgId);
				laudLog.setUserId(userId);
				laudLog.setCreatTime(new Date());
				laudLogMapper.insert(laudLog);
				//每天最多给20点赞积分
				if(imgNum < 20){
					//用户积分
					Date date = new Date();
					UserCoin userCoin = new UserCoin();
					userCoin.setId(CommonUtils.getUUID());
					userCoin.setUserId(userId);
					userCoin.setTotalCoin(1);
					userCoin.setCurrentCoin(1);
					userCoin.setHighestCoin(1);
					userCoin.setCreateTime(date);
					userCoin.setEarnCoin(1);
					userCoin.setModifyTime(date);
					userCoinMapper.saveOrUpdate(userCoin);
					//用户积分日志
					UserCoinLog userCoinLog = new UserCoinLog();
					userCoinLog.setId(CommonUtils.getUUID());
					userCoinLog.setUserId(userId);
					userCoinLog.setEarnCoin(1);
					userCoinLog.setUserOperate("Laud");
					userCoinLog.setCreateTime(date);
					userCoinMapper.saveLog(userCoinLog);
				}
				//更新点赞
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("laudNum", 1);
				paramsMap.put("id", imgId);
				photoInfoMapper.updateLaud(paramsMap);
			}
			redisUtil.close();
		}catch(Exception e){
			throw e;
		}
		
		return result;
	}

}
