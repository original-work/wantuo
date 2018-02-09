package com.unionx.wantuo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.OrganizationMapper;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.service.CheckStatusService;

@Service
public class CheckStatusServiceImpl extends BaseServiceImpl<Organization> implements CheckStatusService{

	@Autowired
	private OrganizationMapper organizationMapper;
	/**
	 * 审核
	 */
	@Override
	public void checkSuuccess(Map<Object, Object> parmMap) {
		// TODO Auto-generated method stub
		organizationMapper.checkSuccess(parmMap);
	}
	/**
	 * 认证
	 */
	@Override
	public void attestationSuccess(Map<Object, Object> parmMap) {
		organizationMapper.attestationSuccess(parmMap);
		
	}
	/**
	 * 逻辑删除
	 */
	@Override
	public void deleteData(int id) {
		
		organizationMapper.daleteData(id);
	}
	/**
	 * 授权
	 */
	@Override
	public void warrantySuccess(Map<Object, Object> parmMap) {
		// TODO Auto-generated method stub
		organizationMapper.warrantySuccess(parmMap);
	}
	@Override
	public void delectAll(String loginAccounts) {
		// TODO Auto-generated method stub
		organizationMapper.delectAllClass(loginAccounts);
		organizationMapper.delectAllCollect(loginAccounts);
		organizationMapper.delectAllEvaluate(loginAccounts);
		organizationMapper.delectAllla(loginAccounts);
		organizationMapper.delectAllpla(loginAccounts);
		organizationMapper.delectAllStudentSchool(loginAccounts);
		organizationMapper.delectAllTeacher(loginAccounts);
	}
}
