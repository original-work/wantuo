package com.unionx.wantuo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.PushMapper;
import com.unionx.wantuo.model.Push;
import com.unionx.wantuo.service.PushService;

@Service("pushService")
public class PushServiceImpl extends BaseServiceImpl<Push> implements PushService {
	
	
	@Autowired
	private PushMapper pm;

	@Override
	public int unreadCount(Push p) {
		return this.pm.unreadCount(p);
	}

	
	
}
