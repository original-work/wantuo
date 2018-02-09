package com.unionx.wantuo.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentSchool;


public interface StudentSchoolMapper extends BaseDao<StudentSchool>{

	public List<PhoneStudent> selectOrganizationStudent(Map<Object,Object> map);

	public int phoneDeleteStudentSchool(@Param("studentId") String studentId,@Param("organizationAccounts") String organizationAccounts);
}