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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.Patriarch;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.service.PatriarchService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.PatriarchGerenPush;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/Patriarch")
public class PatriarchController {
	@Autowired
	private PatriarchService phService;
	
	@Autowired
	private LoginService lgService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	private static Logger log = LoggerFactory.getLogger(PatriarchController.class);
	/**
	 * 个人信息维护接口
	 * @param ph
	 * @return
	 */
	@RequestMapping(value="/phoneUpdateByPatriarch")//,method=RequestMethod.GET
	@ResponseBody
	public Rest updateByPatriarch(Patriarch ph){
		Rest rest=new Rest();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ph.setCreateDate(formatter.format(new Date()));
			int num=phService.updateByLoginAccounts(ph);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("个人信息修改失败！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		return rest;
	}
	
	/**
	 * @Title: phoneQueryByList 
	 * @Description: TODO(手机端查询全部家长) 
	 * @param @param s
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneQueryByList")
	public Rest phoneQueryByList(Patriarch p,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			p.setStatus(2);
			Pager<Patriarch> pager=phService.queryByList(p,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("patriarchList",pager.getList());
			map.put("count", pager.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	
	/**
	 * 后台管理查询家长列表
	 * @param p
	 * @param c
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/QueryByList",method=RequestMethod.POST)
	public Rest QueryByList(Patriarch p,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			Pager<Patriarch> pager=phService.queryByList(p,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("patriarchList",pager.getList());
			map.put("count", pager.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 删除家长信息，假删除
	 * @param idStrs
	 * @return
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	@ResponseBody
	public Rest deleteById(HttpServletRequest request,String idStrs){
		Rest rest=new Rest();
		try{
			String[] ids=null;
			if(idStrs!=null&&!"".equals(idStrs.toString())){
				ids=idStrs.split(",");
			}
			for (int i = 0; i < ids.length; i++) {
				if(ids[i]==null||ids[i].equals("")){
					continue;
				}
				phService.updatePatriarch(ids[i].toString(),null);//修改家长表
				lgService.deleteByLogin(ids[i]);//修改登入表
			}
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("家长管理");
				sl.setOperation_function("删除");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
			rest.setStatus("0");
			rest.setMessage("删除成功！");
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
			}

		return rest;
	}
	
	
	@RequestMapping(value="/jinyongOrqiyong")
	@ResponseBody
	public Rest jinyongOrqiyong(HttpServletRequest request,String loginAccounts,String num,String id){
		Rest rest=new Rest();
		Integer status=null;
		int sum=-1;
		String a="";
		try{
			if(num.equals("jinyong")||num=="jinyong"){//禁用
				status=1;
				sum=1;
				a="禁用";
				phService.updatePatriarchId(id,status);
				Login logs=lgService.findByAccountAndPws(loginAccounts);
				if(logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
					StringBuilder payload = new StringBuilder();
					payload.append("{");
					payload.append("\"type\":"+"\"4\"");
					payload.append("}");
					PatriarchGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已禁用！",0,payload.toString());
				}
				Login lo = new Login();
				lo.setLoginAccounts(loginAccounts);
				lo.setDeviceToken("");
				lo.setStatus(1);
				lgService.updateLoginBySelected(lo);
			}if(num.equals("qiyong")||num=="qiyong"){//启用
				status=2;
				sum=2;
				a="启用";
				phService.updatePatriarchId(id,status);
				lgService.updateLogin(loginAccounts,status);//修改登入表
			}if(num.equals("shanchu")||num=="shanchu"){//删除
				status=null;
				sum=0;
				a="删除";
				phService.updatePatriarchId(id,status);
				Login logs=lgService.findByAccountAndPws(loginAccounts);
				if(logs.getDeviceToken()!=null&&!"".equals(logs.getDeviceToken())){
					StringBuilder payload = new StringBuilder();
					payload.append("{");
					payload.append("\"type\":"+"\"4\"");
					payload.append("}");
					PatriarchGerenPush.apnpush(logs.getDeviceToken(),"您的帐号已删除！",0,payload.toString());
				}
				lgService.deleteByLogin(loginAccounts);//修改登入表
			}
			if(sum==1){
				rest.setStatus("1");
				rest.setMessage("已禁用");
			}else if(sum==2){
				rest.setStatus("2");
				rest.setMessage("已启用");
			}else if(sum==0){
				rest.setStatus("0");
				rest.setMessage("删除成功！");
			}
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("家长管理");
				sl.setOperation_function(a);
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return rest;
	}
}
