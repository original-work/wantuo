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

import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.ManageService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	private static Logger log = LoggerFactory.getLogger(ManageController.class);
	
	@Autowired
	private ManageService manageService;

	@Autowired
	private SystemLogService systemLogService;
	/**
	 * @Title: save 
	 * @Description: TODO(保存后台管理员) 
	 * @param @param m Manag实体
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/save")
	public Map<Object,Object> save(Manage m) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			manageService.save(m);
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return map;
	}
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(查询后台管理员列表) 
	 * @param @param m Manag实体
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/queryByList",method=RequestMethod.POST)
	public Map<Object,Object> queryByList(Manage m,Condition c) {
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			Pager<Manage> pager=manageService.queryByList(m,c);
			map.put("manageList",pager.getList());
			map.put("count", pager.getCount());
			map.put("status",ConstantUtil.POST_SATATUS_SUCCESS);
			map.put("message",ConstantUtil.POST_MESSAGE_SUCCESS);
		}catch(Exception e){
			map.put("status",ConstantUtil.POST_SATATUS_ERROR);
			map.put("message",ConstantUtil.POST_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		return map;
	}
	
	/**
	 * @Title: update 
	 * @Description: TODO(修改管理员帐号信息  or 单条记录删除) 
	 * @param @param m Manag实体
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Map<Object,Object> update(Manage m) {
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		return map;
	}
	
	/**
	 * @Title: updateStaust 
	 * @Description: TODO(批量删除) 
	 * @param @param ids in（ids） , fields 表中字段
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/updateStatus")
	public Map<Object,Object> updateStatus(String ids,String fields) {
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		return map;
	}
	
	/**
	 * @Title: getById 
	 * @Description: TODO(根据ID查询管理员) 
	 * @param @param id
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/getById")
	public Map<Object,Object> getById(int id) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		return map;
	}
	
	/**
	 * 根据id删除管理员
	 * @param idStrs id数组
	 * @return
	 */
	@RequestMapping(value="/deleteManageById",method=RequestMethod.POST)
	@ResponseBody
	public Rest deleteManageById(HttpServletRequest request,String idStrs){
		Rest rest=new Rest();
		int num=0;
		try{
			String[] ids=null;
			if(idStrs!=null&&!"".equals(idStrs.toString())){
				ids=idStrs.trim().split(",");
			}
			for (int i = 0; i < ids.length; i++) {
				if(ids[i]==null||ids[i].equals("")){
					continue;
				}
				Manage m=new Manage();
				m.setId(Integer.parseInt(ids[i]));
				m.setStatus(1);
				if(ids[i]!="1"&&!ids[i].equals("1")){
					num=manageService.updateById(m);
				}
			}
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("删除成功！");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manageUser =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manageUser.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("用户管理");
					sl.setOperation_function("删除");
					sl.setName(manageUser.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}else{
				rest.setStatus("2");
				rest.setMessage("删除失败！");
			}
		} catch (Exception e) {
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
		}
		return rest;
	}
	
	
	/**
	 * 添加管理员
	 * @param manage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="insertMange",method=RequestMethod.POST)
	public Rest insertMange(HttpServletRequest request,Manage manage){
		Rest rest=new Rest();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
			if(manageService.findByLogin(manage.getLogin_accounts())!=null){
				rest.setStatus("2");
				rest.setMessage("用户名已注册，请重新输入！");
			}else{
				manage.setStatus(2);
				manage.setStatus_name("可用");
				manage.setCreate_date(formatter.format(new Date()));
				int num=manageService.saveManage(manage);
				if(num>0){
					rest.setStatus("0");
					rest.setMessage("添加成功！");
					try{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						HttpSession session =request.getSession();
					    Manage manageUser =(Manage)session.getAttribute("userinfo");
						SystemLog sl = new SystemLog();
						sl.setLogin_accounts(manageUser.getLogin_accounts());
						sl.setCreate_date(format.format(new Date()));
						sl.setOperation_module("用户管理");
						sl.setOperation_function("添加");
						sl.setName(manageUser.getManage_name());
						systemLogService.save(sl);
					}catch (Exception e){
					}
				}
			}
		} catch (Exception e) {
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
			}
		return rest;
	}
	
	@RequestMapping(value="updateBySelect",method=RequestMethod.POST)
	@ResponseBody
	public Rest updateBySelect(HttpServletRequest request,Manage m){
		Rest rest=new Rest();
		try {
			int num =manageService.updateById(m);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("修改成功！");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manageUser =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manageUser.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("用户管理");
					sl.setOperation_function("编辑");
					sl.setName(manageUser.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}else{
				rest.setStatus("2");
				rest.setMessage("修改失败！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		return rest;
	}
}
