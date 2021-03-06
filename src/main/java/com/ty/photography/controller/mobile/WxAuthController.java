package com.ty.photography.controller.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.UrlUtil;


@Controller
public class WxAuthController {
	
	@RequestMapping(value="/redirectInfo.do",produces="text/plain;charset=UTF-8;")
	@ResponseBody
	public void redirectInfo(String code ,String state,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String appId=CommonUtils.parseProperties("WX_APPID");
		String secret=CommonUtils.parseProperties("WX_SECRET");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		String result = UrlUtil.httpsRequest(url, "GET", null);
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String,String> map = mapper.readValue(result, HashMap.class);
		if(state != null && state.equals("0")){
			//我的作品
			response.sendRedirect(CommonUtils.parseProperties("BASE_URL")+"/wx/myPhotos.do?userSource=1&competitionType=9&openid="+map.get("openid"));
		}else if(state != null && state.equals("1")){
			//更多作品
			response.sendRedirect(CommonUtils.parseProperties("BASE_URL")+"/wx/allPhotos.do?userSource=1&competitionType=9&openid="+map.get("openid"));
		}
	}

	public static String getAccessToken(String appId,String secret){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appId + "&secret=" + secret;
		String str = UrlUtil.httpsRequest(url, "GET", null);
		Map<String,String> result = CommonUtils.parseJson(str, Map.class);
		System.out.println(result.get("access_token"));
		return result.get("access_token");
	}
	
	public static Map<String,Object> getMenu(){
		String baseUrl = CommonUtils.parseProperties("BASE_URL");
		Map<String,Object> parent = new HashMap<String,Object>();
		Map<String,Object> child = new HashMap<String,Object>();
		List<Map<String,Object>> listC = new ArrayList<Map<String,Object>>();
		
		
		child = new HashMap<String,Object>();
		child.put("type", "view");
		child.put("name", "大赛介绍");
		child.put("url", CommonUtils.parseProperties("BASE_URL")+"/introduce.jsp");
		listC.add(child);
		child = new HashMap<String,Object>();
		child.put("type", "view");
		child.put("name", "征稿细则");
		child.put("url", CommonUtils.parseProperties("BASE_URL")+"/rule.jsp");
		listC.add(child);
		child = new HashMap<String,Object>();
		child.put("type", "view");
		child.put("name", "更多作品");
		child.put("url", baseUrl+"/wx/allPhotos.do");
		listC.add(child);
		
//		child.put("type", "click");
//		child.put("name", "我要上传");
//		child.put("key", "iwantupload");
//		listC.add(child);
//		
//		Map<String,Object> subMap = new HashMap<String,Object>();
//		List<Map<String,Object>> subButton = new ArrayList<Map<String,Object>>();
//		Map<String,Object> sub1 = new HashMap<String,Object>();
//		sub1.put("type", "click");
//		sub1.put("name", "我的作品");
//		sub1.put("key", "myPhotos");
//		subButton.add(sub1);
//		sub1 = new HashMap<String,Object>();
//		sub1.put("type", "view");
//		sub1.put("name", "更多作品");
//		sub1.put("url", baseUrl+"/wx/allPhotos.do");
//		subButton.add(sub1);
//		subMap.put("name", "作品展示");
//		subMap.put("sub_button", subButton);
//		listC.add(subMap);
//		
//		Map<String,Object> subMap2 = new HashMap<String,Object>();
//		List<Map<String,Object>> subButton2 = new ArrayList<Map<String,Object>>();
//		Map<String,Object> sub2 = new HashMap<String,Object>();
//		sub2.put("type", "view");
//		sub2.put("name", "大赛介绍");
//		sub2.put("url", CommonUtils.parseProperties("BASE_URL")+"/introduce.jsp");
//		subButton2.add(sub2);
//		sub2 = new HashMap<String,Object>();
//		sub2.put("type", "view");
//		sub2.put("name", "征稿细则");
//		sub2.put("url", CommonUtils.parseProperties("BASE_URL")+"/rule.jsp");
//		subButton2.add(sub2);
//		subMap2.put("name", "大赛介绍");
//		subMap2.put("sub_button", subButton2);
//		listC.add(subMap2);

		parent.put("button", listC);
		return parent;
	}
	
	
	public static void main(String args[]) throws JsonGenerationException, JsonMappingException, IOException{
//		System.out.println(java.net.URLEncoder.encode("http://42.99.0.141:9888/dyjl_ext/redirectInfo.do","UTF-8"));
		String appId="wxbb68c06d3e54c494";
		String secret="59c8f1b8aae7d207ca011b7803772672";
		
		Map<String,Object> menu = getMenu();
		String URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken(appId,secret);
		String content = CommonUtils.toJsonStr(menu);
		System.out.println(content);
		System.out.println(UrlUtil.doHttpPostJson(URL, content));
		
//		String URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=iNWlocDhR8uBGYlX0wvlru3ayDfSmgxyKdWhlwwEnKDovdm65ILSqU5l2qxPgVgvhduI6lHReIkVhUGbnZ1wsBQzsp5R9RlZZYs58c6Y2bE&type=jsapi";
//		System.out.println(UrlUtil.httpsRequest(URL, "GET", null));
		
	}
	
	

}
