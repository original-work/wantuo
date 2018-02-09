package com.unionx.wantuo.dao;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Push;



public interface PushMapper extends BaseDao<Push>{

	public int unreadCount(Push p);
}