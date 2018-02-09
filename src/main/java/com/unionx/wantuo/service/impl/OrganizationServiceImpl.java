package com.unionx.wantuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.OrganizationMapper;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService{
	@Autowired
	private OrganizationMapper ozMapper;
	
	@Override
	public int saveOrganizationBySelected(Organization oz) {
		// TODO Auto-generated method stub
		return ozMapper.insertSelective(oz);
	}

	@Override
	public Organization selectByLoginAccounts(String LoginAccounts) {
		// TODO Auto-generated method stub
		return ozMapper.findByLoginAccounts(LoginAccounts);
	}
	
	/**
	 * 手机端机构预约+1
	 */
	@Override
	public void phoneUpdateOrder(Organization o) {
		ozMapper.phoneUpdateOrder(o);
	}

	@Override
	public int updateByLoginAccounts(Organization o) {
		// TODO Auto-generated method stub
		return ozMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public int saveOrganzation(Organization o) {
		// TODO Auto-generated method stub
		return ozMapper.insertSelective(o);
	}

	@Override
	public int updateOrganzation(Organization o) {
		// TODO Auto-generated method stub
		return ozMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public Organization selectById(Integer id) {
		// TODO Auto-generated method stub
		return ozMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateDingWer(String x, String y, String id) {
		// TODO Auto-generated method stub
		return ozMapper.updateById(x, y, id);
	}

	@Override
	public Organization selectDetails(String loginAccounts) {
		// TODO Auto-generated method stub
		return ozMapper.findByDetails(loginAccounts);
	}
	
}
