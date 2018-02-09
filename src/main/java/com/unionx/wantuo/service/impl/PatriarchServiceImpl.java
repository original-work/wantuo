package com.unionx.wantuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.PatriarchMapper;
import com.unionx.wantuo.model.Patriarch;
import com.unionx.wantuo.service.PatriarchService;

@Service
public class PatriarchServiceImpl extends BaseServiceImpl<Patriarch> implements PatriarchService{
	@Autowired
	private PatriarchMapper phMapper;
	
	@Override
	public Patriarch findByAccounts(String loginAccounts) {
		// TODO Auto-generated method stub
		return phMapper.findByLoginAccounts(loginAccounts);
	}

	@Override
	public int ParentRegister(Patriarch patriarch) {
		// TODO Auto-generated method stub
		return phMapper.insertSelective(patriarch);
	}

	@Override
	public int updateByLoginAccounts(Patriarch patriarch) {
		// TODO Auto-generated method stub
		return phMapper.updateByPrimaryKeySelective(patriarch);
	}

	@Override
	public int updatePatriarch(String loginAccounts,Integer status) {
		// TODO Auto-generated method stub
		return phMapper.updateByLoginAccounts(loginAccounts,status);
	}

	@Override
	public int updatePatriarchId(String id, Integer status) {
		return phMapper.updatePatriarchId(id,status);
	}

}
