package com.ty.photography.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.model.UserBindDto;
import com.ty.photography.service.IUserBindInfoService;

@WebFilter(filterName="mhAuthenticateFilter",urlPatterns={"/mh/*"})
public class CountryAuthenticateFilter implements Filter{
	private Logger log = LoggerFactory.getLogger(CountryAuthenticateFilter.class);
	
	private IUserBindInfoService userBindInfoServiceImpl;
	private static String baseUrl;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();  
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
		userBindInfoServiceImpl = (IUserBindInfoService)ctx.getBean("userBindInfoServiceImpl");
		baseUrl = CommonUtils.parseProperties("BASE_URL");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		UserBindDto userBindDto = (UserBindDto)session.getAttribute("mh_userBindInfo");
		boolean flag = true;
		String referer = httpRequest.getRequestURL().toString();
		if(userBindDto==null || userBindDto.equals("")){
			log.info("------------userBindDto is null");
			Cookie[] cookies = httpRequest.getCookies();
    		if(cookies != null){
    			for(Cookie cookie : cookies){
    				if(cookie.getName().equals("syds_login_id") && cookie.getValue() != null && !cookie.getValue().equals("")){
    					flag = false;
    					String loginId= cookie.getValue();
    					log.info("------------syds_login_id is "+ loginId);
    					session.setAttribute("sourceId", loginId);
    					userBindDto = userBindInfoServiceImpl.findUserBindDto(loginId, "0", "9");
    					if(userBindDto == null){
    						if(httpRequest.getQueryString() !=null && !httpRequest.getQueryString().equals("")){
    							referer = referer + "?" + httpRequest.getQueryString();
    						}
    						HttpServletResponse httprResponse = (HttpServletResponse)response;
    						httprResponse.sendRedirect(baseUrl+"/register.jsp?p=9&referer="+java.net.URLEncoder.encode(referer,"UTF-8"));
//    						httprResponse.sendRedirect("http://localhost:8080/syds/register.jsp");
    						break;
    					}else{
    						log.info("------------session.setAttribute mh_userBindInfo");
    						session.setAttribute("mh_userBindInfo", userBindDto);
    						break;
    					}
    				}
    			}
    		}
    		if(flag){
    			//判断ajax请求
    			if(httpRequest.getHeader("x-requested-with")!=null  
    	            && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
    				if(httpRequest.getServletPath().contains("/laud.do") || httpRequest.getServletPath().contains("/lottery.do")){
    					//是ajax请求，则返回个消息给前台
        	            PrintWriter printWriter = response.getWriter();   
        	            printWriter.print("{\"sessionState\":\"timeout\",\"redirect\":\""+baseUrl+"/mh_index.jsp\"}");   
        	            printWriter.flush();   
        	            printWriter.close();   
    				}
    	        }else{
    	        	log.info("------------httprResponse.sendRedirect to: http://www.hicdma.com/mh/views/pages/login.jsp");
    	        	if(httpRequest.getQueryString() !=null && !httpRequest.getQueryString().equals("")){
    					referer = referer + "?" + httpRequest.getQueryString();
    				}
    				HttpServletResponse httprResponse = (HttpServletResponse)response;
    				httprResponse.sendRedirect("http://www.hicdma.com/mh/views/pages/login.jsp?redirect="+baseUrl+"/mh_index.jsp");
    	        }
    		}else{
    			log.info("------------flag is false to doFilter");
    			chain.doFilter(request, response);
    		}
		}else{
			log.info("------------userBindDto is OK");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
