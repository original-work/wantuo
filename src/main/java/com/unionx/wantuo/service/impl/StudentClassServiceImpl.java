package com.unionx.wantuo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.StudentClassMapper;
import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentClass;
import com.unionx.wantuo.service.StudentClassService;

@Service("studentClassService")
public class StudentClassServiceImpl extends BaseServiceImpl<StudentClass> implements StudentClassService {
	
	@Autowired
	private StudentClassMapper sc;

	@Override
	public void saveStudentClass(String studentId, String classId) {
		this.sc.saveStudentClass(studentId,classId);
	}

	@Override
	public Integer deleteStudentClass(String studentId, String classId) {
		return this.sc.deleteStudentClass(studentId,classId);
	}

	@Override
	public List<PhoneStudent> selectClassStudent(String classId) {
		return this.sc.selectClassStudent(classId);
	}
}
