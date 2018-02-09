package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.unionx.wantuo.model.ClassModel;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.ClassService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.service.TeacherService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	private static Logger log = LoggerFactory.getLogger(ClassController.class);
	
	@Autowired
	private OrganizationService ozService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private TeacherService thService;
	
	/**
	 * @Title: phoneSave 
	 * @Description: TODO(添加班级) 
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/phoneSave")
	public Rest phoneSave(ClassModel c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(c.getOrganizationAccounts())||null==c.getOrganizationAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			if("".equals(c.getOrganizationClass())||null==c.getOrganizationClass()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写班级名称！");
				return rest;
			}
			c.setStatus(2);
			c.setStatusName("可用");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			c.setCreateDate(formatter.format(new Date()));
			int i=classService.save(c);
			if(1==i){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			}else{
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_ERROR);
			}
			try{
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(c.getOrganizationAccounts());
				sl.setCreate_date(formatter.format(new Date()));
				sl.setOperation_module("班级管理");
				sl.setOperation_function("班级添加");
				Organization o = ozService.selectByLoginAccounts(c.getOrganizationAccounts());
				sl.setName(o.getOrganization());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneSelect 
	 * @Description: TODO(查询机构下的班级) 
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelect")
	public Rest phoneSelect(ClassModel cm,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			cm.setStatus(2);
			Pager<ClassModel> pager=classService.queryByList(cm,c);
			map.put("classtList",pager.getList());
			map.put("count", pager.getCount());
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneDelete 
	 * @Description: TODO(删除班级) 
	 * @param @param cm
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneDelete")
	public Rest phoneDelete(ClassModel cm,String login) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			cm.setStatus(1);
			int i=classService.update(cm);
			if(1==i){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_SUCCESS);
			}else{
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_ERROR);
			}
			try{
				if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("班级管理");
					sl.setOperation_function("删除班级");
					Organization o = ozService.selectByLoginAccounts(login);
					if(null == o){
						Teacher th=thService.selectByLoginAccounts(login);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					systemLogService.save(sl);
				}
			}catch (Exception e){
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
}
