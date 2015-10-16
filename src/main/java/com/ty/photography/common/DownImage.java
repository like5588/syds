package com.ty.photography.common;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 下载图片
 * @author wits
 *
 */
public class DownImage implements Command{
	private Logger log = LoggerFactory.getLogger(DownImage.class);
	
	private MobileUtils mobileUtils;
	private String media_id;
	private int i=0;
	
	public DownImage(MobileUtils mobileUtils,String media_id){
		this.mobileUtils = mobileUtils;
		this.media_id = media_id;
	}
	
	public boolean action(){
		return action(false);
	}
	
	@Override
	public boolean action(boolean stat){
		boolean result=false;
		String url = mobileUtils.getMediaUtl()+"?access_token="
				+ mobileUtils.getAccessToken().getAccess_token()+"&media_id="+media_id;
		try {
			String file_path =CommonUtils.parseProperties("file_path");
			String nowStr = CommonUtils.dateToString(new Date());
			File nowDir = new File(file_path+"/compress/"+nowStr+"/");
			if(!nowDir.exists()){
				nowDir.mkdir();
			}
			result = UrlUtil.downLoadFile(url,file_path+"/compress/"+nowStr+"/",media_id);
			if(!result && ++i<3){
				if(stat){
					mobileUtils.setRefresh(false);
				}
				mobileUtils.action(this);
			}
		} catch (ResponseCodeException e) {
			log.error("downLoad image Response Code is "+e.getMessage(),e);
			result  = false;
		} catch (Exception e) {
			log.error("downLoad image has error "+e.getMessage(),e);
			result  = false;
		}
		return result;
	}
	
	public static void main(String args[]){
		WxUtil wxUtil = WxUtil.getInstance();
		DownImage downImage = new DownImage(wxUtil,"SuWOcC_zlg58sTWZoTQkRDCZfgZYJzR7WklRNLxLG-Hxa-0v-oX43HUPTE5OiH-Y");
		downImage.action();
	}

}
