package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.SystemLog;

public interface SystemLogService {
	
	public Integer save(SystemLog sl);
	
	public List<SystemLog> queryList(SystemLog sl);
	
	public Integer queryListCount(SystemLog ls);
	
}
