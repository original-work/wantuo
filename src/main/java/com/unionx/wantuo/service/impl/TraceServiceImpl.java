package com.unionx.wantuo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.TraceMapper;
import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Trace;
import com.unionx.wantuo.model.TraceAverage;
import com.unionx.wantuo.model.TraceMap;
import com.unionx.wantuo.model.TraceShow;
import com.unionx.wantuo.service.TraceService;

@Service("traceService")
public class TraceServiceImpl extends BaseServiceImpl<Trace> implements TraceService {
	
	
	@Autowired
	private TraceMapper tm;

	/**
	 * 手机端根据学科月份查询学生成绩曲线图
	 */
	@Override
	public List<TraceMap> selectSubjectGrade(Trace t) {
		return tm.selectSubjectGrade(t);
	}
	
	/**
	 * 手机端根据月份查询行为曲线、学习曲线曲线图
	 */
	@Override
	public List<TraceShow> phoneSelectShow(Trace t) {
		return tm.phoneSelectShow(t);
	}

	/**
	 * 查询所有学生平均评价
	 */
	@Override
	public List<TraceAverage> phoneAverage() {
		return tm.phoneAverage();
	}

	@Override
	public Login sSelectPushP(String studentId) {
		return tm.sSelectPushP(studentId);
	}

	@Override
	public List<TraceAverage> phoneAverageWhere(String grade) {
		return tm.phoneAverageWhere(grade);
	}

	@Override
	public List<TraceAverage> phoneSelectShowAverage(Trace t) {
		return tm.phoneSelectShowAverage(t);
	}
}
