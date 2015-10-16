package com.ty.photography.model;


public class AccessToken {
	
	private String access_token;
	private int expires_in;
	private long createTime;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 是否过期
	 * @return
	 */
	public boolean isExpires(){
		if(System.currentTimeMillis()-createTime<expires_in*1000){
			return false;
		}else{
			return true;
		}
	}

}
