package com.ty.photography.monitor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.photography.service.IPhotoInfoService;

public class UpdateLaudListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-database.xml");
		IPhotoInfoService photoInfoService = (IPhotoInfoService)ctx.getBean("PhotoInfoServiceImpl");
		UpdateLaud updateLaud = new UpdateLaud(photoInfoService);
		updateLaud.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
