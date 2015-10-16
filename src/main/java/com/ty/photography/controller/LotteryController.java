package com.ty.photography.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import com.ty.photography.model.LotteryModel;
import com.ty.photography.model.UserBindDto;
import com.ty.photography.model.UserLottery;
import com.ty.photography.service.ILotteryService;

@Controller
public class LotteryController {
	private Logger log = LoggerFactory.getLogger(LotteryController.class);
	@Autowired
	private ILotteryService lotteryServiceImpl;
	
	@RequestMapping(value="/mh/lottery.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String lottery(HttpServletRequest request){
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		try{
			Random random = new Random();
			int angle = 0;	//旋转角度
			int circle = 360;	//
			int duration = 5000;	//持续时间
			
			int first = 157;
			int second = 337;
			int third = 76;
			int fourth = 247;
			
			int[] none = {22,112,202,292};
			int[] circles = {4,5,6};	//旋转4-6圈
			
			UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
			Map<String,Integer> map = lotteryServiceImpl.lottery(userBindDto.getUserId());
			int result = map.get("lotteryResult");
			resultMap.put("status", map.get("status"));
			int info = 0;
			switch(result){
				case 1:
					angle=first;
					info = 1;
					break;
				case 2:
					angle=second;
					info = 2;
					break;
				case 3:
					angle=third;
					info = 3;
					break;
				case 4:
					angle=fourth;
					info = 4;
					break;
				case 0:
					angle=none[random.nextInt(none.length)];
					break;
				default:
					angle=none[random.nextInt(none.length)];
			}
			int num = random.nextInt(circles.length);
			circle *= circles[num];
			angle += circle; 
			if(num==1){
				duration += 800;
			}else if(num==2){
				duration += 1600;
			}
			if(result == -3){
				resultMap.put("result", 3);	//用户积分不足
			}else if(result == -2){
				resultMap.put("result", 2); //已到达抽奖次数上限
			}else if(result == -1){
				resultMap.put("result", 0); //不在抽奖时间,则为未中奖
			}else{
				resultMap.put("result", 0);
			}
			resultMap.put("angle", angle);
			resultMap.put("duration", duration);
			resultMap.put("info",info);
		}catch(Exception e){
			log.error("----lottery has error ----",e);
			resultMap.put("result", -1);
		}
		return CommonUtils.toJsonStr(resultMap);
	}

	@RequestMapping(value="/recentlyInfo.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String recentlyInfo(){
		List<LotteryModel> lotteryModelList = lotteryServiceImpl.getRecentlyInfo();
		return CommonUtils.toJsonStr(lotteryModelList);
	}
	
	@RequestMapping(value="/wheelSurf.do",produces="text/plain;charset=UTF-8;")  
	public String wheelSurf(ModelMap model){
		List<LotteryModel> lotteryModelList = lotteryServiceImpl.getRecentlyInfo();
		Integer winNum = lotteryServiceImpl.getTotalWin();
		model.addAttribute("lotteryModelList", lotteryModelList);
		model.addAttribute("listSize", lotteryModelList.size());
		model.addAttribute("winNum", winNum);
		return "../wheelSurf";
	}
	
	@RequestMapping(value="mh/saveLotteryInfo.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String saveLotteryInfo(HttpServletRequest request,String userName,String mobile,String postcode,String address){
		String result = "0";
		try{
			if(StringUtils.isNoneBlank(userName,mobile,postcode,address)){
				UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
				UserLottery userLottery = new UserLottery();
				userLottery.setUserId(userBindDto.getUserId());
				userLottery.setUserName(userName);
				userLottery.setMobile(mobile);
				userLottery.setPostcode(postcode);
				userLottery.setAddress(address);
				userLottery.setStatus(2);
				lotteryServiceImpl.saveUserLotteryInfo(userLottery);
			}else{
				result = "2";
			}
		}catch(Exception e){
			log.info("----saveLotteryInfo has error----",e);
			result = "1";
		}
		return "{\"result\":\""+result+"\"}";
	}
	
	@RequestMapping(value="mh/showHistory.do",produces="text/plain;charset=UTF-8;")  
	public String showHistory(HttpServletRequest request,String pageIndex,ModelMap model){
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
		UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
		UserLottery userLottery = new UserLottery();
		userLottery.setUserId(userBindDto.getUserId());
		List<UserLottery> userLotteryList = lotteryServiceImpl.queryHistoryByPage(page,userLottery);
		model.addAttribute("userLotteryList", userLotteryList);
		model.addAttribute("listSize", userLotteryList.size());
		model.addAttribute("page",page);
		return "mh/history_list";
	}
}
