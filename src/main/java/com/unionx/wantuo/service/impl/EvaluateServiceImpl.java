package com.unionx.wantuo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.EvaluateMapper;
import com.unionx.wantuo.model.Evaluate;
import com.unionx.wantuo.service.EvaluateService;

@Service
public class EvaluateServiceImpl extends BaseServiceImpl<Evaluate> implements EvaluateService{

	@Autowired
	private EvaluateMapper em;
	
	@Override
	public int saveEvaluate(Evaluate e) {
		// TODO Auto-generated method stub
		return em.insertSelective(e);
	}

	@Override
	public List<Evaluate> adminQueryList(Evaluate e) {
		// TODO Auto-generated method stub
		return em.adminSelect(e);
	}

	@Override
	public Integer adminQueryCount(Evaluate e) {
		// TODO Auto-generated method stub
		return em.adminSelectCount(e);
	}

	@Override
	public int updateById(Evaluate e) {
		// TODO Auto-generated method stub
		return em.updateByPrimaryKeySelective(e);
	}

	@Override
	public List<Evaluate> hasComments(Evaluate e) {
		// TODO Auto-generated method stub
		return em.isComments(e);
	}
	
	
}
