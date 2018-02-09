package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentClass;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface StudentClassService {

	public Pager<StudentClass> queryByList(StudentClass s,Condition c);
	
	public void saveStudentClass(String studentId,String classId);
	
	public Integer deleteStudentClass(String studentId,String classId);
	
	/**
	 * @Title: selectClassStudent 
	 * @Description: TODO(获取班级下的学生) 
	 * @param @param classId
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Student> 返回类型 
	 * @throws
	 */
	public List<PhoneStudent> selectClassStudent(String classId);
}
