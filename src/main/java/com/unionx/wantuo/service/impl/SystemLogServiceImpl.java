package com.unionx.wantuo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.SystemLogMapper;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.SystemLogService;

@Service("systemLogService")
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog> implements SystemLogService {
	
	@Autowired
	private SystemLogMapper slm;

	@Override
	public List<SystemLog> queryList(SystemLog sl) {
		// TODO Auto-generated method stub
		return slm.adminFindLog(sl);
	}

	@Override
	public Integer queryListCount(SystemLog ls) {
		// TODO Auto-generated method stub
		return slm.adminFindLogCount(ls);
	}
	
}
