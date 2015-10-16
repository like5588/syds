package com.ty.photography.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.Page;
import com.ty.photography.model.PhotoInfoDto;
import com.ty.photography.model.PhotoList;
import com.ty.photography.model.UserBindDto;
import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.ComparableList;
import com.ty.photography.common.RedisUtil;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.monitor.TelecomInfo;
import com.ty.photography.service.IDictionaryService;
import com.ty.photography.service.IPhotoListService;
import com.ty.photography.service.IUserBindInfoService;
import com.ty.photography.service.IUserInfoService;
import com.ty.photography.service.IPhotoInfoService;


@Controller
public class PhotoInfoController {
	
	@Autowired
	private IUserBindInfoService userBindInfoServiceImpl;
	@Autowired
	private IUserInfoService userInfoServiceImpl;
	@Autowired
	private IPhotoInfoService photoInfoServiceImpl;
	@Autowired
	private IPhotoListService photoListServiceImpl;
	@Autowired
	private IDictionaryService dictionaryServiceImpl;
	
	
	
	/**
	 * 全国赛区作品展示
	 * 测试地址：http://localhost/syds/mh_exhibition.jsp
	 * @return
	 */
	@RequestMapping(value="exhibition.do",produces="text/plain;charset=UTF-8;")  
	public String exhibition(String competitionType,String userName,String photoType,HttpServletRequest request,
			String photoTitle,String simpleGroup,String photoGroup,String pageIndex,ModelMap model){
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
		PhotoInfoDto dto = new PhotoInfoDto();
		if(StringUtils.isNotBlank(competitionType)){
			dto.setCompetitionType(Integer.valueOf(competitionType));
		}
		if(StringUtils.isNotBlank(userName)){ 
			dto.setUserName(userName);
		}
		if(StringUtils.isNotBlank(photoType)){
			dto.setPhotoType(photoType);
		}
 
		if(StringUtils.isNotBlank(photoTitle)){
			dto.setPhotoTitle(photoTitle);
		}
		if(StringUtils.isNotBlank(simpleGroup)){
			dto.setSimpleGroup(Integer.valueOf(simpleGroup));
		}
		if(StringUtils.isNotBlank(photoGroup)){
			dto.setPhotoGroup(Integer.valueOf(photoGroup));
		}
		dto.setPhotoStatus(1);  //设置图片状态为审核通过
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfoDto>  photoInfoDto = photoInfoServiceImpl.findAllPhotosByPage(page,dto,"desc"); 	
		//判断用户是否已登录
				UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
				if(userBindDto != null){
					RedisUtil redis = new RedisUtil();
					Set<String> photoIds = redis.sinter(userBindDto.getUserId()+"_"+CommonUtils.dateToString(new Date()));
					for(PhotoInfoDto photoDto : photoInfoDto){
						if(photoIds.contains(photoDto.getId())){
							photoDto.setLaud(true);
						}
					}
					redis.close();
				}
		model.addAttribute("PhotoListDto",photoInfoDto);
		model.addAttribute("listSize",photoInfoDto.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail", imageUrl);
		model.addAttribute("compress", imageShowUrl);
		return "../teleCom_photo_list";
	}
	/**
	 * 获取当前用户作品信息，显示在我的作品页面。
	 * 当前用户user_id = sourceId=1
	 * 测试地址：http://localhost/syds/mh_myPhoto.jsp?sourceId=39a64dcf93294c3a9f4b6a909a3ff75d
	 * @return
	 */
	@RequestMapping(value={"/dx/getMyPhoto.do"},produces="text/plain;charset=UTF-8;")  
	public String getMyPhoto(String photoType,String photoTitle,String pageIndex,HttpServletRequest request,
			String simpleGroup,String photoGroup,ModelMap model){
		UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("dx_userBindInfo");
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
		PhotoInfo dto = new PhotoInfo();
		dto.setUserId(userBindDto.getUserId());
		if(StringUtils.isNotBlank(photoTitle)){
			dto.setPhotoTitle(photoTitle);
		}
		if(StringUtils.isNotBlank(photoType)){
			dto.setPhotoType(photoType);
		}
		if(StringUtils.isNotBlank(simpleGroup)){
			dto.setSimpleGroup(Integer.valueOf(simpleGroup));
		}
		if(StringUtils.isNotBlank(photoGroup)){
			dto.setPhotoGroup(Integer.valueOf(photoGroup));
		}
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfo> photoList = photoInfoServiceImpl.findMyPhotosByPage(page,dto); 	//我的作品展示
		model.addAttribute("photoList",photoList);
		model.addAttribute("listSize",photoList.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail", imageUrl);
		model.addAttribute("compress", imageShowUrl);
		return "/dx/photo_list";
	}
	/**
	 * 门户我的作品
	 * @param photoType
	 * @param photoTitle
	 * @param pageIndex
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/mh/getMyPhoto.do"},produces="text/plain;charset=UTF-8;")  
	public String getMhMyPhoto(String photoType,String photoTitle,String pageIndex,HttpServletRequest request,
			String simpleGroup,String photoGroup,ModelMap model){
		UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
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
		PhotoInfo dto = new PhotoInfo();
		dto.setUserId(userBindDto.getUserId());
		if(StringUtils.isNotBlank(photoTitle)){
			dto.setPhotoTitle(photoTitle);
		}
		if(StringUtils.isNotBlank(photoType)){
			dto.setPhotoType(photoType);
		}
		if(StringUtils.isNotBlank(simpleGroup)){
			dto.setSimpleGroup(Integer.valueOf(simpleGroup));
		}
		if(StringUtils.isNotBlank(photoGroup)){
			dto.setPhotoGroup(Integer.valueOf(photoGroup));
		}		
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfo> photoList = photoInfoServiceImpl.findMyPhotosByPage(page,dto); 	//我的作品展示
		model.addAttribute("photoList",photoList);
		model.addAttribute("listSize",photoList.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail", imageUrl);
		model.addAttribute("compress", imageShowUrl);
		return "/mh/photo_list";
	}
	/**
	 * 获取电信当前赛区作品信息
	 * 测试地址：http://localhost/syds/teleCom_photos.jsp?area=北京  赛区作品展示
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="photosTeleCom.do",produces="text/plain;charset=UTF-8;") 
	public String photosTeleCom(String competitionType,String competitionArea,String userName,HttpServletRequest request,
			String photoType,String photoTitle,String simpleGroup,String photoGroup,String pageIndex,
			ModelMap model) throws UnsupportedEncodingException{
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
		competitionArea = URLDecoder.decode(competitionArea,"utf-8");

		PhotoInfoDto dto = new PhotoInfoDto();
		if(StringUtils.isNotBlank(competitionType)){
			dto.setCompetitionType(Integer.valueOf(competitionType));
		}
		if(StringUtils.isNotBlank(competitionArea)){
			dto.setCompetitionArea(competitionArea);
		}
		if(StringUtils.isNotBlank(userName)){
			dto.setUserName(userName);
		}
		if(StringUtils.isNotBlank(photoType)){
			dto.setPhotoType(photoType);
		}
		if(StringUtils.isNotBlank(photoTitle)){
			dto.setPhotoTitle(photoTitle);
		}	
		if(StringUtils.isNotBlank(simpleGroup)){
			dto.setSimpleGroup(Integer.valueOf(simpleGroup));
		}
		if(StringUtils.isNotBlank(photoGroup)){
			dto.setPhotoGroup(Integer.valueOf(photoGroup));
		}
		dto.setPhotoStatus(1);
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoInfoDto>  photoInfoDto = photoInfoServiceImpl.findAllPhotosByPage(page,dto,"desc"); 	//当前赛区所有的作品信息
		//判断用户是否已登录
		UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("dx_userBindInfo");
		if(userBindDto != null){
			RedisUtil redis = new RedisUtil();
			Set<String> photoIds = redis.sinter(userBindDto.getUserId()+"_"+CommonUtils.dateToString(new Date()));
			for(PhotoInfoDto photoDto : photoInfoDto){
				if(photoIds.contains(photoDto.getId())){
					photoDto.setLaud(true);
				}
			}
			redis.close();
		}
		model.addAttribute("PhotoListDto",photoInfoDto);
		model.addAttribute("listSize",photoInfoDto.size());
		model.addAttribute("page",page);
		model.addAttribute("thumbnail", imageUrl);
		model.addAttribute("compress", imageShowUrl);
		return "../teleCom_photo_list";
	}
	/**author zcx
	 * 获取电信首页信息
	 * 测试地址：http://localhost/syds/teleComPhotos.jsp?competitionArea=1
	 * @return
	 */
	@RequestMapping(value="teleComPhotos.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String teleComPhotos(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> info = null;
		List<Map<String,Object>> sortInfo = new ArrayList<Map<String,Object>>();
		try{
			info = TelecomInfo.info;
			sortInfo.addAll(info);
			ComparableList comparator=new ComparableList();
			Collections.sort(sortInfo, comparator);
			sortInfo = sortInfo.subList(0, 3);
//			for (Map<String, Object> map : info) {
//				Iterator<String> ite= map.keySet().iterator();
//				while(ite.hasNext()){
//					String key = ite.next();
//					Object value = map.get(key);
//				}
//			}
			result.put("info", info);
			result.put("sortInfo", sortInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return CommonUtils.toJsonStr(result);
		
	}
	/**author zcx
	 * 获取全国首页照片信息-佳作（相机组） -佳作（手机组）
	 * 测试地址：http://localhost/syds/mh_index.jsp
	 * @return
	 */
	@RequestMapping(value="contryPhotos.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String contryPhotos(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		List<PhotoList> Camera_index  = null;
		List<PhotoList> cellphone_index  = null;
		try{
			Camera_index = TelecomInfo.camera_index;
			cellphone_index = TelecomInfo.cellphone_index;
			result.put("Camera_index", Camera_index);			//首页佳作相机组
			result.put("cellphone_index", cellphone_index);		//首页佳作手机组
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return CommonUtils.toJsonStr(result);
		
	}
	
	/**author zcx
	 * 获取更多佳作（相机组）list_type =1  -更多佳作（手机组）list_type =2
	 * 测试地址：http://localhost/syds/mh_index.jsp?listType=1
	 * @return
	 */
	@RequestMapping(value="moreCameraCellphone.do",produces="text/plain;charset=UTF-8;")  
	public String moreCameraCellphone(String userName,String photoTitle,String photoType,
			String simpleGroup,String photoGroup,String ListType,HttpServletRequest request,String pageIndex,ModelMap model){
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
		//competitionArea = URLDecoder.decode(competitionArea,"utf-8");
		PhotoList Listdto = new PhotoList();
		
		if(StringUtils.isNotBlank(userName)){
			Listdto.setUserName(userName);
		}
		if(StringUtils.isNotBlank(photoTitle)){
			Listdto.setPhotoTitle(photoTitle);
		}
		if(StringUtils.isNotBlank(photoType)){
			Listdto.setPhotoType(photoType);
		}
		if(StringUtils.isNotBlank(simpleGroup)){
			Listdto.setSimpleGroup(Integer.valueOf(simpleGroup));
		}
		if(StringUtils.isNotBlank(photoGroup)){
			Listdto.setPhotoGroup(Integer.valueOf(photoGroup));
		}
		if(StringUtils.isNotBlank(ListType)){
			Listdto.setListType(ListType);
		} 
		Listdto.setStatus(1);
		String imageUrl = CommonUtils.parseProperties("thumbnail_image_url");
		String imageShowUrl = CommonUtils.parseProperties("compress_image_url");
		List<PhotoList>  photoListDto = photoListServiceImpl.findIndexPhotosByPage(page,Listdto); 	
		//判断用户是否已登录
		UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
		if(userBindDto != null){
			RedisUtil redis = new RedisUtil();
			Set<String> photoIds = redis.sinter(userBindDto.getUserId()+"_"+CommonUtils.dateToString(new Date()));
			for(PhotoList photoDto : photoListDto){
				if(photoIds.contains(photoDto.getId())){
					photoDto.setLaud(true);
				}
			}
			redis.close();
		}
		model.addAttribute("PhotoListDto",photoListDto);
		model.addAttribute("listSize",photoListDto.size());
		model.addAttribute("thumbnail", imageUrl);
		model.addAttribute("compress", imageShowUrl);
		model.addAttribute("page",page);
		return "../teleCom_photo_list";
		
	}
}