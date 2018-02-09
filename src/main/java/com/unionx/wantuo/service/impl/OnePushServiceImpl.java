package com.unionx.wantuo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.OnePushMapper;
import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.OnePush;
import com.unionx.wantuo.service.OnePushService;

@Service("onePushService")
public class OnePushServiceImpl extends BaseServiceImpl<OnePush> implements OnePushService {
	
	
	@Autowired
	private OnePushMapper opm;

	@Override
	public int unreadCount(OnePush p) {
		return this.opm.unreadCount(p);
	}

	@Override
	public List<Login> oSelectPushP(String push_login_accounts) {
		return this.opm.oSelectPushP(push_login_accounts);
	}

	@Override
	public List<Login> manageSelectPushO() {
		return this.opm.manageSelectPushO();
	}

	@Override
	public List<Login> manageSelectPushP() {
		return this.opm.manageSelectPushP();
	}

	@Override
	public List<Login> manageSelectPushAll() {
		return this.opm.manageSelectPushAll();
	}
	
}
