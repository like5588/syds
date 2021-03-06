package com.ty.photography.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.FileUtil;
import com.ty.photography.model.PhotoInfo;
import com.ty.photography.model.UserBindDto;
import com.ty.photography.service.IPhotoInfoService;

@Controller
public class FileUploadController {
	
	@Autowired
	private IPhotoInfoService photoInfoServiceImpl;
	
	private Logger log = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping(value="/pictureProcessing.do",produces="text/plain;charset=UTF-8;")
	@ResponseBody
	public String pictureProcessing(String fileUrl,String photoGroup,String simpleGroup){
		String result="";
		try {
			//压缩图
			String file_path = CommonUtils.parseProperties("file_path");
			String base_url = CommonUtils.parseProperties("BASE_URL");
			String fileDir = fileUrl.substring(fileUrl.indexOf("/tmp"));
			String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"));
			String fileTmp = fileDir.substring(0,fileDir.lastIndexOf("/"));
//			fileUtil.compressPic(file_path+"/"+fileDir, file_path+"/compress/"+fileName);
			//缩略图
			File dir = new File(file_path+fileTmp+"/thumbnail");
			if(!dir.exists()){
				dir.mkdir();
			}
			FileUtil.zoomImage(
					file_path+fileDir,
					file_path+fileTmp+"/thumbnail"+fileName, 400, 0);
			FileUtil.cutCenterImage(
					file_path+fileTmp+"/thumbnail"+fileName,
					file_path+fileTmp+"/thumbnail"+fileName, 400, 400);
			result = "{\"thumbnail\":\""+base_url+fileTmp+"/thumbnail"+fileName+"\"}";
		} catch (Exception e) {
			log.error("---pictureProcessing has error---",e);
		}
		return result;
	}
	
	@RequestMapping(value="/dx/saveImages.do",produces="text/plain;charset=UTF-8;")
	public String saveImages(String[] photoTitle,String[] imgUrl,String[] photoDesc,String photoGroup,
			String photoType,String simpleGroup,HttpServletRequest request,ModelMap model){
		try{
			String canSave = (String)request.getSession().getAttribute("saveImage");
			if(canSave!=null){
				request.getSession().removeAttribute("saveImage");
			}else{
				return "dx/upload";
			}
			UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("dx_userBindInfo");
			String groupCode="";
			if(simpleGroup.equals("1")){
				groupCode = CommonUtils.getUUID();
			}
			String file_path = CommonUtils.parseProperties("file_path");
			Date now = new Date();
			for(int i=0;i<imgUrl.length;i++){
				String fileDir = imgUrl[i].substring(imgUrl[i].indexOf("/tmp")+1);
				String fileName = imgUrl[i].substring(imgUrl[i].lastIndexOf("/"));
				String fileTmp = fileDir.substring(0,fileDir.indexOf("/"));
				String nowStr = CommonUtils.dateToString(new Date());
				boolean isOK = false;
				try {
					File thumbnail = new File(file_path+"/thumbnail/"+nowStr);
					if(!thumbnail.exists()){
						thumbnail.mkdir();
					}
					isOK = FileUtil.copyFile(file_path+"/"+fileDir, file_path+"/thumbnail/"+nowStr+fileName);
					File compress = new File(file_path+"/compress/"+nowStr);
					if(!compress.exists()){
						compress.mkdir();
					}
					FileUtil.compressPic(file_path+"/"+fileTmp+fileName,file_path+"/compress/"+nowStr+fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(isOK){
					PhotoInfo photoInfo = new PhotoInfo();
					photoInfo.setId(CommonUtils.getUUID());
					photoInfo.setUserId(userBindDto.getUserId());
					if(simpleGroup.equals("0")){
						if(photoTitle.length >0){
							photoInfo.setPhotoTitle(photoTitle[i]);
						}
						if(photoDesc.length >0){
							photoInfo.setPhotoDesc(photoDesc[i]);
						}
						photoType = (String)request.getParameter("photoType_"+i);
						photoInfo.setUploadDate(new Date());
					}else{
						if(photoTitle.length >0){
							photoInfo.setPhotoTitle(photoTitle[0]+" "+(int)(i+1)+"/"+imgUrl.length);
						}
						if(photoDesc.length >0){
							photoInfo.setPhotoDesc(photoDesc[0]);
						}
						photoInfo.setUploadDate(now);
					}
					photoInfo.setPhotoUrl(nowStr+fileName);
					photoInfo.setPhotoStatus(0);
					photoInfo.setUploadDate(new Date());
					photoInfo.setIsSelect("0");
					photoInfo.setPhotoGroup(Integer.parseInt(photoGroup));
					photoInfo.setPhotoType(photoType);
					photoInfo.setPhotoSource("PC");
					photoInfo.setLaudNum(0);
					if(simpleGroup.equals("1")){
						photoInfo.setGroupCode(groupCode);
						photoInfo.setGroupSerial(i);
					}
					photoInfo.setSimpleGroup(Integer.parseInt(simpleGroup));
					photoInfo.setCompetitionType(5);
					photoInfoServiceImpl.savePhotoInfo(photoInfo);
				}
			}
			model.addAttribute("result", "0");
		}catch(Exception e){
			log.error("-----dx saveImages has error------",e);
			model.addAttribute("result", "-1");
		}
		return "dx/saveImages_status";
	}
	/**
	 * 全国
	 * @param photoTitle
	 * @param imgUrl
	 * @param photoDesc
	 * @param photoGroup
	 * @param photoType
	 * @param simpleGroup
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mh/saveImages_mh.do",produces="text/plain;charset=UTF-8;")
	public String saveImages_mh(String[] photoTitle,String[] imgUrl,String[] photoDesc,String photoGroup,
			String photoType,String simpleGroup,HttpServletRequest request,ModelMap model){
		try{
			String canSave = (String)request.getSession().getAttribute("saveImage");
			if(canSave!=null){
				request.getSession().removeAttribute("saveImage");
			}else{
				return "mh/upload";
			}
			UserBindDto userBindDto = (UserBindDto)request.getSession().getAttribute("mh_userBindInfo");
			String groupCode="";
			if(simpleGroup.equals("1")){
				groupCode = CommonUtils.getUUID();
			}
			String file_path = CommonUtils.parseProperties("file_path");
			Date now = new Date();
			for(int i=0;i<imgUrl.length;i++){
				String fileDir = imgUrl[i].substring(imgUrl[i].indexOf("/tmp")+1);
				String fileName = imgUrl[i].substring(imgUrl[i].lastIndexOf("/"));
				String fileTmp = fileDir.substring(0,fileDir.indexOf("/"));
				String nowStr = CommonUtils.dateToString(new Date());
				boolean isOK = false;
				try {
					File thumbnail = new File(file_path+"/thumbnail/"+nowStr);
					if(!thumbnail.exists()){
						thumbnail.mkdir();
					}
					isOK = FileUtil.copyFile(file_path+"/"+fileDir, file_path+"/thumbnail/"+nowStr+fileName);
					File compress = new File(file_path+"/compress/"+nowStr);
					if(!compress.exists()){
						compress.mkdir();
					}
					FileUtil.compressPic(file_path+"/"+fileTmp+fileName,file_path+"/compress/"+nowStr+fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(isOK){
					PhotoInfo photoInfo = new PhotoInfo();
					photoInfo.setId(CommonUtils.getUUID());
					photoInfo.setUserId(userBindDto.getUserId());
					if(simpleGroup.equals("0")){
						if(photoTitle.length >0){
							photoInfo.setPhotoTitle(photoTitle[i]);
						}
						if(photoDesc.length >0){
							photoInfo.setPhotoDesc(photoDesc[i]);
						}
						photoType = (String)request.getParameter("photoType_"+i);
						photoInfo.setUploadDate(new Date());
					}else{
						if(photoTitle.length >0){
							photoInfo.setPhotoTitle(photoTitle[0]+" "+(int)(i+1)+"/"+imgUrl.length);
						}
						if(photoDesc.length >0){
							photoInfo.setPhotoDesc(photoDesc[0]);
						}
						photoInfo.setUploadDate(now);
					}
					photoInfo.setPhotoUrl(nowStr+fileName);
					photoInfo.setPhotoStatus(0);
					photoInfo.setIsSelect("0");
					photoInfo.setPhotoGroup(Integer.parseInt(photoGroup));
					photoInfo.setPhotoType(photoType);
					photoInfo.setPhotoSource("PC");
					photoInfo.setLaudNum(0);
					if(simpleGroup.equals("1")){
						photoInfo.setGroupCode(groupCode);
						photoInfo.setGroupSerial(i);
					}
					photoInfo.setSimpleGroup(Integer.parseInt(simpleGroup));
					photoInfo.setCompetitionType(9);
					photoInfoServiceImpl.savePhotoInfo(photoInfo);
				}
			}
			model.addAttribute("result", "0");
		}catch(Exception e){
			log.error("-----saveImages_mh  has error------",e);
			model.addAttribute("result", "-1");
		}
		return "mh/saveImages_status";
	}

}
