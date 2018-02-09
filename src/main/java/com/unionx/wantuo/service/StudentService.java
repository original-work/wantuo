package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.Student;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface StudentService {
	
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
	 * @Title: save 
	 * @Description: TODO(保存学员) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Student> 返回类型 
	 * @throws
	 */
	public Integer save(Student s);

	/**
	 * @Title: getById 
	 * @Description: TODO(获取单个学生信息) 
	 * @param @param studentId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Student 返回类型 
	 * @throws
	 */
	public Student getById(String studentId,String organization);
	
	/**
	 * @Title: update 
	 * @Description: TODO(修改学生信息) 
	 * @param @param s 设定文件 
	 * @author abner
	 * @return void 返回类型 
	 * @throws
	 */
	public Integer update(Student s);

	/**
	 * @Title: queryByList 
	 * @Description: TODO(机构年级报表) 
	 * @param @param s
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Pager<Student> 返回类型 
	 * @throws
	 */
	public Pager<Student> queryByList(Student s, Condition c);

	/**
	 * @Title: queryById 
	 * @Description: TODO(根据学生id查询学生) 
	 * @param @param studentId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Student 返回类型 
	 * @throws
	 */
	public Student queryById(String studentId);
}
