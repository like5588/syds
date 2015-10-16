package com.ty.photography.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.model.UserBindInfo;
import com.ty.photography.service.IUserBindInfoService;

@WebFilter(filterName="yxAuthenticateFilter",urlPatterns={"/yx/*"})
public class YXAuthenticateFilter implements Filter{
	
	private IUserBindInfoService userBindInfoServiceImpl;
	private String baseUrl;
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
		UserBindInfo userBindInfo = (UserBindInfo)session.getAttribute("yx_userBindInfo");
		if(userBindInfo==null || userBindInfo.equals("")){
			String openid = httpRequest.getParameter("openid");
			String userSource = httpRequest.getParameter("userSource");
			String competitionType = httpRequest.getParameter("competitionType");
			if(StringUtils.isNotBlank(openid) && StringUtils.isNotBlank(userSource) && StringUtils.isNotBlank(competitionType)){
				userBindInfo = userBindInfoServiceImpl.findUserBindInfo(openid, userSource, competitionType);
				if(userBindInfo == null){
					String referer = httpRequest.getRequestURL().toString();
					if(httpRequest.getQueryString() !=null && !httpRequest.getQueryString().equals("")){
						referer = referer + "?" + httpRequest.getQueryString();
					}
					HttpServletResponse httprResponse = (HttpServletResponse)response;
					httprResponse.sendRedirect(baseUrl+"/app/wx_new_user.jsp?openid="+openid+
							"&userSource="+userSource+"&competitionType="+competitionType+"&referer="+java.net.URLEncoder.encode(referer,"UTF-8"));
				}else{
					session.setAttribute("yx_userBindInfo", userBindInfo);
					chain.doFilter(request, response);
				}
			}else{
				((HttpServletResponse)response).sendRedirect(baseUrl+"/app/error.jsp");
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
