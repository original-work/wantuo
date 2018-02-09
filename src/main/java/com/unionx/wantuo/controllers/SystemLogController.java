package com.unionx.wantuo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.SystemLogService;

@Controller
@RequestMapping("systemLog")
public class SystemLogController {

	@Autowired
	private SystemLogService sysLogService;
	
	@ResponseBody
	@RequestMapping(value="/queryParamList",method=RequestMethod.POST)
	public Map<String, Object> queryParamList(SystemLog log){
		Map<String, Object> map=new HashMap<String, Object>();
		Integer pageIndex=(log.getPageIndex()*log.getPageSize())-log.getPageSize();
		log.setPageIndex(pageIndex);
		try {
			List<SystemLog> list=sysLogService.queryList(log);
			Integer count=sysLogService.queryListCount(log);
			map.put("sysLogList", list);
			map.put("count", count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("message", "查询出错");
		}
		return map;
	}
}
