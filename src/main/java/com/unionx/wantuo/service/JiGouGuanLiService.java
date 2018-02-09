package com.unionx.wantuo.service;

import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;

public interface JiGouGuanLiService {

	public Pager<Organization> queryByList(Organization organization,Condition condition);
	/**
	 * web
	 * @param organization
	 * @param condition
	 * @return
	 */
	public Pager<Organization> queryByListWeb(Organization organization,Condition condition);
}
