package com.unionx.wantuo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.unionx.wantuo.model.Collect;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.service.CollectService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/collect")
public class CollectController {
	
	private static Logger log = LoggerFactory.getLogger(CollectController.class);
	
	@Autowired
	private CollectService collectService;
	
	/**
	 * 收藏机构方法
	 * @param c
	 * @param patriarchAccounts	家长帐号
   	 * @param organizationAccounts	机构帐号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/phoneSave")//,method=RequestMethod.POST
	public Rest phoneSave(Collect c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(c.getPatriarchAccounts())||null==c.getPatriarchAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写家长帐号！");
				return rest;
			}
			if("".equals(c.getOrganizationAccounts())||null==c.getOrganizationAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			c.setStatus(2);
			c.setStatusName("可用");
			int i=collectService.save(c);
			if(1==i){
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("收藏机构失败");
			}
		}catch(Exception e){
			rest.setStatus("1");
			rest.setMessage("方法访问失败！");
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 我的收藏列表展示
	 * @param ct
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/phoneQueryByList")//,method=RequestMethod.POST
	@ResponseBody
	public Rest phoneQueryByList(Collect ct,Condition c){
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try {
			if("".equals(ct.getPatriarchAccounts())||null==ct.getPatriarchAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写家长帐号！");
				return rest;
			}
			Pager<Organization> queryList=collectService.queryList(ct, c);
			map.put("collectList", queryList.getList());
			map.put("collectCount", queryList.getCount());
			rest.setStatus("0");
			rest.setMessage("方法请求成功！");
		} catch (Exception e) {
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	/**
	 * 取消收藏
	 * @param patriarchAccounts 家长登入账号
	 * @param organizationAccounts 机构账号
	 * @return
	 */
	@RequestMapping(value="/phoneDelete")//,method=RequestMethod.POST
	@ResponseBody
	public Rest phoneDelete(String patriarchAccounts,String organizationAccounts){
		Rest rest=new Rest();
		try {
			if("".equals(patriarchAccounts)||null==patriarchAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写家长帐号！");
				return rest;
			}
			if("".equals(organizationAccounts)||null==organizationAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			int i=collectService.deleteColect(patriarchAccounts, organizationAccounts);
			if(1==i){
				rest.setStatus("0");
				rest.setMessage("方法访问成功！");
			}else{
				rest.setStatus("1");
				rest.setMessage("收藏删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("方法访问失败！");
		}
		return rest;
	}
	
}
