package com.ty.photography.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.dao.JudgeLogonMapper;
import com.ty.photography.model.JudgeLogon;
import com.ty.photography.service.IJudgeLogonService;
@Service
public class JudgeLogonServiceImpl implements IJudgeLogonService{
	@Autowired
	private JudgeLogonMapper judgeLogonMapper;
	
	public JudgeLogon findByAccount(String account){
		return judgeLogonMapper.findByAccount(account);
	}

	public int changePwd(String id, String password,String newPassword,String confirm){
		int result = 0;
		JudgeLogon judgeLogon = judgeLogonMapper.findById(id);
		if(judgeLogon != null){
			if(judgeLogon.getPassword().equals(password)){
				if(newPassword.equals(confirm)){
					Map<String,String> paramMap = new HashMap<String,String>();
					paramMap.put("id", id);
					paramMap.put("password", password);
					paramMap.put("newPassword", newPassword);
					judgeLogonMapper.updatePwd(paramMap);
				}else{
					result = 3;	//密码确认信息不一致
				}
			}else{
				result =2;	//密码错误
			}
		}else{
			result =1;	//用户不存在
		}
		return result;
	}

	@Override
	public void updateJudgeInfo(JudgeLogon judgeLogon) {
		judgeLogonMapper.updateById(judgeLogon);
	}
}
