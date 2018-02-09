package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.Patriarch;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.PatriarchService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.service.TeacherService;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private OrganizationService ozService;
	@Autowired
	private PatriarchService phService;
	@Autowired
	private TeacherService thService;
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * 家长注册
	 * @param patriarch
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/phoneParentRegister")//,method=RequestMethod.POST
	@ResponseBody
	public Rest registerParent(String loginAccounts,String password){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>(); 
		try {
			if("".equals(loginAccounts)||null==loginAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(password)||null==password){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写密码！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Login login=loginService.findToRegister(loginAccounts,null);
			if(null==login||"".equals(login.toString())){
				Patriarch ph=new Patriarch();
				ph.setCreateDate(formatter.format(new Date()));
				ph.setLoginAccounts(loginAccounts);
				ph.setStatus(2);
				ph.setStatusName("可用");
				phService.ParentRegister(ph);//保存注册用户信息
				Login lg=new Login();
				lg.setLoginAccounts(loginAccounts);
				lg.setPassword(password);
				lg.setStatus(2);
				lg.setCreateDate(formatter.format(new Date()));
				lg.setUpdateDate(formatter.format(new Date()));
				lg.setStatusName("可用");
				lg.setAccountsType(1);//家长
				loginService.saveLoginBySelected(lg);//保存登入信息
				map.put("LoginAccounts", loginAccounts);
				map.put("password", password);
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("手机号已注册！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 机构注册
	 * @param LoginAccounts 注册账号
	 * @param password 密码
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/phoneOganizationRegister")//,method=RequestMethod.POST
	@ResponseBody
	public Rest registerOrganization(Organization orz,String password){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>(); 
		try {
			if("".equals(orz.getLoginAccounts())||null==orz.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(password)||null==password){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写密码！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Login login=loginService.findToRegister(orz.getLoginAccounts(),null);
			if(login==null||"".equals(login.toString())){
				orz.setCreateDate(formatter.format(new Date()));
				orz.setUpdateDate(formatter.format(new Date()));
				orz.setCheck(1);
				orz.setLoginAccounts(orz.getLoginAccounts());
				ozService.saveOrganizationBySelected(orz);//保存用户注册信息
				
				Login lg=new Login();
				lg.setCreateDate(formatter.format(new Date()));
				lg.setUpdateDate(formatter.format(new Date()));
				lg.setLoginAccounts(orz.getLoginAccounts());
				lg.setPassword(password);
				lg.setAccountsType(2);//注册类型2机构，3机构老师
				lg.setStatus(1);
				loginService.saveLoginBySelected(lg);//保存登入信息
				map.put("LoginAccounts", orz.getLoginAccounts());
				map.put("password", password);
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("手机号已注册！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	
	
	/**
	 * 机构老师注册
	 * @param loginAccounts 登入账号
	 * @param password 密码
	 * @param organizationAccounts 机构账号
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/phoneRegisterTeacher")//,method=RequestMethod.POST
	@ResponseBody
	public Rest registerTeacher(String loginAccounts,String password,String organizationAccounts,String phone,String name){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>(); 
		try {
			if("".equals(loginAccounts)||null==loginAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(password)||null==password){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写密码！");
				return rest;
			}
			if("".equals(organizationAccounts)||null==organizationAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Login login=loginService.findToRegister(loginAccounts,null);
			if(login==null||"".equals(login.toString())){
				Teacher th=new Teacher();
				th.setLoginAccounts(loginAccounts);
				th.setStatus(2);
				th.setOrganizationAccounts(organizationAccounts);
				th.setCreateDate(formatter.format(new Date()));
				th.setName(name);
				th.setPhone(phone);
				thService.saveTeacher(th);
				
				Login lg=new Login();
				lg.setCreateDate(formatter.format(new Date()));
				lg.setUpdateDate(formatter.format(new Date()));
				lg.setLoginAccounts(loginAccounts);
				lg.setPassword(password);
				lg.setAccountsType(3);//注册类型2机构，3机构老师
				lg.setStatus(2);
				lg.setStatusName("可用");
				loginService.saveLoginBySelected(lg);//保存登入信息
				map.put("LoginAccounts", loginAccounts);
				map.put("password", password);
				rest.setStatus("0");
				rest.setMessage("辅导员添加成功！");
				try{
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(organizationAccounts);
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("辅导老师");
					sl.setOperation_function("添加辅导老师");
					Organization o = ozService.selectByLoginAccounts(organizationAccounts);
					sl.setName(o.getOrganization());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}else{
				rest.setStatus("1");
				rest.setMessage("帐号已注册！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	
}
