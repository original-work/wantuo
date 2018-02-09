package com.unionx.wantuo.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.CollectMapper;
import com.unionx.wantuo.model.Collect;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.service.CollectService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.EntityConvertMap;
import com.unionx.wantuo.utils.Pager;

@Service("collectService")
public class CollectServiceImpl extends BaseServiceImpl<Collect> implements CollectService {
	
	@Autowired
	private CollectMapper cm;

	@Override
	public Integer save(Collect o) {
		return cm.insertSelective(o);
	}

	@Override
	public int deleteColect(String patriarchAccounts, String organizationAccounts) {
		return cm.unfavorite(patriarchAccounts, organizationAccounts);
	}

	/**
	 * 根据家长帐号，学校帐号查询是否关注
	 */
	@Override
	public Integer select(Collect c) {
		return cm.select(c);
	}

	@Override
	public Pager<Organization> queryList(Collect o, Condition c) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		Pager<Organization> pager = new Pager<Organization>();
		Integer count = count(o,c);
		pager.setCount(count);
		c.setRowCount(count);
		try {
			paramMap = EntityConvertMap.convertBean(o,paramMap);
			paramMap = EntityConvertMap.convertBean(c,paramMap);
		} catch (Exception e) {
			
		}
//		System.out.print(paramMap);
		pager.setList(cm.queryList(paramMap));
		return pager;
	}

	@SuppressWarnings("unchecked")
	public Integer count(Collect t,Condition c) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		try {
			paramMap = EntityConvertMap.convertBean(t,paramMap);
			paramMap = EntityConvertMap.convertBean(c,paramMap);
		} catch (Exception e) {
			
		}
		return cm.count(paramMap);
	}
	
}
