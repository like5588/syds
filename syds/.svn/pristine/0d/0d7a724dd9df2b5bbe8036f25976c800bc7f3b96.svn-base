package com.ty.photography.controller.mobile;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.Page;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.model.PhotoInfoDto;
import com.ty.photography.model.UserBindInfo;
import com.ty.photography.service.IPhotoInfoService;

@Controller
public class YxPhotoController {
	private Logger log = LoggerFactory.getLogger(YxPhotoController.class);
	
	@Autowired
	private IPhotoInfoService photoInfoServiceImpl;
	
	/**
	 * 跳转编辑照片信息页面
	 * @param openid  openid
	 * @param userSource 0 手机网用户，1微信用户，2易信用户
	 * @param competitionType 9 全国摄影大赛 ，5 电信摄影大赛
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="yx/editPhoto.do",produces="text/plain;charset=UTF-8;")  
	public String editPhoto(String openid,String userSource,String competitionType,HttpServletRequest request,ModelMap model){
			model.addAttribute("openid", openid);
			model.addAttribute("userSource", userSource);
			model.addAttribute("competitionType", competitionType);
			return "yx/edit_photo";
	}
	/**
	 * 提交照片信息
	 * @return
	 */
	@RequestMapping(value="yx/subminImageInfo.do",produces="text/plain;charset=UTF-8;")  
	public String subminImageInfo(String photoName,String photoDesc,String simple_group,String photoType,HttpServletRequest request,ModelMap model){
		try{
			HttpSession session = request.getSession();
			UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("yx_userBindInfo");
			PhotoInfo photoInfo = new PhotoInfo();
			photoInfo.setId(CommonUtils.getUUID());
			photoInfo.setUserId(userBindInfo.getUserId());
			photoInfo.setPhotoTitle(photoName);
			photoInfo.setPhotoDesc(photoDesc);
			photoInfo.setPhotoStatus(-2);    //未提交照片
			photoInfo.setIsSelect("0");		//未入选
			photoInfo.setPhotoGroup(1);
			photoInfo.setUploadDate(new Date());
			photoInfo.setPhotoType(photoType);
			photoInfo.setPhotoSource("YX");		//照片来源
			photoInfo.setLaudNum(0);
			simple_group = "0";
			photoInfo.setSimpleGroup(Integer.parseInt(simple_group));
			if(simple_group.equals(1)){
				photoInfo.setGroupCode(CommonUtils.getUUID());			//组图则设置code
				photoInfo.setGroupSerial(0);							//组图第一个位置序号
			}
			photoInfo.setCompetitionType(userBindInfo.getCompetitionType());
			photoInfoServiceImpl.savePhotoInfo(photoInfo);
		}catch(Exception e){
			log.error("--- yixin subminImageInfo has error---",e);
		}
		return "yx/edit_photo_sec";
	}
	
	/**
	 * 显示我的图片
	 * @param openid
	 * @param pageIndex
	 */
	@RequestMapping(value="yx/myPhotos.do",produces="text/plain;charset=UTF-8;")  
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
			UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("yx_userBindInfo");
			photoInfo.setUserId(userBindInfo.getUserId());
			photoInfo.setCompetitionType(5);   //显示电信大赛
			List<PhotoInfo> photoInfoList = photoInfoServiceImpl.findMyPhotosByPage(page,photoInfo);
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			model.addAttribute("photoInfoList", photoInfoList);
			model.addAttribute("listSize", photoInfoList.size());
			model.addAttribute("page",page);
			model.addAttribute("openid",openid);
			model.addAttribute("userSource",userSource);
			model.addAttribute("competitionType",competitionType);
			model.addAttribute("thumbnail", imageUrl);
			model.addAttribute("compress", imageShowUrl);
		}catch(Exception e){
			log.error("--- show yixin myPhotos has error ---",e);
		}
		return "yx/my_photos";
	}
	
	/**
	 * 我的更多照片
	 * @param openid
	 * @param pageIndex
	 * @param request
	 * @return
	 */
	@RequestMapping(value="yx/moreMyPhotos.do",produces="text/plain;charset=UTF-8;")  
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
			UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("yx_userBindInfo");
			photoInfo.setUserId(userBindInfo.getUserId());
			photoInfo.setCompetitionType(5);
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
			log.error("--- show yixin moreMyPhotos has error ---",e);
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
	@RequestMapping(value="/{openid}/{userSource}/{competitionType}/allPhotos.do",produces="text/plain;charset=UTF-8;")  
	public String allPhotos(@PathVariable("openid") String openid,@PathVariable("userSource") String userSource,
			@PathVariable("competitionType") String competitionType,String pageIndex,HttpServletRequest request,ModelMap model){
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
			photoInfoDto.setCompetitionType(Integer.parseInt(competitionType)); 	
			photoInfoDto.setPhotoStatus(1); //审核通过
			List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page,photoInfoDto,"desc");
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			model.addAttribute("photoInfoDtoList", photoInfoDtoList);
			model.addAttribute("listSize", photoInfoDtoList.size());
			model.addAttribute("page",page);
			model.addAttribute("thumbnail", imageUrl);
			model.addAttribute("compress", imageShowUrl);
		}catch(Exception e){
			log.error("--- show yixin allPhotos has error ---",e);
		}
		return "yx/all_photos";
	}
	
	/**
	 * 更多作品
	 * @param pageIndex
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{openid}/{userSource}/{competitionType}/morePhotos.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String morePhotos(@PathVariable("openid") String openid,@PathVariable("userSource") String userSource,
			@PathVariable("competitionType") String competitionType,String pageIndex,HttpServletRequest request,ModelMap model){
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
			photoInfoDto.setCompetitionType(Integer.parseInt(competitionType));
			photoInfoDto.setPhotoStatus(1); //审核通过
			List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotosByPage(page,photoInfoDto,"desc");
			String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
			String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("page", page);
			resultMap.put("listSize", photoInfoDtoList.size());
			resultMap.put("photoInfoDtoList", photoInfoDtoList);
			resultMap.put("thumbnail", imageUrl);
			resultMap.put("compress", imageShowUrl);
			return CommonUtils.toJsonStr(resultMap);
		}catch(Exception e){
			log.error("--- show yixin morePhotos has error ---",e);
			return null;
		}
	}
}
