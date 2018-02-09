package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Evaluate;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.EvaluateService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
	
	@Autowired
	private EvaluateService evaluateService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * 添加评论
	 * @param e
	 * @return
	 */
	@RequestMapping(value="/phoneAddEvaluate")
	@ResponseBody
	public Rest addEvaluate(Evaluate e){
		Rest rest=new Rest();
		try {
			if("".equals(e.getOrganizationAccounts())||null==e.getOrganizationAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			if("".equals(e.getEvaluate())||null==e.getEvaluate()){
				e.setEvaluate(5);
			}
			e.setStatus(2);
			e.setStatusName("可用");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			e.setCreateDate(formatter.format(new Date()));
			
			
			Evaluate va=new Evaluate();
			va.setOrganizationAccounts(e.getOrganizationAccounts());
			va.setEvaluatePerson(e.getEvaluatePerson());
			SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
			va.setCreateDate(fo.format(new Date()));
			List<Evaluate> list=evaluateService.hasComments(va);
			if(list.size()>=1){
				rest.setStatus("3");
				rest.setMessage("您今天已经评价过了，请明天再来评论。");
			}else{
				int num=evaluateService.saveEvaluate(e);
				if(num>0){
					Organization oz=new Organization();
					Organization org=orgService.selectByLoginAccounts(e.getOrganizationAccounts());
					//计算星级
					double evaluateDub=(org.getEvaluate()*org.getEvaluateNum()+e.getEvaluate())/(org.getEvaluateNum()+1);
					oz.setEvaluate(evaluateDub);
					oz.setEvaluateNum(org.getEvaluateNum()+1);
					oz.setLoginAccounts(org.getLoginAccounts());
					orgService.updateByLoginAccounts(oz);//修改平价星级
					rest.setStatus("0");
					rest.setMessage("评价成功！");
				}else{
					rest.setStatus("2");
					rest.setMessage("评论添加失败！");
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		return rest;
	}
	
	/**
	 * 列表展示评论
	 * @param e
	 * @param c
	 * @return
	 */
	@RequestMapping(value="/phoneEvaluateList")//,method=RequestMethod.POST
	@ResponseBody
	public Rest EvaluateList(Evaluate e,Condition c){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(e.getOrganizationAccounts())||null==e.getOrganizationAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			Pager<Evaluate> list=evaluateService.queryByList(e, c);
			map.put("evaluateList", list.getList());
			map.put("count", list.getCount());
			rest.setStatus("0");
			rest.setMessage("方法访问成功！");
		} catch (Exception e1) {
			e1.printStackTrace();
			rest.setStatus("1");
			rest.setMessage("方法访问失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 后台管理评论列表展示
	 * @param e
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/adminQueryByList",method=RequestMethod.POST)
	public Rest adminQueryByList(Evaluate e){
		Rest rest=new Rest();
		try {
			Map<Object, Object> map=new HashMap<Object,Object>();
			List<Evaluate> list=evaluateService.adminQueryList(e);
			Integer count=evaluateService.adminQueryCount(e);
			map.put("evaluateList", list);
			map.put("count", count);
			rest.setStatus("0");
			rest.setMessage("访问成功");
			rest.setData(map);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rest;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteById",method=RequestMethod.POST)
	public Rest deleteById(HttpServletRequest request,String idStr){
		Rest rest=new Rest();
		String[] ids=null;
		int num=0;
		try {
			if(idStr!=null&&!idStr.equals("")){
				ids=idStr.split(",");
				for (int i = 0; i < ids.length; i++) {
					Evaluate e=new Evaluate();
					e.setId(Integer.parseInt(ids[i]));
					e.setStatus(1);
					num=evaluateService.updateById(e);
				}
				if(num>0){
					rest.setStatus("0");
					rest.setMessage("删除成功！");
					try{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						HttpSession session =request.getSession();
					    Manage manage =(Manage)session.getAttribute("userinfo");
						SystemLog sl = new SystemLog();
						sl.setLogin_accounts(manage.getLogin_accounts());
						sl.setCreate_date(format.format(new Date()));
						sl.setOperation_module("评价管理");
						sl.setOperation_function("删除");
						sl.setName(manage.getManage_name());
						systemLogService.save(sl);
					}catch (Exception e){
					}
				}else{
					rest.setStatus("1");
					rest.setMessage("删除失败！");
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}
}
