package com.ty.photography.monitor;

import redis.clients.jedis.Jedis;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.service.IPhotoInfoService;

public class UpdateLaud extends Thread{
	
	private IPhotoInfoService photoInfoService;
	
	private static final Jedis JEDIS = new Jedis(CommonUtils.redisProperties("redis_host"),Integer.parseInt(CommonUtils.redisProperties("redis_port")));

	public UpdateLaud(IPhotoInfoService photoInfoService){
		this.photoInfoService = photoInfoService;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				process();
			} catch (Exception e) {
				System.err.println(" get telecomInfo Runing Exception:" 
						+ e.getMessage());
			}
			try {
				Thread.sleep(600000);	//10分钟
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	private void process() throws Exception {
		photoInfoService.updateLaud(JEDIS);
	}

}
