package com.unionx.wantuo.service;

import com.unionx.wantuo.model.Login;

public interface LoginService {
	/**
	 * 登入查询方法
	 * @param loginAccount 登入账号
	 * @param password		密码
	 * @return
	 */
	Login findByAccountAndPws(String loginAccount);
	
	/**
	 * 根据填写信息修改登入信息
	 * @param login
	 * @return
	 */
	int updateLoginBySelected(Login login);
	
	/**
	 * 保存登入信息方法
	 * @param login
	 * @return
	 */
	int saveLoginBySelected(Login login);
	
	/**
	 * 登入查询
	 * @param loginAccount 输入的注册帐号
	 * @return
	 */
	Login findToRegister(String loginAccount,String status);

	
	public Integer update(Login login);

	
	/**
	 * 后台管理根据帐号修改登入信息
	 * @param loginAccounts
	 * @return
	 */
	int updateLogin(String loginAccounts,Integer status);
	
	void deleteByLogin(String loginAccounts);
}
