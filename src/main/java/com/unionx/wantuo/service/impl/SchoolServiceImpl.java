package com.unionx.wantuo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.SchoolMapper;
import com.unionx.wantuo.model.School;
import com.unionx.wantuo.service.SchoolService;

@Service("schoolService")
public class SchoolServiceImpl extends BaseServiceImpl<School> implements SchoolService {
	
	@Autowired
	private SchoolMapper sm;

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		sm.updateById(id);
	}

	@Override
	public int saveSchool(School school) {
		// TODO Auto-generated method stub
		return sm.insertSelective(school);
	}

	@Override
	public int updateSchoolById(School school) {
		// TODO Auto-generated method stub
		return sm.updateByPrimaryKeySelective(school);
	}
	
}
