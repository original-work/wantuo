package com.unionx.wantuo.service;


import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;


public interface ManageService {
	
	/**
	 * @Title: save 
	 * @Description: TODO(保存管理员信息) 
	 * @param @param m
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer save(Manage m);
	
	/** 
	 * @Title: queryByList 
	 * @Description: TODO(查询管理员列表) 
	 * @param @param m
	 * @param @param c
	 * @author abner
	 * @return Pager<Manage> 返回类型 
	 * @throws
	 */
	public Pager<Manage> queryByList(Manage m,Condition c);
	
	/**
	 * @Title: update 
	 * @Description: TODO(修改管理员信息) 
	 * @param @param m
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer update(Manage m);

	public void updateStatus(String ids,String fields);
	
	/**
	 * @Title: getById 
	 * @Description: TODO(获取管理员信息) 
	 * @param @param m
	 * @author abner
	 * @return Manage 返回类型 
	 * @throws
	 */
	public Manage getById(Manage m);
	
	/**
	 * 根据id修改管理员信息
	 * @param m
	 * @return
	 */
	public int updateById(Manage m);
	
	/**
	 * 添加管理员
	 * @param m
	 * @return
	 */
	public int saveManage(Manage m);
	
	/**
	 * 根据管理员账号查询管理员信息
	 * @param m
	 * @return
	 */
	public Manage findByLogin(String login_accounts);
}
