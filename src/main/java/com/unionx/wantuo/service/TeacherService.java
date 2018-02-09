package com.unionx.wantuo.service;

import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;

public interface TeacherService {
	
	/**
	 * 根据机构老师账号查询老师信息
	 * @param LoginAccounts 登入账号
	 * @return
	 */
	Teacher selectByLoginAccounts(String LoginAccounts);
	
	/**
	 * 添加机构老师
	 * @param teacher
	 * @return
	 */
	int saveTeacher(Teacher teacher);
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(获取机构下的班主任) 
	 * @param @param t
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Pager<Teacher> 返回类型 
	 * @throws
	 */
	public Pager<Teacher> queryByList(Teacher t,Condition c);
	
	public Integer update(Teacher t);
}
