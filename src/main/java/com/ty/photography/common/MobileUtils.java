package com.ty.photography.common;

import com.ty.photography.model.AccessToken;
/**
 * 移动端工具接口
 * @author liyan
 *
 */
public interface MobileUtils {
	
	public String getMediaUtl();
	
	public AccessToken getAccessToken();
	
	public void setRefresh(boolean refresh);
	
	public void action(Command command);

}
