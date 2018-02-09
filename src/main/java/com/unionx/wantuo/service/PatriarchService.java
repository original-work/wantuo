package com.unionx.wantuo.service;

import com.unionx.wantuo.model.Patriarch;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;

public interface PatriarchService {
	/**
	 * 根据家长帐号查询家长信息
	 * @param loginAccounts
	 * @return
	 */
	public Patriarch findByAccounts(String loginAccounts);
	
	/**
	 * 家长注册
	 * @param patriarch
	 * @return
	 */
	public int ParentRegister(Patriarch patriarch);
	
	/**
	 * 家长个人信息维护
	 * @param patriarch
	 * @return
	 */
	public int updateByLoginAccounts(Patriarch patriarch);
	
	public Pager<Patriarch> queryByList(Patriarch p,Condition c);
	
	/**
	 * 后台管理根据家长帐号修改家长信息
	 * @return
	 */
	public int updatePatriarch(String loginAccounts,Integer status);

	
	public int updatePatriarchId(String id, Integer status);
}
