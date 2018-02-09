package com.unionx.wantuo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.service.JiGouGuanLiService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/jigouguanli")
public class JiGouGuanLiController {

	
	@Autowired
	private JiGouGuanLiService jiGouGuanLiService;
	
	
	/**
	 * 获得列表
	 * @param organization
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getList")
	public Rest getAllList(Organization organization,Condition condition){
//		System.out.println("page:"+condition.getPage());
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			organization.setIdCard(null);
			Pager<Organization> queryList = jiGouGuanLiService.queryByListWeb(organization, condition);
//			System.out.println("size:"+queryList.getList().size());
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
	
	
	@ResponseBody
	@RequestMapping("/queryList")
	public Rest queryList(Organization organization,Condition condition){
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try {
			organization.setIdCard("2");
			Pager<Organization> queryList = jiGouGuanLiService.queryByListWeb(organization, condition);
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
}
