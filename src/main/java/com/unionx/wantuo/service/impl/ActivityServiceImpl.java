package com.unionx.wantuo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.dao.ActivityMapper;
import com.unionx.wantuo.model.Activity;
import com.unionx.wantuo.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper am;
	@Override
	public List<Activity> findAllActivity(Activity a) {
		// TODO Auto-generated method stub
		return am.selectAll(a);
	}
	
	@Override
	public int updateBySelect(Activity a) {
		// TODO Auto-generated method stub
		return am.updateByPrimaryKeySelective(a);
	}

	@Override
	public Activity findById(Integer id) {
		// TODO Auto-generated method stub
		return am.selectByPrimaryKey(id);
	}

}
