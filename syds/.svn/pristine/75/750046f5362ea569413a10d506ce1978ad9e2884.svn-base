package com.ty.photography.common;

import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;

public class VelocityEngine extends org.apache.velocity.app.VelocityEngine{
	
	private static VelocityEngine instance = new VelocityEngine();
	
	private VelocityEngine(){
		super();
		setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                "org.apache.velocity.runtime.log.Log4JLogChute");
		setProperty(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER, "VelocityEngine");
		setProperty(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER_LEVEL, "INFO");
		init();
	}
	
	private VelocityEngine(String className){
		super();
		setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                "org.apache.velocity.runtime.log.Log4JLogChute");
		setProperty(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER, className);
		setProperty(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER_LEVEL, "INFO");
		init();
	}
	
	public static VelocityEngine getInstance(){
		return instance;
	}
	public static VelocityEngine getInstance(String className){
		VelocityEngine ve = new VelocityEngine(className);
		return ve;
	}
}
