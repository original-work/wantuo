package com.unionx.wantuo.dao;

import java.util.List;

import com.unionx.wantuo.model.Activity;


public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    List<Activity> selectAll(Activity record);
}