package com.unionx.wantuo.dao;

import java.util.List;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentClass;

public interface StudentClassMapper extends BaseDao<StudentClass>{
	
	public void saveStudentClass(String studentId,String classId);
	
	public Integer deleteStudentClass(String studentId,String classId);
	
	public List<PhoneStudent> selectClassStudent(String classId);
}