package com.unionx.wantuo.dao;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Teacher;

public interface TeacherMapper extends BaseDao<Teacher> {

    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    /**
     * 根据登入账号查询教师信息
     * @param LoginAccounts
     * @return
     */
    Teacher findByLoginAccount(String LoginAccounts);
}