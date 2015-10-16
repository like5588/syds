package com.ty.photography.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.model.UserBindDto;
import com.ty.photography.model.UserBindInfo;
import com.ty.photography.service.ILaudService;

@Controller
public class LaudController {
	private Logger log = LoggerFactory.getLogger(LaudController.class);
	
	@Autowired
	private ILaudService laudServiceImpl;
	/**
	 * 点赞
	 * @param path
	 * @param photoId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{path}/{photoId}/laud.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String laud(@PathVariable("path") String path,@PathVariable("photoId") String photoId,HttpServletRequest request){
		String userId="";
		String resultStr;
		try{
			if(path.equals("wx")){
				userId = ((UserBindInfo)request.getSession().getAttribute("wx_userBindInfo")).getUserId();
			}else if(path.equals("yx")){
				userId = ((UserBindInfo)request.getSession().getAttribute("yx_userBindInfo")).getUserId();
			}else if(path.equals("mh")){
				userId = ((UserBindDto)request.getSession().getAttribute("mh_userBindInfo")).getUserId();
			}else if(path.equals("dx")){
				userId = ((UserBindDto)request.getSession().getAttribute("dx_userBindInfo")).getUserId();
			}
			if(StringUtils.isNoneBlank(userId) && StringUtils.isNoneBlank(photoId)){
				String status = laudServiceImpl.laud(photoId, userId);
				resultStr = "{\"result\":\""+status+"\"}";
	 		}else{
	 			resultStr = "{\"result\":\"3\"}";	//参数为空
	 		}
		}catch(Exception e){
			log.error("----user laud has error----",e);
			resultStr = "{\"result\":\"-1\"}";
		}
		
		return resultStr;
	}

}
