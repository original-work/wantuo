package com.unionx.wantuo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.ManageMapper;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.service.ManageService;

@Service("manageService")
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {
	
	
	@Autowired
	private ManageMapper mm;

	@Override
	public void updateStatus(String ids, String fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateById(Manage m) {
		// TODO Auto-generated method stub
		return mm.updateByPrimaryKeySelective(m);
	}

	@Override
	public int saveManage(Manage m) {
		// TODO Auto-generated method stub
		return mm.insertSelective(m);
	}

	@Override
	public Manage findByLogin(String login_accounts) {
		// TODO Auto-generated method stub
		return mm.findByLoginAccounts(login_accounts);
	}
	
}
