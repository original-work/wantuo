package com.unionx.wantuo.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.CheckStatusService;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.SystemLogService;
//import com.unionx.wantuo.utils.CommonUtils;
import com.unionx.wantuo.utils.OrganizationGerenPush;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/changeStatus")
public class CheckController {

	@Autowired
	private CheckStatusService checkStatusService;
	
	@Autowired 
	private OrganizationService orgService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private LoginService loginService;
	/**
	 * 审批
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkSuccess")
	public Rest CheckSuccess(HttpServletRequest request,String id,String num){
//		System.out.println("id-=-=-==-=-"+id+"--num--"+num);
		Rest rest = new Rest();
		Map<Object, Object> parmMap = new HashMap<Object, Object>();
		try {
			if("1".equals(num)){
				parmMap.put("id", id);
				parmMap.put("check", 2);
			}else if("2".equals(num)){
				parmMap.put("id", id);
				parmMap.put("check", 1);
			}
			checkStatusService.checkSuuccess(parmMap);
			rest.setStatus("0");
			rest.setMessage("审核通过！");
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("机构管理");
				sl.setOperation_function("审批通过");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("审核失败！");
		}
		
		return rest;
	}
	/**
	 * 认证
	 * @param id
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/attestationSuccess")
	public Rest AttestationSuccess(HttpServletRequest request,String id,String num,String login){
		Rest rest = new Rest();
		Map<Object, Object> parmMap = new HashMap<Object, Object>();
		try {
			if("1".equals(num)){
				parmMap.put("id", id);
				parmMap.put("attestation", 2);
				parmMap.put("warranty", 2);
				loginService.updateLogin(login,2);
			}else if("2".equals(num)){
				parmMap.put("id", id);
				parmMap.put("attestation", 1);
				parmMap.put("warranty", 1);
				loginService.updateLogin(login,1);
			}
			checkStatusService.attestationSuccess(parmMap);
			loginService.updateLogin(login, 2);
//			Login l=loginService.findByAccountAndPws(login);
//			String result="";
//			if(l!=null){
//				result=CommonUtils.attestation(login,l.getPassword());
//			}
			rest.setStatus("0");
			rest.setMessage("认证通过！");
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("机构管理");
				sl.setOperation_function("认证通过");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("认证失败！");
		}
		return rest;
	}
	/**
	 * 逻辑删除
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("/datadel")
	public Rest datadel(HttpServletRequest request,String ids){
		Rest rest = new Rest();
		try {
			ids = ids.substring(1);
			String[] iid = ids.split(",");
			for (int i = 0; i < iid.length; i++) {
				Organization o=orgService.selectById(Integer.parseInt(iid[i]));
				Login logs=loginService.findByAccountAndPws(o.getLoginAccounts());
				if(logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
					StringBuilder payload = new StringBuilder();
					payload.append("{");
					payload.append("\"type\":"+"\"4\"");
					payload.append("}");
					OrganizationGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已删除！",0,payload.toString());
				}
				loginService.deleteByLogin(o.getLoginAccounts());
				checkStatusService.deleteData(Integer.parseInt(iid[i]));
				/*删除所有*/
				checkStatusService.delectAll(o.getLoginAccounts());
			}	
			rest.setStatus("0");
			rest.setMessage("删除成功！");
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("机构管理");
				sl.setOperation_function("批量删除");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("删除失败！");
		}
		return rest;
	}
	/**
	 * 授权
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping("/warrantySuccess")
	public Rest warrantySuccess(HttpServletRequest request,String id,String num,String login){
		Rest rest = new Rest();
		Map<Object, Object> parmMap = new HashMap<Object, Object>();
		try {
			String name="";
			if("1".equals(num)){
				parmMap.put("id", id);
				parmMap.put("warranty", 2);
				loginService.updateLogin(login,2);
				rest.setMessage("此账号已生效！");
				name="生效";
			}else if("2".equals(num)){
				parmMap.put("id", id);
				parmMap.put("warranty", 1);
				rest.setMessage("此账号已失效！");
				name="失效";
				Login logs=loginService.findByAccountAndPws(login);
				if(logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
					StringBuilder payload = new StringBuilder();
					payload.append("{");
					payload.append("\"type\":"+"\"4\"");
					payload.append("}");
					OrganizationGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已失效！",0,payload.toString());
				}
				Login lo = new Login();
				lo.setLoginAccounts(login);
				lo.setDeviceToken("");
				lo.setStatus(1);
				loginService.updateLoginBySelected(lo);
			}
			checkStatusService.warrantySuccess(parmMap);
			rest.setStatus("0");
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("机构管理");
				sl.setOperation_function(name);
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("授权失败！");
		}
		
		return rest;
	}
}
