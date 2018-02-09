package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.Activity;

public interface ActivityService {
	/**
	 * 查询所有活动
	 * @param a
	 * @return
	 */
	List<Activity> findAllActivity(Activity a);
	
	int updateBySelect(Activity a);
	
	Activity findById(Integer id);
}
