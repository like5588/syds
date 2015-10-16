package com.ty.photography.monitor;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.photography.model.Dictionary;
import com.ty.photography.service.IDictionaryService;

@WebListener
public class TelecomInfolistener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-database.xml");
		IDictionaryService dictionaryService = (IDictionaryService)ctx.getBean("dictionaryServiceImpl");
		//抽奖概率初始化
		LotteryInfo lotteryInfo = LotteryInfo.getInstance(dictionaryService);
		lotteryInfo.init();
		//缓存网站信息
		TelecomInfo telecomInfo = new TelecomInfo(ctx);
		List<Dictionary> telecomArea = dictionaryService.findByType("telecom_area");
		telecomInfo.setTelecomArea(telecomArea);
		Thread t = new Thread(telecomInfo);
		t.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
