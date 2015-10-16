package com.ty.photography.service;

import com.ty.photography.model.JudgeLogon;

public interface IJudgeLogonService {
	/**
	 * 根据账号获取账户信息
	 * @param account
	 * @return
	 */
	public JudgeLogon findByAccount(String account);
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @param newPassword
	 * @param confirm
	 * @return
	 */
	public int changePwd(String id, String password,String newPassword,String confirm);
	/**
	 * 更新信息
	 * @param judgeLogon
	 */
	public void updateJudgeInfo(JudgeLogon judgeLogon);
}
