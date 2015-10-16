package com.ty.photography.controller.system;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.model.JudgeLogon;
import com.ty.photography.model.PhotoInfoDto;
import com.ty.photography.service.IPhotoInfoService;

@Controller
@RequestMapping(value="sys")
public class BatchDownLoadController {
	
	@Autowired
	public IPhotoInfoService photoInfoServiceImpl;
	/**
	 * 判断是否存在入围作品
	 * @param request
	 * @return
	 */
	@RequestMapping(value="validateDown.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String validateDown(HttpServletRequest request){
		String result = "";
		try{
			PhotoInfoDto photoInfoDto = new PhotoInfoDto();
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			if(judgeLogon.getType()==0){
				result = "{\"result\":\"1\"}";	//无权访问
			}else if(judgeLogon.getType() == 5){	//电信评委
				photoInfoDto.setCompetitionType(5);
				photoInfoDto.setCompetitionArea(judgeLogon.getCompetitionArea());
			}else if(judgeLogon.getType() == 9){	//全国评委
				photoInfoDto.setCompetitionType(9);
				photoInfoDto.setJudgeGroupId(judgeLogon.getJudgeGroupId());
				if(judgeLogon.getJudgeRole() == 0){	//组员
					photoInfoDto.setJudgeId(judgeLogon.getId());
				}
			}
			photoInfoDto.setPhotoStatus(1);	
			photoInfoDto.setIsSelect("2");
			List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotos(photoInfoDto);
			if(photoInfoDtoList.size() >0){
				result = "{\"result\":\"0\"}";	//有入围照片
			}else{
				result = "{\"result\":\"2\"}";	//没有入围照片
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 下载入围作品
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="batchDownload.do",produces="text/plain;charset=UTF-8;")  
	public void batchDownload(HttpServletRequest request, HttpServletResponse response){
		try{
			PhotoInfoDto photoInfoDto = new PhotoInfoDto();
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			if(judgeLogon.getType()==0){
				return;
			}else if(judgeLogon.getType() == 5){	//电信评委
				photoInfoDto.setCompetitionType(5);
				photoInfoDto.setCompetitionArea(judgeLogon.getCompetitionArea());
			}else if(judgeLogon.getType() == 9){	//全国评委
				photoInfoDto.setCompetitionType(9);
				photoInfoDto.setJudgeGroupId(judgeLogon.getJudgeGroupId());
				if(judgeLogon.getJudgeRole() == 0){	//组员
					photoInfoDto.setJudgeId(judgeLogon.getId());
				}
			}
			photoInfoDto.setPhotoStatus(1);	
			photoInfoDto.setIsSelect("2");
			List<PhotoInfoDto> photoInfoDtoList = photoInfoServiceImpl.findAllPhotos(photoInfoDto);
			down(response,photoInfoDtoList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void down(HttpServletResponse response,List<PhotoInfoDto> photoInfoDtoList) throws IOException{
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode("入围作品","utf-8")+".zip");
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		int i = 0;
		for (PhotoInfoDto photoInfoDto : photoInfoDtoList) {
			zos.putNextEntry(new ZipEntry((++i)+"、"+photoInfoDto.getUserName()+"+《"+photoInfoDto.getPhotoTitle()+"》+"+photoInfoDto.getMobile()+photoInfoDto.getPhotoUrl().substring(photoInfoDto.getPhotoUrl().lastIndexOf("."))));
			String path = CommonUtils.parseProperties("file_path");
			FileInputStream fis = new FileInputStream(path+"/compress/"+photoInfoDto.getPhotoUrl());
			byte[] buffer = new byte[1024];
			int r = 0;
			while ((r = fis.read(buffer)) != -1) {
				zos.write(buffer, 0, r);
			}
			fis.close();
		}
		zos.flush();
		zos.close();
	}

}
