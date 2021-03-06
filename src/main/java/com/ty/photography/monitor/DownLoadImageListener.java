package com.ty.photography.monitor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.photography.service.IPhotoDownloadService;

@WebListener
public class DownLoadImageListener implements ServletContextListener{

	private static byte[] lock = new byte[0];
	private static boolean start = false;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-database.xml");
		IPhotoDownloadService photoDownloadService = (IPhotoDownloadService)ctx.getBean("photoDownloadServiceImpl");
		try {
			ThreadDataHandle handle = new ThreadDataHandle();
			handle.setPhotoDownloadService(photoDownloadService);
			synchronized (lock){
				if (!start) {
					handle.start();
					start = true;
					System.out.println("Worker Runing...");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
