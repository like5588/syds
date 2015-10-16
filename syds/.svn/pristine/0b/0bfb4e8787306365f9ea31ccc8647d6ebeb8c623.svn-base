package com.ty.photography.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.model.JudgeLogon;

@WebFilter(filterName="sysAuthenticateFilter",urlPatterns={"/sys/*"})
public class SysAuthenticateFilter implements Filter{

	private static String[] path = null;
	private static String basePath;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		basePath = CommonUtils.parseProperties("BASE_URL");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		if(!containUrl(httpRequest.getServletPath())){
			HttpSession session = httpRequest.getSession();
			JudgeLogon judgeLogon = (JudgeLogon)session.getAttribute("judge");
			if(judgeLogon==null || judgeLogon.equals("")){
				//判断ajax请求
    			if(httpRequest.getHeader("x-requested-with")!=null  
    	            && httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
    					//是ajax请求，则返回个消息给前台
        	            PrintWriter printWriter = response.getWriter();   
        	            printWriter.print("{\"sessionState\":\"timeout\",\"redirect\":\""+basePath+"/pingxuan.jsp\"}");   
        	            printWriter.flush();   
        	            printWriter.close();   
    	        }else{
					String redirectPath = basePath + "/pingxuan.jsp";
					((HttpServletResponse)response).sendRedirect(redirectPath);
    	        }
			}else{
				chain.doFilter(request, response);
			}
//		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean containUrl(String url){
		for(String other : path){
			if(url.equals(other)){
				return true;
			}
		}
		return false;
	}

}
