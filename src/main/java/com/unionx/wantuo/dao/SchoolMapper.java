package com.unionx.wantuo.dao;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.School;

public interface SchoolMapper extends BaseDao<School>{
	/**
	 * 根据id修改学校是否可用
	 * @param id 学校id
	 */
	public void updateById(@Param("id") String id);
	
	/**
	 * 添加学校
	 * @param schoole
	 * @return
	 */
	public int insertSelective(School school);
	
	/**
	 * 根据id修改学校信息
	 * @param school
	 * @return
	 */
	public int updateByPrimaryKeySelective(School school);
}