package com.unionx.wantuo.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.utils.CommonUtils;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Rest;

@RequestMapping("/SendSMS")
@Controller
public class SendSMSController {
	@Autowired
	private LoginService loginService;
	/**
	 * 注册发送手机验证码
	 * @param phone
	 * @return
	 */

	@RequestMapping(value="/phoneSendSMSToRegister")//,method=RequestMethod.POST
	@ResponseBody
	public Rest sendSMSToRegister(String phone){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(phone)||null==phone){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage("请填写手机号！");
			return rest;
		}
			Login login=loginService.findToRegister(phone,null);
			if(login==null||"".equals(login.toString())){
			String code=CommonUtils.getCode();
			String result=CommonUtils.sendNote(phone, code);//发送消息
			if(result=="0"||result.equals("0")){
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
				map.put("verificatCode", code);
			}else{
				rest.setStatus("1");
				rest.setMessage("方法请求失败！");
			}
			}else{
				rest.setStatus("1");
				rest.setMessage("该手机号已注册！");
				return rest;
			}
		} catch (UnsupportedEncodingException e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	
	
	/**
	 * 找回密码发送手机验证码
	 * @param phone
	 * @return
	 */

	@RequestMapping(value="/phoneSendSMSToFind")//,method=RequestMethod.POST
	@ResponseBody
	public Rest phoneSendSMSToFind(String phone){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		try {
			if("".equals(phone)||null==phone){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage("请填写手机号！");
			return rest;
		}
			Login login=loginService.findToRegister(phone,null);
			if(null!=login&&null!=login.getId()&&null!=login.getLoginAccounts()&&!"".equals(login.getLoginAccounts())){
			String code=CommonUtils.getCode();
			String result=CommonUtils.sendNote(phone, code);//发送消息
			if(result=="0"||result.equals("0")){
				rest.setStatus("0");
				rest.setMessage("方法请求成功！");
				map.put("verificatCode", code);
			}else{
				rest.setStatus("1");
				rest.setMessage("方法请求失败！");
			}
			}else{
				rest.setStatus("1");
				rest.setMessage("该手机号不存在！");
				return rest;
			}
		} catch (UnsupportedEncodingException e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
}
	}
