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

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.service.TeacherService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.OrganizationGerenPush;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private static Logger log = LoggerFactory.getLogger(TeacherController.class);
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private LoginService loginService;


	@Autowired
	private OrganizationService ozService;
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * @Title: queryByList 
	 * @Description: TODO(获取机构下的班主任) 
	 * @param @param t
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@RequestMapping(value="/phoneQueryByList")//,method=RequestMethod.POST
	@ResponseBody
	public Rest queryByList(Teacher t,Condition c){
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try {
			t.setStatus(2);
			Pager<Teacher> queryList=teacherService.queryByList(t, c);
			map.put("queryList", queryList.getList());
			map.put("Count", queryList.getCount());
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
		} catch (Exception e) {
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.info(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneDelete 
	 * @Description: TODO(删除老师) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneDelete")
	public Rest phoneDelete(Teacher t,String login) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			t.setStatus(1);
//			int i=teacherService.update(t);
//			if(i==1){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Login logs=loginService.findByAccountAndPws(t.getLoginAccounts());
				if(logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
					StringBuilder payload = new StringBuilder();
					payload.append("{");
					payload.append("\"type\":"+"\"4\"");
					payload.append("}");
					OrganizationGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已删除！",0,payload.toString());
				}
				loginService.deleteByLogin(t.getLoginAccounts());
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_SUCCESS);
				try{
					if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("辅导老师");
					sl.setOperation_function("删除辅导老师");
					Organization o = ozService.selectByLoginAccounts(login);
					if(null == o){
						Teacher th=teacherService.selectByLoginAccounts(login);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					systemLogService.save(sl);
					}
				}catch (Exception e){
				}
//			}else{
//				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
//				rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_ERROR);
//			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneUpdateTeacher 
	 * @Description: TODO(修改老师信息) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneUpdateTeacher")
	public Rest phoneUpdateTeacher(Teacher t,String login) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			int i=teacherService.update(t);
			if(i==1){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_SUCCESS);
				try{
					if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("辅导老师");
					sl.setOperation_function("修改辅导老师");
					Organization o = ozService.selectByLoginAccounts(login);
					if(null == o){
						Teacher th=teacherService.selectByLoginAccounts(login);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					systemLogService.save(sl);
					}
				}catch (Exception e){
				}
			}else{
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_ERROR);
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
}
