package com.ty.photography.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.ty.photography.common.WxUtil;
import com.ty.photography.model.JsTicket;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.model.PhotoInfoDto;
import com.ty.photography.model.UserBindInfo;
import com.ty.photography.service.IPhotoDownloadService;
import com.ty.photography.service.IPhotoInfoService;
import com.ty.photography.service.IUserBindInfoService;

@Controller
@RequestMapping(value="/wx")
public class PhotoController {
	private Logger log = LoggerFactory.getLogger(PhotoController.class);
	
	@Autowired
	private IUserBindInfoService userBindInfoServiceImpl;
	@Autowired
	private IPhotoInfoService photoInfoServiceImpl;
	@Autowired
	private IPhotoDownloadService photoDownloadServiceImpl;
	
	/**
	 * 编辑照片信息
	 * @param openid  openid
	 * @param userSource 0 手机网用户，1微信用户，2易信用户
	 * @param competitionType 9 全国摄影大赛 ，5 电信摄影大赛
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="editPhoto.do",produces="text/plain;charset=UTF-8;")  
	public String editPhoto(String openid,HttpServletRequest request,ModelMap model){
			jsSDK(request,model);
			model.addAttribute("openid", openid);
			return "edit_photo";
	}
	/**
	 * 微信端js
	 * @param request
	 * @param model
	 */
	private void jsSDK(HttpServletRequest request,ModelMap model){
		String basePath = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if(StringUtils.isNoneBlank(queryString)){
			basePath += "?"+queryString;
		}
		log.debug(basePath);
		WxUtil wxUtil = WxUtil.getInstance();
		JsTicket jsTicket = wxUtil.getJsTicket();
		String appId = wxUtil.getAppId();
		Map<String, String> result = jsTicket.sign(basePath);
		model.addAttribute("appId", appId);
		model.addAttribute("timestamp", result.get("timestamp"));
		model.addAttribute("nonceStr", result.get("nonceStr"));
		model.addAttribute("signature", result.get("signature"));
	}
	/**
	 * 图片上传
	 * @param serverId
	 * @param photoName
	 * @param request
	 */
	@RequestMapping(value="upload.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String upload(String serverId,String photoName,String photoType,HttpServletRequest request){
		String result="0";
		try{
			HttpSession session = request.getSession();
			UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("wx_userBindInfo");
			photoDownloadServiceImpl.uploadPhoto(userBindInfo.getUserId(), serverId, photoName, userBindInfo.getCompetitionType(),photoType,"WX");
		}catch(Exception e){
			result="-1";
			log.error("---weixin photos upload has error---",e);
		}
		return result;
	}

	/**
	 * 显示我的图片
	 * @param openid
	 * @param pageIndex
	 */
	@RequestMapping(value="myPhotos.do",produces="text/plain;charset=UTF-8;")  
	public String myPhotos(String openid,String userSource,String competitionType,String pageIndex,HttpServletRequest request,ModelMap model){
		try{
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
			PhotoInfo photoInfo = new PhotoInfo();
			HttpSession session = request.getSession();
			UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("wx_userBindInfo");
			photoInfo.setUserId(userBindInfo.getUserId());
			photoInfo.setCompetitionType(Integer.parseInt(competitionType));   
			List<PhotoInfo> photoInfoList = photoInfoServiceImpl.findMyPhotosByPage(page,photoInfo);
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			model.addAttribute("photoInfoList", CommonUtils.toJsonStr(photoInfoList));
			model.addAttribute("listSize", photoInfoList.size());
			model.addAttribute("page",page);
			model.addAttribute("openid",openid);
			model.addAttribute("userSource",userSource);
			model.addAttribute("competitionType",competitionType);
			model.addAttribute("thumbnail", imageUrl);
			model.addAttribute("compress", imageShowUrl);
			//使用微信端js
			jsSDK(request,model);
		}catch(Exception e){
			log.error("--- show myPhotos has error ---",e);
		}
		return "my_photos";
	}
	/**
	 * 我的更多照片
	 * @param openid
	 * @param pageIndex
	 * @param request
	 * @return
	 */
	@RequestMapping(value="moreMyPhotos.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String moreMyPhotos(String openid,String userSource,String competitionType,String pageIndex,HttpServletRequest request){
		try{
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
			PhotoInfo photoInfo = new PhotoInfo();
			HttpSession session = request.getSession();
			UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("wx_userBindInfo");
			photoInfo.setUserId(userBindInfo.getUserId());
			photoInfo.setCompetitionType(Integer.parseInt(competitionType));
			List<PhotoInfo> photoInfoList = photoInfoServiceImpl.findMyPhotosByPage(page,photoInfo);
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("page", page);
			resultMap.put("listSize", photoInfoList.size());
			resultMap.put("photoInfoList", photoInfoList);
			resultMap.put("thumbnail", imageUrl);
			resultMap.put("compress", imageShowUrl);
			return CommonUtils.toJsonStr(resultMap);
		}catch(Exception e){
			log.error("--- show moreMyPhotos has error ---",e);
			return null;
		}
	}
	/**
	 * 更多作品
	 * @param pageIndex
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="allPhotos.do",produces="text/plain;charset=UTF-8;")  
	public String allPhotos(String pageIndex,HttpServletRequest request,ModelMap model){
		try{
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
			PhotoInfoDto photoInfoDto = new PhotoInfoDto();
			photoInfoDto.setCompetitionType(9);   //显示全国大赛
			photoInfoDto.setPhotoStatus(1); //审核通过
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page,photoInfoDto,"desc");
			model.addAttribute("photoInfoDtoList", CommonUtils.toJsonStr(photoInfoDtoList));
			model.addAttribute("listSize", photoInfoDtoList.size());
			model.addAttribute("page",page);
			model.addAttribute("thumbnail", imageUrl);
			model.addAttribute("compress", imageShowUrl);
			//使用微信端js
			jsSDK(request,model);
		}catch(Exception e){
			log.error("--- show allPhotos has error ---",e);
		}
		return "all_photos";
	}
	/**
	 * 更多作品
	 * @param pageIndex
	 * @param request
	 * @return
	 */
	@RequestMapping(value="morePhotos.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String morePhotos(String pageIndex,HttpServletRequest request){
		try{
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
			PhotoInfoDto photoInfoDto = new PhotoInfoDto();
			photoInfoDto.setCompetitionType(9);   //显示全国大赛
			photoInfoDto.setPhotoStatus(1); //审核通过
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page,photoInfoDto,"desc");
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("page", page);
			resultMap.put("listSize", photoInfoDtoList.size());
			resultMap.put("photoInfoDtoList", photoInfoDtoList);
			resultMap.put("thumbnail", imageUrl);
			resultMap.put("compress", imageShowUrl);
			return CommonUtils.toJsonStr(resultMap);
		}catch(Exception e){
			log.error("--- show allPhotos has error ---",e);
			return null;
		}
	}
}
