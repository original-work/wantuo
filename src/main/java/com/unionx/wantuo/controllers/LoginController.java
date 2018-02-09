package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.Patriarch;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.service.ManageService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.PatriarchService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.service.TeacherService;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.OrganizationGerenPush;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PatriarchService phService;
	

	@Autowired
	private OrganizationService ozService;
	
	@Autowired
	private TeacherService thService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	/**
	 * @Title: manageLogin 
	 * @Description: TODO(后台管理员登录) 
	 * @param @param m
	 * @param @param request
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/manageLogin")
	public Map<Object,Object> manageLogin(Manage m,HttpServletRequest request) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			Manage manage=new Manage();
			manage=manageService.getById(m);
			if(null!=m&&m.getPassword().equals(manage.getPassword())){
				HttpSession session =request.getSession();
				session.setAttribute("userinfo", manage);
				map.put("status",ConstantUtil.POST_SATATUS_SUCCESS);
				map.put("message",ConstantUtil.POST_LOGIN_MESSAGE_SUCCESS);
			}else{
				map.put("status",ConstantUtil.POST_SATATUS_ERROR);
				map.put("message","帐号或密码错误！");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			map.put("status",ConstantUtil.POST_SATATUS_ERROR);
			map.put("message","帐号或密码错误！");
		}
		return map;
	}

	
	
	
	/**
	 * IOS家长登入
	 * @param request
	 * @param loginAccount 帐号
	 * 
	 * @param password 密码
	 * @param deviceToken 推送id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/phoneIosParentlogin")//,method=RequestMethod.POST
	public Rest IosParentlogin(HttpServletRequest request,@ModelAttribute Login login){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(login.getLoginAccounts())||null==login.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(login.getPassword())||null==login.getPassword()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写密码！");
				return rest;
			}
			Login logs=loginService.findByAccountAndPws(login.getLoginAccounts());
			if(null!=logs&&!logs.toString().equals("")){
				//request.getSession().setAttribute("Login", login);
				if(null==logs.getAccountsType()||"".equals(logs.getAccountsType())||!logs.getAccountsType().equals(1)){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage("您无权在次登录！");
					return rest;
				}
				if(logs.getStatus().equals(1)){
					rest.setStatus("1");
					rest.setMessage("帐号已禁用！");
					return rest;
				}
				if(!login.getPassword().equals(logs.getPassword())){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage(ConstantUtil.PHONE_PASSWORD_MESSAGE_ERROR);
					return rest;
				}
//				if(!logs.getDeviceToken().equals(login.getDeviceToken())&&logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
//					StringBuilder payload = new StringBuilder();
//					payload.append("{");
//					payload.append("\"type\":"+"\"3\"");
//					payload.append("}");
//					PatriarchGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已在别处登录！",0,payload.toString());
//				}
				logs.setDeviceToken(login.getDeviceToken());
				logs.setPhoneUuid(login.getPhoneUuid().toString());
				loginService.updateLoginBySelected(logs);//修改推送帐号
				Patriarch ph=phService.findByAccounts(logs.getLoginAccounts());//获取登入人详细信息
				rest.setStatus("0");
				rest.setMessage("方法请求成功");
				map.put("login", logs);
				map.put("Patriarch", ph);
			}else{
				rest.setStatus("1");
				rest.setMessage("帐号不存在或已删除！");
				return rest;
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败");
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 机构老师登入接口
	 * @param login
	 * @param loginAccount 帐号
	 * @param accountsType 登入类型2机构，3机构老师
	 * @param password 密码
	 * @param deviceToken 推送id
	 * @return
	 */
	@RequestMapping(value="/phoneOzAndTeacherLogin")//,method=RequestMethod.POST
	@ResponseBody
	public Rest ozAndTeacherLogin(@ModelAttribute Login login){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(login.getLoginAccounts())||null==login.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(login.getPassword())||null==login.getPassword()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写密码！");
				return rest;
			}
			Login logs=loginService.findByAccountAndPws(login.getLoginAccounts());
			if(logs!=null&&!"".equals(logs.toString())){
				if(null==logs.getAccountsType()||"".equals(logs.getAccountsType())||logs.getAccountsType().equals(1)){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage("您无权在次登录！");
					return rest;
				}
				if("2".equals(logs.getAccountsType().toString())){//机构登入
					Organization oz=ozService.selectByLoginAccounts(logs.getLoginAccounts());
					if(logs.getStatus().equals(1)){
						if(null==oz){
							rest.setStatus("1");
							rest.setMessage("您的机构账号已失效，请与客服联系 400-070-8835！");
							return rest;
						}
						if(oz.getAttestation()==1){
							rest.setStatus("1");
							rest.setMessage("您的机构账号审批中！");
							return rest;
						}
					}
					map.put("Organization", oz);//返回机构基本信息
				}else if("3".equals(logs.getAccountsType().toString())){//教师登入
					Teacher th=thService.selectByLoginAccounts(logs.getLoginAccounts());
					map.put("Teacher", th);
				}
				if(!login.getPassword().equals(logs.getPassword())){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage(ConstantUtil.PHONE_PASSWORD_MESSAGE_ERROR);
					return rest;
				}
				if(!logs.getDeviceToken().equals(login.getDeviceToken())&&logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
					StringBuilder payload = new StringBuilder();
					payload.append("{");
					payload.append("\"type\":"+"\"3\"");
					payload.append("}");
					OrganizationGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已在别处登录！",0,payload.toString());
				}
				logs.setPhoneUuid(login.getPhoneUuid().toString());
				logs.setDeviceToken(login.getDeviceToken().toString());
				loginService.updateLoginBySelected(logs);//修改推送帐号
				map.put("Login", logs);
				rest.setStatus("0");
				rest.setMessage("登入成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("帐号不存在或已删除！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	
	/**
	 * 修改密码
	 * @param LoginAccounts 登入账号
	 * @param oldPassword	旧密码
	 * @param newPassword	新密码
	 * @return
	 */
	@RequestMapping(value="/phoneUpdatePassword")//,method=RequestMethod.POST
	@ResponseBody
	public Rest updatePassword(String loginAccounts,String oldPassword,String newPassword){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(loginAccounts)||null==loginAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(oldPassword)||null==oldPassword){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写旧密码！");
				return rest;
			}
			if("".equals(newPassword)||null==newPassword){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写新密码！");
				return rest;
			}
			Login logs=loginService.findByAccountAndPws(loginAccounts);
			if(null!=logs&&!"".equals(logs.toString())){
				if(!oldPassword.equals(logs.getPassword())){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage(ConstantUtil.PHONE_PASSWORD_MESSAGE_ERROR);
					return rest;
				}
				if(oldPassword.equals(newPassword)){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage("新密码不能与旧密码一致！");
					map.put("loginAccounts", loginAccounts);
					map.put("password", oldPassword);
				}else{
					Login login=new Login();
					login.setPassword(newPassword);
					login.setLoginAccounts(loginAccounts);
					loginService.updateLoginBySelected(login);//修改密码
					map.put("loginAccounts", loginAccounts);
					map.put("password", newPassword);
					rest.setStatus("0");
					rest.setMessage("方法请求成功！");
				}
				try{
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(loginAccounts);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("设置");
					sl.setOperation_function("修改密码");
					if(logs.getAccountsType().equals(1)){
						Patriarch p = phService.findByAccounts(loginAccounts);
						sl.setName(p.getPatriarchName());
					}
					if(logs.getAccountsType().equals(2)){
						Organization o = ozService.selectByLoginAccounts(loginAccounts);
						sl.setName(o.getOrganization());
					}
					if(logs.getAccountsType().equals(3)){
						Teacher t = thService.selectByLoginAccounts(loginAccounts);
						sl.setName(t.getName());
					}
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}else{
				rest.setStatus("1");
				rest.setMessage("账号不存在！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 找回密码
	 * @param loginAccounts 登入账号
	 * @param newPassword	密码
	 * @return
	 */
	@RequestMapping(value="/phoneFindPassword")//,method=RequestMethod.POST
	@ResponseBody
	public Rest findPassword(String loginAccounts,String newPassword){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(loginAccounts)||null==loginAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			if("".equals(newPassword)||null==newPassword){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写新密码！");
				return rest;
			}
			Login login=loginService.findToRegister(loginAccounts,"2");//查询帐号是否存在
			if(null!=login&&!"".equals(login.toString())){
				Login ln=new Login();
				ln.setPassword(newPassword);
				ln.setLoginAccounts(loginAccounts);
				loginService.updateLoginBySelected(ln);//修改密码
				map.put("loginAccounts", loginAccounts);
				map.put("password", newPassword);
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("帐号不存在！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 退出登入
	 * @param loginAccounts 登入帐号
	 * @return
	 */
	@RequestMapping(value="/phoneOutLogin")//,method=RequestMethod.POST
	@ResponseBody
	public Rest outLogin(String loginAccounts){
		Rest rest=new Rest();
		Login login=new Login();
		try {
			login.setDeviceToken("");
			login.setLoginAccounts(loginAccounts);
			loginService.updateLoginBySelected(login);
			rest.setStatus("0");
			rest.setMessage("方法请求成功！");
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		return rest;
	}
	
	/**
	 * @Title: loginOut 
	 * @Description: TODO(后台退出) 
	 * @param @param request
	 * @param @return 设定文件 
	 * @author abner
	 * @return ModelAndView 返回类型 
	 * @throws
	 */
	@RequestMapping(value="/loginOut",method=RequestMethod.GET)//,method=RequestMethod.POST
	public ModelAndView loginOut(HttpServletRequest request){
		ModelAndView view=new ModelAndView();
		try {
			HttpSession session1 = request.getSession();
			session1.invalidate();
		} catch (Exception e) {
		}
		view.setViewName("redirect:login.jsp");
		return view;
	}
	
	/**
	 * @Title: phoneVerifyLogin 
	 * @Description: TODO(手机端校验) 
	 * @param @param loginAccounts
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@RequestMapping(value="/phoneVerifyLogin")//,method=RequestMethod.POST
	@ResponseBody
	public Rest phoneVerifyLogin(Login login){
		Rest rest=new Rest();
		rest.setStatus("0");
		rest.setMessage("方法请求成功！");
		try {
			if("".equals(login.getLoginAccounts())||null==login.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			Login logs=loginService.findByAccountAndPws(login.getLoginAccounts());
			if(null!=logs){
				if(logs.getAccountsType().equals(1)){
					if(logs.getStatus().equals(1)){
						rest.setStatus("1");
						rest.setMessage("您的帐号已禁用！");
					}
				}else{
					if(null!=logs.getPhoneUuid()&&null!=login.getPhoneUuid()&&!logs.getPhoneUuid().equals(login.getPhoneUuid())){
						rest.setStatus("1");
						rest.setMessage("您的帐号已在别处登录！");
					}
					if(logs.getStatus().equals(1)){
						rest.setStatus("1");
						rest.setMessage("您的帐号已禁用！");
					}
				}
			}else{
				rest.setStatus("1");
				rest.setMessage("您的帐号已删除！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		return rest;
	}
}
