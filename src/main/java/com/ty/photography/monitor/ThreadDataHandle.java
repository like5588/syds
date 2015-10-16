package com.ty.photography.monitor;

import com.ty.photography.service.IPhotoDownloadService;

public class ThreadDataHandle extends Thread{
	
	private IPhotoDownloadService photoDownloadService;

	public void setPhotoDownloadService(IPhotoDownloadService photoDownloadService) {
		this.photoDownloadService = photoDownloadService;
	}
	
	public ThreadDataHandle(){
	}
	
	public void run() {
		while (true) {
			try {
				process();
			} catch (Exception e) {
				System.err.println("Logger Worker Runing Exception:" 
						+ e.getMessage());
			}
			try {
				Thread.sleep(300000);	//5分钟
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	private void process() throws Exception {
		photoDownloadService.downLoadPhotos();
		
	}
	

}
