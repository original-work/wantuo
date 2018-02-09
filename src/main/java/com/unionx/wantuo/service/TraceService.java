package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Trace;
import com.unionx.wantuo.model.TraceAverage;
import com.unionx.wantuo.model.TraceMap;
import com.unionx.wantuo.model.TraceShow;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface TraceService {
	
	/**
	 * @Title: save 
	 * @Description: TODO(手机端添加追踪信息) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	public Integer save(Trace t);
	
	/**
	 * @Title: update 
	 * @Description: TODO(手机端修改追踪信息) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	public Integer update(Trace t);
	
	/**
	 * @Title: getById 
	 * @Description: TODO(手机端获取每日追踪信息) 
	 * @param @param o
	 * @param @return 设定文件 
	 * @author abner
	 * @return Trace 返回类型 
	 * @throws
	 */
	public Trace getById(Trace t);
	
	/**
	 * @Title: selectSubjectGrade 
	 * @Description: TODO(手机端根据学科月份查询学生成绩曲线图) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Trace> 返回类型 
	 * @throws
	 */
	public List<TraceMap> selectSubjectGrade(Trace t);
	
	/**
	 * @Title: selectSubjectGrade 
	 * @Description: TODO(手机端根据月份查询行为曲线、学习曲线曲线图) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Trace> 返回类型 
	 * @throws
	 */
	public List<TraceShow> phoneSelectShow(Trace t);
	
	/**
	 * @Title: phoneAverage 
	 * @Description: TODO(查询所有学生平均评价) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<Trace> 返回类型 
	 * @throws
	 */
	public List<TraceAverage> phoneAverage();

	/**
	 * @Title: sSelectPushP 
	 * @Description: TODO(根据学生查询推送的家长帐号) 
	 * @param @param studentId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Login 返回类型 
	 * @throws
	 */
	public Login sSelectPushP(String studentId);
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(签到报表) 
	 * @param @param t
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Pager<Trace> 返回类型 
	 * @throws
	 */
	public Pager<Trace> queryByList(Trace t, Condition c);

	public List<TraceAverage> phoneAverageWhere(String grade);
	
	/**
	 * @Title: phoneSelectShowAverage 
	 * @Description: TODO(查询每日平均评价) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return List<TraceAverage> 返回类型 
	 * @throws
	 */
	public List<TraceAverage> phoneSelectShowAverage(Trace t);
}
