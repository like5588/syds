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

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.model.UserBindDto;
import com.ty.photography.service.IUserBindInfoService;

@WebFilter(filterName="dxAuthenticateFilter",urlPatterns={"/dx/*"})
public class DXAuthenticateFilter implements Filter{
	
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
		UserBindDto userBindDto = (UserBindDto)session.getAttribute("dx_userBindInfo");
		boolean flag = true;
		String referer = httpRequest.getRequestURL().toString();
		if(userBindDto==null || userBindDto.equals("")){
			Cookie[] cookies = httpRequest.getCookies();
    		if(cookies != null){
    			for(Cookie cookie : cookies){
    				if(cookie.getName().equals("syds_login_id") && cookie.getValue() != null && !cookie.getValue().equals("")){
    					flag = false;
    					String loginId= cookie.getValue();
    					session.setAttribute("sourceId", loginId);
    					userBindDto = userBindInfoServiceImpl.findUserBindDto(loginId, "0", "5");
    					if(userBindDto == null){
    						if(httpRequest.getQueryString() !=null && !httpRequest.getQueryString().equals("")){
    							referer = referer + "?" + httpRequest.getQueryString();
    						}
    						HttpServletResponse httprResponse = (HttpServletResponse)response;
    						httprResponse.sendRedirect(baseUrl+"/register.jsp?p=5&referer="+java.net.URLEncoder.encode(referer,"UTF-8"));
//    						httprResponse.sendRedirect("http://localhost:8080/syds/register.jsp");
    						break;
    					}else{
    						session.setAttribute("dx_userBindInfo", userBindDto);
    						break;
    					}
    				}
    			}
    		}
    		if(flag){
    			//判断ajax请求
    			if(httpRequest.getHeader("x-requested-with")!=null  
    	            && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
    				if(httpRequest.getServletPath().contains("/laud.do")){
    					//是ajax请求，则返回个消息给前台
        	            PrintWriter printWriter = response.getWriter();   
        	            printWriter.print("{\"sessionState\":\"timeout\",\"redirect\":\""+baseUrl+"/teleCom_index.jsp\"}");   
        	            printWriter.flush();   
        	            printWriter.close();   
    				}
    	        }else{
    	        	if(httpRequest.getQueryString() !=null && !httpRequest.getQueryString().equals("")){
    					referer = referer + "?" + httpRequest.getQueryString();
    				}
    				HttpServletResponse httprResponse = (HttpServletResponse)response;
    				httprResponse.sendRedirect("http://www.hicdma.com/mh/views/pages/login.jsp?redirect="+baseUrl+"/teleCom_index.jsp");
    	        }
    		}else{
    			chain.doFilter(request, response);
    		}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
