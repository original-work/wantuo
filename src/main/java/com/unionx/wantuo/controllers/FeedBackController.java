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
import com.unionx.wantuo.model.FeedBack;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.FeedBackService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/feedBack")
public class FeedBackController {
	
	private static Logger log = LoggerFactory.getLogger(FeedBackController.class);
	
	@Autowired
	private FeedBackService feedBackService;
	
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * @Title: phoneSave 
	 * @Description: TODO(手机端保存反馈信息) 
	 * @param @param fb
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/phoneSave")
	public Rest phoneSave(FeedBack fb) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fb.setFeedbackDate(formatter.format(new Date()));
			fb.setStatus(2);
			int i=feedBackService.save(fb);
			if(1==i){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			}else{
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_ERROR);
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
	 * @Title: queryByList 
	 * @Description: TODO(后台查询反馈列表) 
	 * @param @param f
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/queryByList")
	public Rest queryByList(FeedBack f,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			f.setStatus(2);
			Pager<FeedBack> pager=feedBackService.queryByList(f,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("FeedBackList",pager.getList());
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
	 * @Title: deleteById 
	 * @Description: TODO(后台批量删除) 
	 * @param @param idStrs
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
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
				FeedBack fb = new FeedBack();
				fb.setId(Integer.parseInt(ids[i]));
				fb.setStatus(1);
				feedBackService.update(fb);
			}
			rest.setStatus("0");
			rest.setMessage("删除成功！");
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("反馈管理");
				sl.setOperation_function("删除");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
			}
		return rest;
	}
}
