package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Activity;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.ActivityService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@ResponseBody
	@RequestMapping(value="phoneQueryList")
	public Rest phoneQueryList(Activity a){
		Map<Object, Object> map=new HashMap<Object, Object>();
		Rest rest=new Rest();
		try {
			List<Activity> list=activityService.findAllActivity(a);
			map.put("ActivityList",list);
			rest.setData(map);
			rest.setMessage("访问成功");
			rest.setStatus("0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}
	
	@RequestMapping(value="QueryList",method=RequestMethod.GET)
	public String QueryList(HttpServletRequest request,Activity a){
		try {
			if(a.getActivityName()!=null&&!"".equals(a.getActivityName())){
				String aa=new String(a.getActivityName().getBytes("ISO-8859-1"),"UTF-8");
				a.setActivityName(aa);
			}
			List<Activity> list=activityService.findAllActivity(a);
			HttpSession session=request.getSession();
			session.setAttribute("activityList",list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/views/huodongguanli.jsp";
	}
	
	@RequestMapping(value="findByOne",method=RequestMethod.GET)
	public String findByOne(HttpServletRequest request,String id,String num){
		try {
			Activity v=activityService.findById(Integer.parseInt(id));
			HttpSession session=request.getSession();
			session.setAttribute("activity",v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(num.equals("1")){
			return "redirect:/views/huodong_details.jsp";
		}else{
			return "redirect:/views/huodong_update.jsp";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="updateById",method=RequestMethod.POST)
	public Map<String, Object> updateById(HttpServletRequest request,Activity a){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			if(a!=null){
				a.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				int num=activityService.updateBySelect(a);
				if(num>0){
					map.put("message","修改成功");
					try{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						HttpSession session =request.getSession();
					    Manage manage =(Manage)session.getAttribute("userinfo");
						SystemLog sl = new SystemLog();
						sl.setLogin_accounts(manage.getLogin_accounts());
						sl.setCreate_date(format.format(new Date()));
						sl.setOperation_module("活动管理");
						sl.setOperation_function("编辑");
						sl.setName(manage.getManage_name());
						systemLogService.save(sl);
					}catch (Exception e){
					}
				}else{
					map.put("message","修改失败");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/phoneGetId")//,method=RequestMethod.POST
	public Map<Object,Object> getId(Integer id) {
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
		    map.put("a",activityService.findById(id));
		}catch(Exception e){
//			log.error(e.getMessage());
		}
		return map;
	}
}
