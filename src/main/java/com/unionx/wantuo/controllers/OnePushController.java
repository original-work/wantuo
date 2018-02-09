package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.OnePush;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.OnePushService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.OrganizationGroupPush;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.PatriarchGroupPush;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/onePush")
public class OnePushController {
	
	private static Logger log = LoggerFactory.getLogger(OnePushController.class);
	
	@Autowired
	private OnePushService onePushService;
	
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * 
	 * @Title: phoneMessageList 
	 * @Description: TODO(移动端查询后台推送记录) 
	 * @param @param p
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneMessageList")
	public Rest phoneMessageList(OnePush p,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(p.getLogin_accounts())||null==p.getLogin_accounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写接收人帐号！");
				return rest;
			}
			p.setPush_type(ConstantUtil.PUSH_ALL);
			Pager<OnePush> pager=onePushService.queryByList(p,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
//			int time=(int) (System.currentTimeMillis()/1000);
			map.put("manageList",pager.getList());
			map.put("count", pager.getCount());
			p.setStatus(2);
			map.put("unreadCount",onePushService.unreadCount(p));
//			map.put("time",time);
			rest.setData(map);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		return rest;
	}
	
	/**
	 * @Title: phonePatriarchMessageList 
	 * @Description: TODO(移动端根据家长帐号查询机构推送给家长的记录) 
	 * @param @param p
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phonePatriarchMessageList")
	public Rest phonePatriarchMessageList(OnePush p,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(p.getLogin_accounts())||null==p.getLogin_accounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写接收人帐号！");
				return rest;
			}
			p.setPush_type(ConstantUtil.PUSH_ONE);
			Pager<OnePush> pager=onePushService.queryByList(p,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
//			int time=(int) (System.currentTimeMillis()/1000);
			map.put("manageList",pager.getList());
			map.put("count", pager.getCount());
			p.setStatus(2);
			map.put("unreadCount",onePushService.unreadCount(p));
//			map.put("time",time);
			rest.setData(map);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		return rest;
	}
	
	/**
	 * @Title: phoneStudentSave 
	 * @Description: TODO(手机端个人推送) 
	 * @param @param p
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSave")
	public Rest phoneStudentSave(OnePush p) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(p.getPush_login_accounts())||null==p.getPush_login_accounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写推送人帐号！");
				return rest;
			}
			if("".equals(p.getPush_name())||null==p.getPush_name()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写推送人名字！");
				return rest;
			}
			List<Login> op= new ArrayList<Login>();
			op=onePushService.oSelectPushP(p.getPush_login_accounts());
			List<String> dtl = new ArrayList<String>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			p.setCreate_date(formatter.format(new Date()));
			p.setPush_type(ConstantUtil.PUSH_ONE);
			for(Login l : op){
				if(!"".equals(l.getDeviceToken())&&null!=l.getDeviceToken()){
					dtl.add(l.getDeviceToken());
				}
				p.setLogin_accounts(l.getLoginAccounts());
				onePushService.save(p);	
			}
			StringBuilder payload = new StringBuilder();
			payload.append("{");
			payload.append("\"type\":"+"\"0\"");
			payload.append(",");
			payload.append("\"createTime\":\""+formatter.format(new Date())+"\"");	
			payload.append(",");
			payload.append("\"pushName\":\""+p.getPush_name()+"\"");	
			payload.append(",");
			payload.append("\"pushDetail\":\""+p.getPush_details()+"\"");	
			payload.append("}");
			PatriarchGroupPush.apnpush(dtl,(p.getPush_details().length()>30)?p.getPush_details().substring(0,30):p.getPush_details(),1,payload.toString());
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			try{
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(p.getPush_login_accounts());
				sl.setCreate_date(formatter.format(new Date()));
				sl.setOperation_module("机构推送消息");
				sl.setOperation_function("推送");
				sl.setName(p.getPush_name());
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
	 * @Title: phoneUpdateMessage 
	 * @Description: TODO(改变消息状态) 
	 * @param @param p
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneUpdateMessage")
	public Rest phoneUpdateMessage(OnePush p) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(p.getId())||null==p.getId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写接消息id！");
				return rest;
			}
			p.setStatus(3);
			p.setStatus_name("已读");
			onePushService.update(p);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_SUCCESS);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneUpdateUnreadCount 
	 * @Description: TODO(手机端查询未读条数) 
	 * @param @param p
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneUpdateUnreadCount")
	public Rest phoneUpdateUnreadCount(OnePush p) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(p.getLogin_accounts())||null==p.getLogin_accounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写接收人帐号！");
				return rest;
			}
			p.setStatus(2);
			map.put("unreadCount",onePushService.unreadCount(p));
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
	 * @Title: onePushSave 
	 * @Description: TODO(后台推送) 
	 * @param @param p
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/onePushSave")
	public Rest onePushSave(HttpServletRequest request,String type,String note) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			List<Login> logino= new ArrayList<Login>();
			List<Login> loginp= new ArrayList<Login>();
			if(type.equals("1")){//所有机构
				logino=onePushService.manageSelectPushO();	
			}
			if(type.equals("2")){//所有家长
				loginp=onePushService.manageSelectPushP();	
			}
			if(type.equals("3")){//所有用户
				logino=onePushService.manageSelectPushO();
				loginp=onePushService.manageSelectPushP();
			}
			List<String> dtlp = new ArrayList<String>();
			List<String> dtlo = new ArrayList<String>();
			OnePush p = new OnePush();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			p.setCreate_date(formatter.format(new Date()));
			p.setPush_type(ConstantUtil.PUSH_ALL);
			HttpSession session =request.getSession();
		    Manage manage =(Manage)session.getAttribute("userinfo");
		    p.setPush_login_accounts(manage.getLogin_accounts());
		    p.setPush_name(manage.getManage_name());
		    p.setPush_details(note);
			for(Login l : loginp){
				if(!"".equals(l.getDeviceToken())&&null!=l.getDeviceToken()){
					dtlp.add(l.getDeviceToken());
				}
				p.setLogin_accounts(l.getLoginAccounts());
				onePushService.save(p);	
			}
			for(Login l : logino){
				if(!"".equals(l.getDeviceToken())&&null!=l.getDeviceToken()){
					dtlo.add(l.getDeviceToken());
				}
				p.setLogin_accounts(l.getLoginAccounts());
				onePushService.save(p);	
			}
			StringBuilder payload = new StringBuilder();
			payload.append("{");
			payload.append("\"type\":"+"\"0\"");
			payload.append(",");
			payload.append("\"createTime\":\""+formatter.format(new Date())+"\"");	
			payload.append(",");
			payload.append("\"pushName\":\""+p.getPush_name()+"\"");	
			payload.append(",");
			payload.append("\"pushDetail\":\""+p.getPush_details()+"\"");	
			payload.append("}");
			if(dtlp.size()>0){
				PatriarchGroupPush.apnpush(dtlp,(note.length()>30)?note.substring(0,30):note,1,payload.toString());
			}
			if(dtlo.size()>0){
				OrganizationGroupPush.apnpush(dtlo,(note.length()>30)?note.substring(0,30):note,1,payload.toString());
			}
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.POST_MESSAGE_SUCCESS);
			try{
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(p.getPush_login_accounts());
				sl.setCreate_date(formatter.format(new Date()));
				sl.setOperation_module("后台推送消息");
				sl.setOperation_function("推送");
				sl.setName(p.getPush_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.POST_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
}
