package com.ty.photography.controller.system;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ty.photography.model.PhotoList;
import com.ty.photography.service.IPhotoInfoService;
import com.ty.photography.service.IPhotoListService;

@Controller
@RequestMapping(value="sys")
public class DxPingXuanController {
	private Logger log = LoggerFactory.getLogger(VettingController.class);
	@Autowired
	public IPhotoInfoService photoInfoServiceImpl;
	@Autowired
	public IPhotoListService photoListServiceImpl;
	/**
	 * 全国评选首页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="main.do",produces="text/plain;charset=UTF-8;")  
	public String pingxuan(HttpServletRequest request,ModelMap model){
		JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
		model.addAttribute("role", judgeLogon.getJudgeRole());
		return "sys/main";
	}
	/**
	 * 全国待评选
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="daipingxuan.do",produces="text/plain;charset=UTF-8;")  
	public String daipingxuan(HttpServletRequest request,ModelMap model){
		return "sys/daipingxuan";
	}
	/**
	 * 全国带入选
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="dairuxuan.do",produces="text/plain;charset=UTF-8;")  
	public String dairuxuan(HttpServletRequest request,ModelMap model){
		return "sys/dairuxuan";
	}
	/**
	 * 全国初评
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="chuping.do",produces="text/plain;charset=UTF-8;")  
	public String chuping(HttpServletRequest request,ModelMap model){
		return "sys/chuping";
	}
	/**
	 * 全国佳作
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="jiazuo.do",produces="text/plain;charset=UTF-8;")  
	public String jiazuo(HttpServletRequest request,ModelMap model){
		return "sys/jiazuo";
	}
	/**
	 * 佳作
	 * @param photoIds
	 * @param request
	 * @return
	 */
	@RequestMapping(value="excellent.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String excellent(String[] photoIds,HttpServletRequest request){
		String result = "";
		try{
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			photoListServiceImpl.save(photoIds,judgeLogon.getId());
			result = "{\"result\":\"0\"}";
		}catch(Exception e){
			log.error("--- excellent has error ---",e);
			result = "{\"result\":\"-1\"}";
		}
		return result;
	}
	@RequestMapping(value="excellentPage.do",produces="text/plain;charset=UTF-8;")  
	public String excellentPage(String userName,String photoTitle,String photoType,String simpleGroup,String status,
			String photoGroup,String ListType,String pageIndex,HttpServletRequest request,ModelMap model){
		try{
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			Page page = new Page();
			page.setPageSize(8);
			int pageNo = 1;
			if(pageIndex != null){
				try{
					pageNo = Integer.parseInt(pageIndex);
				}catch(Exception e){
					throw e;
				}
				page.setPageIndex(pageNo);
			}
			PhotoList listdto = new PhotoList();
			
			
			if(StringUtils.isNotBlank(userName)){
				listdto.setUserName(userName);
			}
			if(StringUtils.isNotBlank(photoTitle)){
				listdto.setPhotoTitle(photoTitle);
			}
			if(StringUtils.isNotBlank(photoType)){
				listdto.setPhotoType(photoType);
			}
			if(StringUtils.isNotBlank(simpleGroup)){
				listdto.setSimpleGroup(Integer.valueOf(simpleGroup));
			}
			if(StringUtils.isNotBlank(photoGroup)){
				listdto.setPhotoGroup(Integer.valueOf(photoGroup));
			}
			if(StringUtils.isNotBlank(status)){
				listdto.setStatus(Integer.valueOf(status));
			}
			if(StringUtils.isNotBlank(ListType)){
				listdto.setListType(ListType);
			} 
			listdto.setJudgeGroupId(judgeLogon.getJudgeGroupId());
			if(judgeLogon.getJudgeRole() == 0){	//组员
				listdto.setJudgeId(judgeLogon.getId());
			}
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			List<PhotoList>  photoListDto = photoListServiceImpl.findIndexPhotosByPage(page,listdto); 	
			model.addAttribute("photoListDto",photoListDto);
			model.addAttribute("listSize",photoListDto.size());
			model.addAttribute("thumbnail", imageUrl);
			model.addAttribute("compress", imageShowUrl);
			model.addAttribute("page",page);
		}catch(Exception e){
			log.error("--- excellentPage has error ---",e);
		}
		return "sys/jiazuo_list";
	}
	/**
	 * 佳作更新
	 * @param status
	 * @param photoId
	 * @return
	 */
	@RequestMapping(value="updateInfo.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String updateInfo(String status,String comment,String photoId,HttpServletRequest request){
		String result = "";
		try {
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			boolean stat = photoListServiceImpl.updateInfo(status,comment,photoId,judgeLogon.getId());
			result = "{\"result\":\""+(stat?0:1)+"\"}";
		} catch (Exception e) {
			result = "{\"result\":\"-1\"}";
			log.error("---excellent updateInfo has error---",e);
		}
		return result;
	}
	
	@RequestMapping(value="dx_pingxuan.do",produces="text/plain;charset=UTF-8;")  
	public String dx_pingxuan(String userName,String photoTitle,String photoType,String simpleGroup,
			String photoGroup,String isSelect,String pageIndex,HttpServletRequest request,HttpServletResponse response, ModelMap model){
		JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
		Page page = new Page();
		if(StringUtils.isNotBlank(pageIndex)){
			int pageNo = 1;
			try{
				pageNo = Integer.parseInt(pageIndex);
			}catch(Exception e){
			}
			page.setPageIndex(pageNo);
			Cookie cookie = new Cookie("isSelect_"+isSelect,pageNo+"");
			response.addCookie(cookie);
		}else{
			 Cookie[] cookies = request.getCookies();
			    if(null!=cookies){
			        for(Cookie cookie : cookies){
			        	if(cookie.getName().equals("isSelect_"+isSelect)){
			        		 page.setPageIndex(Integer.parseInt(cookie.getValue().equals("0")?"1":cookie.getValue()));
			        		 break;
						}
			        }
			    }
		}
		PhotoInfoDto photoInfoDto = new PhotoInfoDto();
		photoInfoDto.setPhotoStatus(1);	//已通过审核
		if(StringUtils.isNotBlank(isSelect) && CommonUtils.isNumeric(isSelect)){
			photoInfoDto.setIsSelect(isSelect);
		}else{
			photoInfoDto.setIsSelect("0");
		}
		if(StringUtils.isNotBlank(userName)){
			photoInfoDto.setUserName(userName.trim());
		}
		if(StringUtils.isNotBlank(photoTitle)){
			photoInfoDto.setPhotoTitle(photoTitle.trim());
		}
		if(StringUtils.isNotBlank(photoType)){
			photoInfoDto.setPhotoType(photoType);
		}
		if(StringUtils.isNotBlank(simpleGroup)){
			photoInfoDto.setSimpleGroup(Integer.valueOf(simpleGroup));
		}
		if(StringUtils.isNotBlank(photoGroup)){
			photoInfoDto.setPhotoGroup(Integer.valueOf(photoGroup));
		}
		if(judgeLogon.getType() == 5){	//电信评委
			photoInfoDto.setCompetitionType(5);
			photoInfoDto.setCompetitionArea(judgeLogon.getCompetitionArea());
		}else if(judgeLogon.getType() == 9){	//全国评委
			photoInfoDto.setCompetitionType(9);
			photoInfoDto.setJudgeGroupId(judgeLogon.getJudgeGroupId());
			if(judgeLogon.getJudgeRole() == 0){	//组员
				photoInfoDto.setJudgeId(judgeLogon.getId());
			}
		}
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page, photoInfoDto,"asc");
		if(photoInfoDtoList.size()==0 && page.getPageTotal() >0 && page.getPageTotal()<=page.getPageIndex()){
			page.setPageIndex(page.getPageTotal());
			Cookie cookie = new Cookie("isSelect_"+isSelect,page.getPageTotal()+"");
			response.addCookie(cookie);
			photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page, photoInfoDto,"asc");
		}
		model.addAttribute("photoInfoDtoList", photoInfoDtoList);
		model.addAttribute("listSize", photoInfoDtoList.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail",imageUrl);
		model.addAttribute("compress",imageShowUrl);
		return "sys/photos_list";
	}
	
	/**
	 * 评审操作
	 * @param newStats,oldStats
	 * @param photoIds
	 * @param model
	 * @return
	 */
	@RequestMapping(value="updateIsSelect.do",produces="text/plain;charset=UTF-8;")
	@ResponseBody
	public String updateIsSelect(String newStats,String oldStats,String[] photoIds,ModelMap model){
		String result = "";
		try{
			if(StringUtils.isNoneBlank(newStats) && StringUtils.isNoneBlank(oldStats) && photoIds != null && photoIds.length>0){
				int num = photoInfoServiceImpl.updateIsSelect(newStats, oldStats, photoIds);
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
			log.error("---updateIsSelect has error ---",e);
		}
		return result;
	}
}
