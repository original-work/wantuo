package com.unionx.wantuo.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.ClassMapper;
import com.unionx.wantuo.model.ClassModel;
import com.unionx.wantuo.service.ClassService;


@Service("classService")
public class ClassServiceImpl extends BaseServiceImpl<ClassModel> implements ClassService {
	
	@Autowired
	private ClassMapper cm;

	@Override
	public Integer updateNum(int i,String classId) {
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		map.put("i",i);
		map.put("classId",classId);
		return cm.updateNum(map);
	}
	
	
}
