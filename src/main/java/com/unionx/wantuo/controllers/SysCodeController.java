package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.SysCode;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.SysCodeService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/syscode")
public class SysCodeController {
	
	private static Logger log = LoggerFactory.getLogger(SysCodeController.class);
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	/**
	 * @Title: phoneQueryByList 
	 * @Description: TODO(手机端查询字典表列表 分页) 
	 * @param @param s
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneQueryByList")
	public Rest phoneQueryByList(SysCode s,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			s.setStatus(ConstantUtil.SATATUS_NEW);
			Pager<SysCode> pager=sysCodeService.queryByList(s,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("sysCodeList",pager.getList());
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
	 * 后台管理展示数据字典
	 * @param s
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/QueryByList",method=RequestMethod.POST)
	@ResponseBody
	public Rest QueryByList(SysCode s,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			s.setStatus(ConstantUtil.SATATUS_NEW);
			Pager<SysCode> pager=sysCodeService.queryByList(s,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("sysCodeList",pager.getList());
			map.put("count", pager.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	

	@RequestMapping(value="/deleteSysCode",method=RequestMethod.POST)
	@ResponseBody
	public Rest deleteSysCode(HttpServletRequest request,String idStrs){
		Rest rest=new Rest();
		int num=0;
		String Message="";
		String Status ="";
		try{
			if(idStrs!=null&&!"".equals(idStrs)){
				String[] ids=idStrs.split(",");
				for (int i = 0; i < ids.length; i++) {
					if(ids[i]==null||ids[i].equals("")){
						continue;
					}
					SysCode s = new SysCode();
					Condition c = new Condition(); 
					s.setParentId(Integer.parseInt(ids[i]));
					Pager<SysCode> pager=sysCodeService.queryByList(s,c);
					if(pager.getCount()>0){
						Status = "2";
						Message += "编码等于" +ids[i] + "有子节点不能删除";
					}else{
						num=sysCodeService.adminDeleteSysCode(ids[i]);
					}
					
				}
				if(num>0){
					if(!Status.equals(2)){
						rest.setStatus("0");
						rest.setMessage("删除成功！");
					}else{
						rest.setStatus(Status);
						rest.setMessage(Message);
					}

					try{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						HttpSession session =request.getSession();
					    Manage manage =(Manage)session.getAttribute("userinfo");
						SystemLog sl = new SystemLog();
						sl.setLogin_accounts(manage.getLogin_accounts());
						sl.setCreate_date(format.format(new Date()));
						sl.setOperation_module("数据字典");
						sl.setOperation_function("删除");
						sl.setName(manage.getManage_name());
						systemLogService.save(sl);
					}catch (Exception e){
					}
				}
			}
	
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
		}
		return rest;
	}
	
	

	
	@RequestMapping(value="/updateById",method=RequestMethod.POST)
	@ResponseBody
	public Rest updateById(HttpServletRequest request,SysCode sc){
		Rest rest=new Rest();
		try{
			int num=sysCodeService.updateSysCode(sc);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("修改成功！");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manage =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manage.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("数据字典");
					sl.setOperation_function("编辑");
					sl.setName(manage.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
			}
		return rest;
	}
	
	@RequestMapping(value="/getParentMeum",method=RequestMethod.GET)
	@ResponseBody
	public Rest getParentMeum(){
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			List<SysCode> list=sysCodeService.findByParent();
			map.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rest.setData(map);
		return rest;
	}
	
	@RequestMapping(value="/adminAddSysCode",method=RequestMethod.POST)
	@ResponseBody
	public Rest adminAddSysCode(HttpServletRequest request,SysCode sc){
		Rest rest=new Rest();
		try {
			int num=sysCodeService.addSysCode(sc);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("保存成功！");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manage =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manage.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("数据字典");
					sl.setOperation_function("添加");
					sl.setName(manage.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("方法访问失败");
		}
		return rest;
	}
}
