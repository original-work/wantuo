package com.unionx.wantuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;

import com.unionx.wantuo.model.Student;


public interface StudentMapper extends BaseDao<Student>{
	
	/**
	 * @Title: select 
	 * @Description: TODO(根据家长查询学生) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Student> 返回类型 
	 * @throws
	 */
	public List<Student> select(Student s);
	
	/**
	 * @Title: saveStudent 
	 * @Description: TODO(保存学员) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Student> 返回类型 
	 * @throws
	 */
	public Student saveStudent(Student s);

	/**
	 * @Title: getById 
	 * @Description: TODO(获取单个学生信息) 
	 * @param @param studentId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Student 返回类型 
	 * @throws
	 */
	public Student getById(@Param("studentId") String studentId,@Param("organization") String organization);

	public Student queryById(@Param("studentId") String studentId);
}