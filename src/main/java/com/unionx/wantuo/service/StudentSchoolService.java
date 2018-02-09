package com.unionx.wantuo.service;


import java.util.List;

import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentSchool;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface StudentSchoolService {
	
	
	/**
	 * @Title: save 
	 * @Description: TODO(保存学员学校关系) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Student> 返回类型 
	 * @throws
	 */
	public Integer save(StudentSchool s);
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(手机端获取机构下的学员) 
	 * @param @return 设定文件 
	 * @author abner
	 * @return Pager<StudentSchool> 返回类型 
	 * @throws
	 */
	public Pager<StudentSchool> queryByList(StudentSchool s,Condition c);
	
	/**
	 * @Title: selectOrganizationStudent 
	 * @Description: TODO(获取机构下全部学生) 
	 * @param @param organizationAccounts
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Student> 返回类型 
	 * @throws
	 */
	public List<PhoneStudent> selectOrganizationStudent(String organizationAccounts,String name);

	/**
	 * @Title: phoneDeleteStudentSchool 
	 * @Description: TODO(删除学生与机构关系) 
	 * @param @param studentId
	 * @param @param organizationAccounts
	 * @param @return 设定文件 
	 * @author abner
	 * @return int 返回类型 
	 * @throws
	 */
	public int phoneDeleteStudentSchool(String studentId,String organizationAccounts);
}
