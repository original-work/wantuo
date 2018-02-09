package com.unionx.wantuo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.TeacherMapper;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.TeacherService;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public Teacher selectByLoginAccounts(String LoginAccounts) {
		// TODO Auto-generated method stub
		return teacherMapper.findByLoginAccount(LoginAccounts);
	}

	@Override
	public int saveTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherMapper.insertSelective(teacher);
	}
	
	
}
