package com.ty.photography.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.JudgeLogon;

@Repository
public interface JudgeLogonMapper {
	/**
	 * 根据账号查询
	 * @param account
	 * @return
	 */
	public JudgeLogon findByAccount(String account);
	/**
	 * 根据id获取账号信息
	 * @param findById
	 * @return
	 */
	public JudgeLogon findById(String findById); 
	/**
	 * 修改新密码
	 * @param id
	 * @param pwd
	 * @param newPwd
	 */
	public void updatePwd(Map<String,String> paramMap);
	/**
	 * 更新信息
	 * @param judgeLogon
	 */
	public void updateById(JudgeLogon judgeLogon);
	/**
	 * 条件查询评委
	 * @param judgeLogon
	 * @return
	 */
	public List<JudgeLogon> queryByConstraint(JudgeLogon judgeLogon);
}
