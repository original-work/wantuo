package com.unionx.wantuo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.StudentMapper;
import com.unionx.wantuo.model.Student;
import com.unionx.wantuo.service.StudentService;

@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
	
	
	@Autowired
	private StudentMapper sm;

	/**
	 * 根据家长查询学生
	 */
	@Override
	public List<Student> select(Student s) {
		return sm.select(s);
	}

	@Override
	public Student getById(String studentId,String organization) {
		return sm.getById(studentId,organization);
	}

	@Override
	public Student queryById(String studentId) {
		return sm.queryById(studentId);
	}
}
