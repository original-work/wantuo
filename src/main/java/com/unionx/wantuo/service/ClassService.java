package com.unionx.wantuo.service;


import com.unionx.wantuo.model.ClassModel;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface ClassService {
	
	public Integer save(ClassModel c);

	public Pager<ClassModel> queryByList(ClassModel s,Condition c);
	
	public Integer update(ClassModel s);
	
	public Integer updateNum(int i,String classId);

	public Integer getCount(ClassModel cm, Condition c);
}
