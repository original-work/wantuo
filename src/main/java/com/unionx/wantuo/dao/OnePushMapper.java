package com.unionx.wantuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.OnePush;



public interface OnePushMapper extends BaseDao<OnePush>{

	public int unreadCount(OnePush p);

	public List<Login> oSelectPushP(@Param("push_login_accounts") String push_login_accounts);

	public List<Login> manageSelectPushO();

	public List<Login> manageSelectPushP();

	public List<Login> manageSelectPushAll();
}