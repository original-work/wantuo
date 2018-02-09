package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.Evaluate;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;

public interface EvaluateService {
	
	/**
	 * 添加评论
	 * @param e
	 * @return
	 */
	public int saveEvaluate(Evaluate e);
	
	/**
	 * 列表展示评论
	 * @param e
	 * @param c
	 * @return
	 */
	public Pager<Evaluate> queryByList(Evaluate e,Condition c);
	
	
	public List<Evaluate> adminQueryList(Evaluate e);
	
	public Integer adminQueryCount(Evaluate e);
	
	public int updateById(Evaluate e);
	
	public List<Evaluate> hasComments(Evaluate e);
}
