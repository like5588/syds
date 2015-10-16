<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="org.apache.log4j.Logger"%>
<%
	Logger log = Logger.getLogger(this.getClass());
	String syds_login_account = request.getParameter("syds_login_account");
	String syds_login_id = request.getParameter("syds_login_id");
	
	log.info("-------sync_login---syds_login_account="+syds_login_account+" ,syds_login_id="+syds_login_id);
	Cookie cookie = new Cookie("syds_login_account",syds_login_account);
	cookie.setDomain(".hicdma.com");
	cookie.setMaxAge(3600);
	cookie.setPath("/");
	response.addCookie(cookie);
	cookie = new Cookie("syds_login_id",syds_login_id);
	cookie.setDomain(".hicdma.com");
	cookie.setMaxAge(3600);
    cookie.setPath("/");
	response.addCookie(cookie);
%>