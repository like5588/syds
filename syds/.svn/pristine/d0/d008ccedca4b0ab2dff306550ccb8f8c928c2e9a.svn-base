package com.ty.photography.controller.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.Page;
import com.ty.photography.common.RedisUtil;
import com.ty.photography.model.Dictionary;
import com.ty.photography.model.UserLottery;
import com.ty.photography.service.IDictionaryService;
import com.ty.photography.service.ILotteryService;


@Controller
@RequestMapping(value="sys")
public class LotteryManageController {
	private Logger log = LoggerFactory.getLogger(LotteryManageController.class);
	@Autowired
	private ILotteryService lotteryServiceImpl;
	@Autowired
	private IDictionaryService dictionaryServiceImpl;
	
	@RequestMapping(value="lotteryInfo.do",produces="text/plain;charset=UTF-8;")  
	public String lotteryInfo(String pageIndex,ModelMap model){
		Page page = new Page();
		int pageNo = 1;
		if(pageIndex != null){
			try{
				pageNo = Integer.parseInt(pageIndex);
			}catch(Exception e){
				throw e;
			}
			page.setPageIndex(pageNo);
		}
		UserLottery userLottery = new UserLottery();
		userLottery.setLotteryResult_notZero(1);
		List<UserLottery> userLotteryList = lotteryServiceImpl.queryHistoryByPage(page,userLottery);
		model.addAttribute("userLotteryList", userLotteryList);
		model.addAttribute("listSize",userLotteryList.size());
		model.addAttribute("page",page);
		return "sys/lottery_info";
	}
	
	@RequestMapping(value="lotteryListInfo.do",produces="text/plain;charset=UTF-8;") 
	public String lotteryListInfo(String userName,String mobile,String address,String lotteryResult,String status,String fromTime,String toTime,String pageIndex,ModelMap model){
		Page page = new Page();
		int pageNo = 1;
		if(pageIndex != null){
			try{
				pageNo = Integer.parseInt(pageIndex);
			}catch(Exception e){
				throw e;
			}
			page.setPageIndex(pageNo);
		}
		UserLottery userLottery = new UserLottery();
		if(StringUtils.isNotBlank(userName)){
			userLottery.setUserName(userName);
		}
		if(StringUtils.isNotBlank(mobile)){
			userLottery.setMobile(mobile);
		}
		if(StringUtils.isNotBlank(address)){
			userLottery.setAddress(address);
		}
		if(StringUtils.isNotBlank(lotteryResult)){
			userLottery.setLotteryResult(Integer.parseInt(lotteryResult));
		}
		if(StringUtils.isNotBlank(status)){
			userLottery.setStatus(Integer.parseInt(status));
		}
		if(StringUtils.isNotBlank(fromTime)){
			userLottery.setFromTime(CommonUtils.stringToTime(fromTime));
		}
		if(StringUtils.isNotBlank(toTime)){
			userLottery.setToTime(CommonUtils.stringToTime(toTime));
		}
		userLottery.setLotteryResult_notZero(1);
		List<UserLottery> userLotteryList = lotteryServiceImpl.queryHistoryByPage(page,userLottery);
		model.addAttribute("userLotteryList", userLotteryList);
		model.addAttribute("listSize",userLotteryList.size());
		model.addAttribute("page",page);
		return "sys/lottery_list";
	}
	/**
	 * 获取抽奖参数
	 * @param model
	 * @return
	 */
	@RequestMapping(value="paramsSetting.do",produces="text/plain;charset=UTF-8;") 
	public String paramsSetting(ModelMap model){
		RedisUtil redis = new RedisUtil();
		String lottery_days = redis.get("lottery_days");	//抽奖持续天数
		String lottery_start = redis.get("lottery_start");	//抽奖开始时间
		String lottery_times = redis.get("lottery_times");	//每人每天抽奖次数
		Map<String,String> lottery_probability_map = redis.hgetAll("lottery_probability"); //概率
		Map<String,String> lottery_num_map = redis.hgetAll("lottery_num");
		model.addAttribute("lottery_days", lottery_days);
		model.addAttribute("lottery_start", lottery_start);
		model.addAttribute("lottery_times", lottery_times);
		model.addAttribute("lottery_probability_map", lottery_probability_map);
		model.addAttribute("lottery_num_map", lottery_num_map);
		redis.close();
		return "sys/paramsSetting";
	}
	/**
	 * 更新抽奖天数
	 * @param lottery_days
	 * @param lottery_start
	 * @return
	 */
	@RequestMapping(value="updateDays.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String updateDays(String lottery_days,String lottery_start){
		String result="{\"result\":\"0\"}";
		try{
			Dictionary dictionary = new Dictionary();     
			dictionary.setId("lottery_days");
			dictionary.setDateValue(lottery_days);
			dictionary.setCreateTime(CommonUtils.stringToDate(lottery_start));	//抽奖开始时间
			dictionaryServiceImpl.update(dictionary);
			RedisUtil redis = new RedisUtil();
			redis.set("lottery_days", lottery_days);
			redis.set("lottery_start", lottery_start);
			redis.close();
		}catch(Exception e){
			log.error("---updateDays has error---",e);
			result="{\"result\":\"1\"}";
		}
		
		return result;
	}
	/**
	 * 更新抽奖次数
	 * @param lottery_times
	 * @return
	 */
	@RequestMapping(value="updateTimes.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String updateTimes(String lottery_times){
		String result="{\"result\":\"0\"}";
		try{
			Dictionary dictionary = new Dictionary();     
			dictionary.setId("lottery_times");
			dictionary.setDateValue(lottery_times);
			dictionaryServiceImpl.update(dictionary);
			RedisUtil redis = new RedisUtil();
			redis.set("lottery_times", lottery_times);
			redis.close();
		}catch(Exception e){
			log.error("---updateDays has error---",e);
			result="{\"result\":\"1\"}";
		}
		
		return result;
	}
	/**
	 * 更新抽奖概率
	 * @param probability1
	 * @param probability2
	 * @param probability3
	 * @param probability4
	 * @return
	 */
	@RequestMapping(value="updateProbability.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String updateProbability(String probability1,String probability2,String probability3,String probability4){
		String result="{\"result\":\"0\"}";
		try{
			Dictionary dictionary = new Dictionary();     
			dictionary.setId("lottery_probability_1");
			dictionary.setDateValue(probability1);
			dictionaryServiceImpl.update(dictionary);
			dictionary = new Dictionary();     
			dictionary.setId("lottery_probability_2");
			dictionary.setDateValue(probability2);
			dictionaryServiceImpl.update(dictionary);
			dictionary = new Dictionary();     
			dictionary.setId("lottery_probability_3");
			dictionary.setDateValue(probability3);
			dictionaryServiceImpl.update(dictionary);
			dictionary = new Dictionary();     
			dictionary.setId("lottery_probability_4");
			dictionary.setDateValue(probability4);
			dictionaryServiceImpl.update(dictionary);
			RedisUtil redis = new RedisUtil();
			redis.hset("lottery_probability", "lottery_probability_1", probability1);
			redis.hset("lottery_probability", "lottery_probability_2", probability2);
			redis.hset("lottery_probability", "lottery_probability_3", probability3);
			redis.hset("lottery_probability", "lottery_probability_4", probability4);
			redis.close();
		}catch(Exception e){
			log.error("---updateDays has error---",e);
			result="{\"result\":\"1\"}";
		}
		
		return result;
	}
	/**
	 * 更新中奖个数
	 * @param lotteryNum1
	 * @param lotteryNum2
	 * @param lotteryNum3
	 * @param lotteryNum4
	 * @return
	 */
	@RequestMapping(value="updatelotteryNum.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String updatelotteryNum(String lotteryNum1,String lotteryNum2,String lotteryNum3,String lotteryNum4){
		String result="{\"result\":\"0\"}";
		try{
			Dictionary dictionary = new Dictionary();     
			dictionary.setId("lottery_num_1");
			dictionary.setDateValue(lotteryNum1);
			dictionaryServiceImpl.update(dictionary);
			dictionary = new Dictionary();     
			dictionary.setId("lottery_num_2");
			dictionary.setDateValue(lotteryNum2);
			dictionaryServiceImpl.update(dictionary);
			dictionary = new Dictionary();     
			dictionary.setId("lottery_num_3");
			dictionary.setDateValue(lotteryNum3);
			dictionaryServiceImpl.update(dictionary);
			dictionary = new Dictionary();     
			dictionary.setId("lottery_num_4");
			dictionary.setDateValue(lotteryNum4);
			dictionaryServiceImpl.update(dictionary);
			
			RedisUtil redis = new RedisUtil();
			redis.hset("lottery_num", "lottery_num_1", lotteryNum1);
			redis.hset("lottery_num", "lottery_num_2", lotteryNum2);
			redis.hset("lottery_num", "lottery_num_3", lotteryNum3);
			redis.hset("lottery_num", "lottery_num_4", lotteryNum4);
			redis.close();
		}catch(Exception e){
			log.error("---updateDays has error---",e);
			result="{\"result\":\"1\"}";
		}
		
		return result;
	}
}
