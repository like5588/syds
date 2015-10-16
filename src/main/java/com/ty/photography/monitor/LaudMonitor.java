package com.ty.photography.monitor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.model.UserBindDto;
import com.ty.photography.service.ILaudService;


@Controller
public class LaudMonitor {
	
	@Autowired
	private ILaudService laudServiceImpl;
	
	@RequestMapping("/laud")
	@ResponseBody
	public Map<String,String> laud(String imgId,HttpServletRequest request,HttpServletResponse response){
		Map<String,String> resultMap = new HashMap<String,String>();
		UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("dx_userBindInfo");
		laudServiceImpl.laud(imgId,userBindDto.getUserId());

		return resultMap;
	}

}
