package com.unionx.wantuo.service;

import java.util.Map;

public interface CheckStatusService {

	/**
	 * 审批
	 */
	public void checkSuuccess(Map<Object, Object> parmMap);
	
	/**
	 * 认证
	 */
	public void attestationSuccess(Map<Object, Object> parmMap);
	
	/**
	 * 逻辑删除
	 */
	public void deleteData(int id);
	/**
	 * 审批
	 */
	public void warrantySuccess(Map<Object, Object> parmMap);
	
	/*删除所有*/
	public void delectAll(String loginAccounts);
}
