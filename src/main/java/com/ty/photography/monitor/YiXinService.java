package com.ty.photography.monitor;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.service.IPhotoInfoService;

public class YiXinService {
	
	public static Logger log = LoggerFactory.getLogger(YiXinService.class);
	
	public static String title = "2015中国电信员工摄影大赛";
	
	public static String processRequest(HttpServletRequest request) {
		
		String basePath = CommonUtils.parseProperties("BASE_URL");
		
		String returnxml = "";
		//默认回复
		String respContent = "请求处理异常请稍后尝试";
		
		try {
			String xml = getXmlString(request);
			//时间戳
			String timeNow = getElementText(xml, "CreateTime");
			//本帐号openId
			String myName = getElementText(xml, "ToUserName");
			//来源openId
			String sourceName = getElementText(xml, "FromUserName");
			
			/*
			 * 用户关注这个公共帐号时自动回复内容
			 */
			if ("subscribe".equals(getElementText(xml, "Event"))) {
				respContent = "您好，感谢关注“天翼手机”。欢迎参加2015中国电信员工摄影大赛！本次大赛旨在使用手机、相机摄影，通过易信、天翼手机网平台上传，展示中国电信员工精神风貌，体验天翼4G美好生活。征集时间为2015年8月1日-2015年9月10日";
				returnxml = "<xml> <ToUserName><![CDATA["
						+ sourceName
						+ "]]></ToUserName> <FromUserName><![CDATA["
						+ myName
						+ "]]></FromUserName> <CreateTime>"
						+ timeNow
						+ "</CreateTime> <MsgType><![CDATA[text]]></MsgType> <Content><![CDATA["
						+ respContent + "]]></Content> </xml>";
			}
			/*
			 *用户点击菜单事件，执行以下语句 
			 */
			if ("CLICK".equals(getElementText(xml, "Event"))) {
				String content = getElementText(xml, "EventKey");
				if ("iwantupload".equals(content)) {
					String picUrl = basePath + "/images/shangchuan.jpg";
					String url = basePath + "/yx/editPhoto.do?openid="+sourceName+"&userSource=2&competitionType=5";
					String description = "参展提示\n1.上传作品照片\n2.等待作品审核\n3.查看我的作品...";
					returnxml = "<xml><ToUserName><![CDATA["
							+ sourceName
							+ "]]></ToUserName><FromUserName><![CDATA["
							+ myName
							+ "]]></FromUserName><CreateTime>"
							+ timeNow
							+ "</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA["
							+ title + "]]></Title><Description><![CDATA["
							+ description
							+ "]]></Description><PicUrl><![CDATA[" + picUrl
							+ "]]></PicUrl><Url><![CDATA[" + url
							+ "]]></Url></item></Articles></xml>";
				}
				else if ("myPhotos".equals(content)) {
					String picUrl = basePath + "/images/zuopin.png";
					String url = basePath + "/yx/myPhotos.do?openid="+sourceName+"&userSource=2&competitionType=5";
					String description = "点击这里，立即查看";
					returnxml = "<xml><ToUserName><![CDATA["
							+ sourceName
							+ "]]></ToUserName><FromUserName><![CDATA["
							+ myName
							+ "]]></FromUserName><CreateTime>"
							+ timeNow
							+ "</CreateTime> <MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA["
							+ title + "]]></Title><Description><![CDATA["
							+ description
							+ "]]></Description><PicUrl><![CDATA[" + picUrl
							+ "]]></PicUrl><Url><![CDATA[" + url
							+ "]]></Url></item></Articles></xml>";
				}
			}
			else if ("image".equals(getElementText(xml, "MsgType"))) {
				String urlStr = getElementText(xml, "PicUrl");
				IPhotoInfoService photoInfoService = (IPhotoInfoService)ContextLoaderListener.getCurrentWebApplicationContext().getBean("photoInfoServiceImpl");
				boolean result = photoInfoService.updatePhotoStatus(sourceName, urlStr, "2", "5");
				if(result){
					respContent = "上传成功！审核通过后，可以在“作品展示”菜单中查看您的作品。";
					returnxml = "<xml><ToUserName><![CDATA["
							+ sourceName
							+ "]]></ToUserName><FromUserName><![CDATA["
							+ myName
							+ "]]></FromUserName><CreateTime>"
							+ timeNow
							+ "</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["
							+ respContent + "]]></Content></xml>";
				}
				
			}
		} catch (Exception e) {
			log.error("---易信响应请求出错---",e);
		}
		return returnxml;
	}
	
	/**
	 * 处理xml格式字符串
	 * @param data
	 * @param elementName
	 * @return
	 */
	public static String getElementText(String data, String elementName) {
		try {
			Document document = DocumentHelper.parseText(data);
			Element root = document.getRootElement();
			Element res = (Element) root.selectSingleNode(elementName);
			if (res != null) {
				return res.getText();
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getXmlString(HttpServletRequest request)
			throws IOException {
		InputStream is = request.getInputStream();
		int size = request.getContentLength();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		String xmlDate = new String(buffer, "UTF-8");
		return xmlDate;
	}
}
