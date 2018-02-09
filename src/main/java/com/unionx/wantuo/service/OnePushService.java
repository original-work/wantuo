package com.unionx.wantuo.service;


import java.util.List;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.OnePush;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;


public interface OnePushService {
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(移动端查询推送记录) 
	 * @param @param m
	 * @param @param c
	 * @author abner
	 * @return Pager<Push> 返回类型 
	 * @throws
	 */
	public Pager<OnePush> queryByList(OnePush p,Condition c);
	
	/**
	 * @Title: unreadCount 
	 * @Description: TODO(查询未读条数) 
	 * @param @param m
	 * @param @return 设定文件 
	 * @author abner
	 * @return int 返回类型 
	 * @throws
	 */
	public int unreadCount(OnePush p);
	
	public Integer save(OnePush p);
	
	public Integer update(OnePush p);
	
	/**
	 * @Title: oSelectPushP 
	 * @Description: TODO(根据机构查询推送的家长帐号) 
	 * @param @param push_login_accounts
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<OnePush> 返回类型 
	 * @throws
	 */
	public List<Login> oSelectPushP(String push_login_accounts);
	
	/**
	 * @Title: manageSelectPushO 
	 * @Description: TODO(后台查询推送全部机构) 
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Login> 返回类型 
	 * @throws
	 */
	public List<Login> manageSelectPushO();
	
	/**
	 * @Title: manageSelectPushP 
	 * @Description: TODO(后台查询推送全部家长) 
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Login> 返回类型 
	 * @throws
	 */
	public List<Login> manageSelectPushP();
	
	/**
	 * @Title: manageSelectPushAll 
	 * @Description: TODO(后台查询推送全部帐号) 
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Login> 返回类型 
	 * @throws
	 */
	public List<Login> manageSelectPushAll();
}
