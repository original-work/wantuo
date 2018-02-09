package com.unionx.wantuo.service;


import com.unionx.wantuo.model.Collect;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface CollectService {
	
	public Integer save(Collect o);
	
	public Pager<Collect> queryByList(Collect o,Condition c);
	
	public Pager<Organization> queryList(Collect o,Condition c);
	/**
	 * 取消机构收藏
	 * @param patriarchAccounts 家长 登入账号
	 * @param organizationAccounts 机构账号
	 * @return
	 */
	public int deleteColect(String patriarchAccounts,String organizationAccounts);
	
	/**
	 * @Title: select 
	 * @Description: TODO(根据家长帐号，学校帐号查询是否关注) 
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer select(Collect c);
}
