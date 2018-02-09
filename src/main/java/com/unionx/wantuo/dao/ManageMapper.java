package com.unionx.wantuo.dao;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Manage;



public interface ManageMapper extends BaseDao<Manage>{
	
	public void updateStatus(String ids,String fields);
	
	public int updateByPrimaryKeySelective(Manage m);
	
	/**
	 * 添加管理员
	 * @param m
	 * @return
	 */
	public int insertSelective(Manage m);
	
	/**
	 * 根据管理员账号查询管理员信息
	 * @param m
	 * @return
	 */
	public Manage findByLoginAccounts(String login_accounts);
}