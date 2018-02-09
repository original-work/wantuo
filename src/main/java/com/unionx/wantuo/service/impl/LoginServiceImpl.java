package com.unionx.wantuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.LoginMapper;
import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.service.LoginService;

@Service
public class LoginServiceImpl extends BaseServiceImpl<Login> implements LoginService{
	@Autowired
	private LoginMapper loginMapper;

	@Override
	public Login findByAccountAndPws(String loginAccount) {
		// TODO Auto-generated method stub
		return loginMapper.findToLogin(loginAccount);
	}

	@Override
	public int updateLoginBySelected(Login login) {
		// TODO Auto-generated method stub
		return loginMapper.updateByPrimaryKeySelective(login);
	}

	@Override
	public int saveLoginBySelected(Login login) {
		// TODO Auto-generated method stub
		return loginMapper.insertSelective(login);
	}

	@Override
	public Login findToRegister(String loginAccount,String status) {
		// TODO Auto-generated method stub
		return loginMapper.findByloginAccount(loginAccount,status);
	}

	@Override
	public int updateLogin(String loginAccounts,Integer status) {
		// TODO Auto-generated method stub
		return loginMapper.updateByLoginAcctounts(loginAccounts,status);
	}

	@Override
	public void deleteByLogin(String loginAccounts) {
		// TODO Auto-generated method stub
		loginMapper.deleteByLoginAccount(loginAccounts);
	}
}
