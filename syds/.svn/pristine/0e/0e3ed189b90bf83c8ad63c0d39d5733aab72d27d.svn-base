package com.ty.photography.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ty.photography.common.Page;

@Controller
public class Test {
	
	@RequestMapping(value="test.do",produces="text/plain;charset=UTF-8;")  
	public String test(String pageIndex,HttpServletRequest request,ModelMap model){
		Page page = new Page();
		int pageNo = 1;
		if(StringUtils.isNotBlank(pageIndex)){
			try{
				pageNo = Integer.parseInt(pageIndex);
			}catch(Exception e){
				throw e;
			}
			page.setPageIndex(pageNo);
		}
			page.setRowTotal(141);
			model.addAttribute("page", page);
			return "../test";
	}

}
