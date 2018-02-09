package com.unionx.wantuo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;

import com.unionx.wantuo.dao.StudentSchoolMapper;

import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentSchool;
import com.unionx.wantuo.service.StudentSchoolService;


@Service("studentSchoolService")
public class StudentSchoolServiceImpl extends BaseServiceImpl<StudentSchool> implements StudentSchoolService {
	
	
	@Autowired
	private StudentSchoolMapper ssm;

	@Override
	public List<PhoneStudent> selectOrganizationStudent(String organizationAccounts,String name) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("organizationAccounts", organizationAccounts);
		map.put("name", name);
		return this.ssm.selectOrganizationStudent(map);
	}

	@Override
	public int phoneDeleteStudentSchool(String studentId,String organizationAccounts) {
		return this.ssm.phoneDeleteStudentSchool(studentId,organizationAccounts);
	}

}
