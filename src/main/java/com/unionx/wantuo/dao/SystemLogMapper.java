package com.unionx.wantuo.dao;

import java.util.List;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.SystemLog;


public interface SystemLogMapper extends BaseDao<SystemLog>{
	
	public List<SystemLog> adminFindLog(SystemLog log);
	
	public Integer adminFindLogCount(SystemLog log);
}