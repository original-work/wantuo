package com.unionx.wantuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Trace;
import com.unionx.wantuo.model.TraceAverage;
import com.unionx.wantuo.model.TraceMap;
import com.unionx.wantuo.model.TraceShow;

public interface TraceMapper  extends BaseDao<Trace> {
	
	/**
	 * @Title: selectSubjectGrade 
	 * @Description: TODO(手机端根据学科月份查询学生成绩曲线图) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Trace> 返回类型 
	 * @throws
	 */
	List<TraceMap> selectSubjectGrade(Trace t);
	
	/**
	 * @Title: phoneSelectShow 
	 * @Description: TODO(手机端根据月份查询行为曲线、学习曲线曲线图) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Trace> 返回类型 
	 * @throws
	 */
	List<TraceShow> phoneSelectShow(Trace t);
	
	/**
	 * @Title: phoneAverage 
	 * @Description: TODO(查询所有学生平均评价) 
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<TraceShow> 返回类型 
	 * @throws
	 */
	List<TraceAverage> phoneAverage();
	
	/**
	 * @Title: sSelectPushP 
	 * @Description: TODO(根据学生查询推送的家长帐号) 
	 * @param @param studentId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Login 返回类型 
	 * @throws
	 */
	Login sSelectPushP(@Param("studentId")String studentId);

	List<TraceAverage> phoneAverageWhere(@Param("grade") String grade);
	/**
	 * @Title: phoneSelectShowAverage 
	 * @Description: TODO(查询每日平均评价) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<TraceAverage> 返回类型 
	 * @throws
	 */
	List<TraceAverage> phoneSelectShowAverage(Trace t);
}