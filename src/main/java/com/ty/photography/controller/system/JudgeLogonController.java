package com.ty.photography.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.model.JudgeLogon;
import com.ty.photography.service.IJudgeLogonService;

@Controller
public class JudgeLogonController {
	private Logger log = LoggerFactory.getLogger(JudgeLogonController.class);
	@Autowired
	private IJudgeLogonService judgeLogonServiceImpl;
	/**
	 * 登录认证
	 * @param userName
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="logon.do",produces="text/plain;charset=UTF-8;")  
	@ResponseBody
	public String logon(@RequestParam("userName") String userName,@RequestParam("password") String password,
			@RequestParam("randVal") String randVal,HttpServletRequest request,HttpServletResponse response){
		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			if(StringUtils.isBlank(userName) || StringUtils.isBlank(password) || StringUtils.isBlank(randVal)){
				resultMap.put("result", "3"); //参数为空
			}else{
				String imgRand  = (String)request.getSession().getAttribute("rand");
				if (imgRand == null || !imgRand.equalsIgnoreCase(randVal)) {
					resultMap.put("result", "2");	//验证码错误
				}else{
					JudgeLogon judgeLogon = judgeLogonServiceImpl.findByAccount(userName);
					if(judgeLogon == null){
						resultMap.put("result", "1");	//账号不存在
					}else{
						if(judgeLogon.getPassword().equals(password)){
							resultMap.put("result", "0");
							request.getSession().setAttribute("judge", judgeLogon);
							if(judgeLogon.getType().equals(0)){
								resultMap.put("redirect", "sys/vetting.do");
							}else if(judgeLogon.getType().equals(5)){
								resultMap.put("redirect", "app/sys/dx_daipingxuan.jsp");
							}else if(judgeLogon.getType().equals(9)){
								resultMap.put("redirect", "sys/main.do");
							}else if(judgeLogon.getType().equals(1)){
								resultMap.put("redirect", "sys/sysManage.do");
							}
						}else{
							resultMap.put("result", "1");	//密码错误
						}
					}
				}
			}
		}catch(Exception e){
			resultMap.put("result", "-1");
			log.error("--- user login has error --",e);
		}
		return CommonUtils.toJsonStr(resultMap);
	}

	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping(value="logout.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().setAttribute("judge", null);
		request.getSession().removeAttribute("judge");
		request.getSession().invalidate();
		return "{\"result\":\"0\"}";
	}
	
	/**
	 * 修改密码
	 * @param password
	 * @param newPassword
	 * @param confirm
	 * @return
	 */
	@RequestMapping(value="sys/changePwd.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String changePwd( String password,String newPassword,String confirm,HttpServletRequest request){
		int result = 0 ;
		try{
			if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(newPassword) && StringUtils.isNotBlank(confirm)){
				JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
				if(judgeLogon != null){
					result = judgeLogonServiceImpl.changePwd(judgeLogon.getId(), password, newPassword, confirm);
				}
			}else{
				result = 4 ;	//参数为空
			}
		}catch(Exception e){
			log.info("---user change password has error---",e);
			result = -1 ;
		}
		
		return "{\"result\":"+result+"}";
	}
	/**
	 * 查看个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sys/judgeInfo.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String judgeInfo(HttpServletRequest request){
		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			resultMap.put("petname", judgeLogon.getPetName());
			resultMap.put("mobile", judgeLogon.getMobile());
			resultMap.put("email", judgeLogon.getEmail());
			resultMap.put("dept", judgeLogon.getDept());
			resultMap.put("comment", judgeLogon.getComment());
			resultMap.put("result", "0");
		}catch(Exception e){
			log.info("---get judgeInfo has error---");
			resultMap.put("result", "-1");
		}
		return CommonUtils.toJsonStr(resultMap);
	}
	/**
	 * 更新信息
	 * @param petname
	 * @param mobile
	 * @param email
	 * @param dept
	 * @return
	 */
	@RequestMapping(value="sys/saveJudgeInfo.do",produces="text/plain;charset=UTF-8;") 
	@ResponseBody
	public String saveJudgeInfo(String petname,String mobile,String email,String dept,String comment,HttpServletRequest request){
		try{
			JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
			judgeLogon.setPetName(petname);
			judgeLogon.setMobile(mobile);
			judgeLogon.setEmail(email);
			judgeLogon.setDept(dept);
			judgeLogon.setComment(comment);
			judgeLogon.setModifyTime(new Date());
			judgeLogonServiceImpl.updateJudgeInfo(judgeLogon);
			request.getSession().setAttribute("judge", judgeLogon);
			return "{\"result\":0,\"petname\":\""+petname+"\"}";
		}catch(Exception e){
			log.info("---saveJudgeInfo has error---",e);
			return "{\"result\":1}";
		}
		
	}
	
	@RequestMapping(value="sys/sysManage.do",produces="text/plain;charset=UTF-8;")  
	public String sysManage(HttpServletRequest request,ModelMap model){
		JudgeLogon judgeLogon = (JudgeLogon)request.getSession().getAttribute("judge");
		model.addAttribute("judgeLogon", judgeLogon);
		return "sys/manage_main";
	}
}
