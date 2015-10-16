package com.ty.photography.controller.system;

import java.util.List;

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
import com.ty.photography.model.JudgeLogon;
import com.ty.photography.model.PhotoInfoDto;
import com.ty.photography.service.IPhotoInfoService;

@Controller
@RequestMapping(value="sys")
public class VettingController {
	
	private Logger log = LoggerFactory.getLogger(VettingController.class);
	
	@Autowired
	public IPhotoInfoService photoInfoServiceImpl;
	
	@RequestMapping(value="vetting.do",produces="text/plain;charset=UTF-8;")  
	public String vetting(HttpServletRequest request,ModelMap model){
		JudgeLogon judgeLogon = new JudgeLogon();
		if(request.getSession() != null && request.getSession().getAttribute("judge") != null){
			judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
		}
		model.addAttribute("judge", judgeLogon);
		return "sys/vetting";
	}
	/**
	 * 审核页面
	 * @return
	 */
	@RequestMapping(value="checkPhotos.do",produces="text/plain;charset=UTF-8;")  
	public String checkPhotos(String pageIndex,String photoStatus,ModelMap model){
		Page page = new Page();
		if(StringUtils.isNotBlank(pageIndex)){
			int pageNo = 1;
			try{
				pageNo = Integer.parseInt(pageIndex);
			}catch(Exception e){
			}
			page.setPageIndex(pageNo);
		}
		PhotoInfoDto photoInfoDto = new PhotoInfoDto();
		if(StringUtils.isNotBlank(photoStatus) && CommonUtils.isNumeric(photoStatus)){
			photoInfoDto.setPhotoStatus(Integer.parseInt(photoStatus));
		}else{
			photoInfoDto.setPhotoStatus(0);
		}
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page, photoInfoDto,"asc");
		model.addAttribute("photoInfoDtoList", photoInfoDtoList);
		model.addAttribute("listSize", photoInfoDtoList.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail",imageUrl);
		model.addAttribute("compress",imageShowUrl);
		return "sys/check";
	}
	
	/**
	 * 审核页面分页
	 * @return
	 */
	@RequestMapping(value="checkPage.do",produces="text/plain;charset=UTF-8;")  
	public String checkPage(String pageIndex,String photoStatus,ModelMap model){
		Page page = new Page();
		if(StringUtils.isNotBlank(pageIndex)){
			int pageNo = 1;
			try{
				pageNo = Integer.parseInt(pageIndex);
			}catch(Exception e){
			}
			page.setPageIndex(pageNo);
		}
		PhotoInfoDto photoInfoDto = new PhotoInfoDto();
		if(StringUtils.isNotBlank(photoStatus) && CommonUtils.isNumeric(photoStatus)){
			photoInfoDto.setPhotoStatus(Integer.parseInt(photoStatus));
		}else{
			photoInfoDto.setPhotoStatus(0);
		}
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page, photoInfoDto,"asc");
		model.addAttribute("photoInfoDtoList", photoInfoDtoList);
		model.addAttribute("listSize", photoInfoDtoList.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail",imageUrl);
		model.addAttribute("compress",imageShowUrl);
		return "sys/check_list";
	}
	/**
	 * 审核操作
	 * @param stat
	 * @param photoIds
	 * @param model
	 * @return
	 */
	@RequestMapping(value="checkOpt.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String checkOpt(String stat,String[] photoIds,ModelMap model){
		String result = "";
		try{
			if(StringUtils.isNoneBlank(stat) && photoIds != null && photoIds.length>0){
				int num = photoInfoServiceImpl.checkPhotos(stat, photoIds);
				if(photoIds.length == num ){
					result = "{\"result\":\"0\"}";
				}else{
					result = "{\"result\":\"2\"}";
				}
			}else{
				result = "{\"result\":\"1\"}";
			}
		}catch(Exception e){
			result = "{\"result\":\"-1\"}";
			log.error("---checkOpt has error ---",e);
		}
		return result;
	}
}
