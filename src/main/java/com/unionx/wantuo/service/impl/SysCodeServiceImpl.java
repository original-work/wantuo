package com.unionx.wantuo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.SysCodeMapper;
import com.unionx.wantuo.model.SysCode;
import com.unionx.wantuo.service.SysCodeService;

@Service("sysCodeService")
public class SysCodeServiceImpl extends BaseServiceImpl<SysCode> implements SysCodeService {
	
	
	@Autowired
	private SysCodeMapper sc;

	@Override
	public List<SysCode> adminQueryByList(String name,Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		
		return sc.adminSelect(name, pageIndex, pageSize);
	}

	@Override
	public Integer getcount(String name) {
		// TODO Auto-generated method stub
		return sc.adminSelectCount(name);
	}

	@Override
	public int adminDeleteSysCode(String id) {
		// TODO Auto-generated method stub
		return sc.deleteById(id);
	}

	@Override
	public int updateSysCode(SysCode ssc) {
		// TODO Auto-generated method stub
		return sc.updateByPrimaryKeySelective(ssc);
	}

	@Override
	public List<SysCode> findByParent() {
		// TODO Auto-generated method stub
		return sc.selectParent();
	}

	@Override
	public int addSysCode(SysCode c) {
		// TODO Auto-generated method stub
		return sc.insertSelective(c);
	}
	
}
