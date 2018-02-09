package com.unionx.wantuo.service;

import com.unionx.wantuo.model.School;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;


public interface SchoolService {
	
	public Integer save(School m);
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(分页查询) 
	 * @param @param m
	 * @param @param c
	 * @author abner
	 * @return Pager<School> 返回类型 
	 * @throws
	 */
	public Pager<School> queryByList(School m,Condition c);
	
	public Integer update(School m);
	
	public School getById(School m);
	
	/**
	 * 根据id删除学校，假删除
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * 添加学校
	 * @param school
	 * @return
	 */
	public int saveSchool(School school);
	
	/**
	 * 根据学校id修改学校信息
	 * @param school
	 * @return
	 */
	public int updateSchoolById(School school);
}
