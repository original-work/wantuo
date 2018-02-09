package com.unionx.wantuo.dao;

import java.util.Map;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.ClassModel;

public interface ClassMapper extends BaseDao<ClassModel>{

	public Integer updateNum(Map<Object,Object> map);
}