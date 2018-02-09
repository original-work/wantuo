package com.unionx.wantuo.service;


import com.unionx.wantuo.model.Push;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;


public interface PushService {
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(移动端查询推送记录) 
	 * @param @param m
	 * @param @param c
	 * @author abner
	 * @return Pager<Push> 返回类型 
	 * @throws
	 */
	public Pager<Push> queryByList(Push m,Condition c);
	
	/**
	 * @Title: unreadCount 
	 * @Description: TODO(查询未读条数) 
	 * @param @param m
	 * @param @return 设定文件 
	 * @author abner
	 * @return int 返回类型 
	 * @throws
	 */
	public int unreadCount(Push p);
}
