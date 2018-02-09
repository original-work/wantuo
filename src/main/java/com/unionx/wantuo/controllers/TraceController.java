package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.Student;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.model.Trace;
import com.unionx.wantuo.model.TraceAverage;
import com.unionx.wantuo.model.TraceMap;
import com.unionx.wantuo.model.TraceShow;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.StudentService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.service.TeacherService;
import com.unionx.wantuo.service.TraceService;
import com.unionx.wantuo.utils.CommonUtils;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.PatriarchGerenPush;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/trace")
public class TraceController {
	
	private static Logger log = LoggerFactory.getLogger(TraceController.class);
	
	@Autowired
	private TraceService traceService;
	
	@Autowired
	private TeacherService thService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private OrganizationService ozService;
	
	@Autowired
	private StudentService studentService;
	/**
	 * @Title: phoneSave 
	 * @Description: TODO(手机端添加追踪信息) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSave")
	public Rest phoneSave(Trace t,String loginin) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(t.getOrganizationAccounts())||null==t.getOrganizationAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			if("".equals(t.getCreateDate())||null==t.getCreateDate()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写时间！");
				return rest;
			}
			if("".equals(t.getStudentId())||null==t.getStudentId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
//			if("".equals(t.getSigninPerson())||null==t.getSigninPerson()){
//				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
//				rest.setMessage("请填写签到人！");
//				return rest;
//			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			t.setCreateDate(formatter.format(Long.parseLong(t.getCreateDate())*1000));
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			t.setSigninDate(formatter1.format(new Date()));
			Trace t1=traceService.getById(t);
			if(null!=t1&&null!=t1.getId()&&!"".equals(t1.getId())){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("该学生今日追踪已存在！");
				return rest;
			}
			int i=traceService.save(t);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			t=traceService.getById(t);
			if(null!=t){
				if(null!=t.getSigninDate()&&t.getSigninDate().length()>10){
					t.setSigninDate(t.getSigninDate().substring(0,t.getSigninDate().length()-2));
				}
				if(null!=t.getLeaveDate()&&t.getLeaveDate().length()>10){
					t.setLeaveDate(t.getLeaveDate().substring(0,t.getLeaveDate().length()-2));
				}
				if(null!=t.getSummaryDate()&&t.getSummaryDate().length()>10){
					t.setSummaryDate(t.getSummaryDate().substring(0,t.getSummaryDate().length()-2));
				}
			}
			map.put("trace", t);
			if(null!=t.getStatus()&&!"".equals(t.getStatus())&&i==1){
				Login l=traceService.sSelectPushP(t.getStudentId());
				StringBuilder payload = new StringBuilder();
				payload.append("{");
				payload.append("\"type\":"+"\"1\"");
				payload.append(",");
				payload.append("\"childId\":\""+t.getStudentId()+"\"");	
				payload.append(",");
				payload.append("\"createTime\":\""+formatter.format(new Date())+"\"");
				payload.append("}");
				if(null!=l&&!"".equals(l.getDeviceToken())&&null!=l.getDeviceToken()){
					if(null!=t.getStatus()&&t.getStatus().equals(1)){
						PatriarchGerenPush.apnpush(l.getDeviceToken(),ConstantUtil.push_qt,1,payload.toString());
					}
					if(null!=t.getStatus()&&t.getStatus().equals(4)){
						PatriarchGerenPush.apnpush(l.getDeviceToken(),ConstantUtil.push_qq,1,payload.toString());
					}
				}
				try{
					if(null!=t.getStatus()&&!"".equals(loginin)&&null!=loginin){
						SystemLog sl = new SystemLog();
						sl.setLogin_accounts(loginin);
						Organization o = ozService.selectByLoginAccounts(loginin);
						if(null == o){
							Teacher th=thService.selectByLoginAccounts(loginin);
							sl.setName(th.getName());
						}else{
							sl.setName(o.getOrganization());
						}
						sl.setCreate_date(formatter1.format(new Date()));
						sl.setOperation_module("每日跟踪");
						if(t.getStatus().equals(1)){
							sl.setOperation_function("签到");
						}
					    if(t.getStatus().equals(4)){
							sl.setOperation_function("缺勤");
						}
						systemLogService.save(sl);
					}
				}catch (Exception e){
				}
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
	 * @Title: phoneGetById 
	 * @Description: TODO(手机端获取每日追踪信息) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneGetById")
	public Rest phoneGetById(Trace t) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(t.getCreateDate())||null==t.getCreateDate()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写查询时间！");
				return rest;
			}
			if("".equals(t.getStudentId())||null==t.getStudentId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			t.setCreateDate(formatter.format(Long.parseLong(t.getCreateDate())*1000));
			t=traceService.getById(t);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			if(null!=t){
				if(null!=t.getSigninDate()&&t.getSigninDate().length()>10){
					t.setSigninDate(t.getSigninDate().substring(0,t.getSigninDate().length()-2));
				}
				if(null!=t.getLeaveDate()&&t.getLeaveDate().length()>10){
					t.setLeaveDate(t.getLeaveDate().substring(0,t.getLeaveDate().length()-2));
				}
				if(null!=t.getSummaryDate()&&t.getSummaryDate().length()>10){
					t.setSummaryDate(t.getSummaryDate().substring(0,t.getSummaryDate().length()-2));
				}
			}
			map.put("trace",t);
		}catch(Exception e){
			e.printStackTrace();
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneUpdate 
	 * @Description: TODO(手机端修改跟踪信息) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneUpdate")
	public Rest phoneUpdate(Trace t,String loginin) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ms="";
			if("".equals(t.getId())||null==t.getId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写id！");
				return rest;
			}
//			if("".equals(t.getStatus())||null==t.getStatus()){
//				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
//				rest.setMessage("请填写状态！");
//				return rest;
//			}
			if(null!=t.getStatus()&&!"".equals(t.getStatus())&&t.getStatus().equals(2)){
				if("".equals(t.getSummaryPerson())||null==t.getSummaryPerson()){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage("请填写总结人！");
					return rest;
				}
				t.setSummaryDate(formatter.format(new Date()));
				ms=ConstantUtil.push_zj;
			}
			if(null!=t.getStatus()&&!"".equals(t.getStatus())&&t.getStatus().equals(3)){
				if("".equals(t.getLeavePerson())||null==t.getLeavePerson()){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage("请填写离校人！");
					return rest;
				}
				t.setLeaveDate(formatter.format(new Date()));
				ms=ConstantUtil.push_lx;
			}
			traceService.update(t);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_SUCCESS);
			Trace t1=traceService.getById(t);
			if(null!=t1){
				if(null!=t1.getSigninDate()&&t1.getSigninDate().length()>10){
					t1.setSigninDate(t1.getSigninDate().substring(0,t1.getSigninDate().length()-2));
				}
				if(null!=t1.getLeaveDate()&&t1.getLeaveDate().length()>10){
					t1.setLeaveDate(t1.getLeaveDate().substring(0,t1.getLeaveDate().length()-2));
				}
				if(null!=t1.getSummaryDate()&&t1.getSummaryDate().length()>10){
					t1.setSummaryDate(t1.getSummaryDate().substring(0,t1.getSummaryDate().length()-2));
				}
			}
			map.put("trace", t1);
			if(null!=t.getStatus()&&!"".equals(t.getStatus())){
			Login l=traceService.sSelectPushP(t.getStudentId());
			StringBuilder payload = new StringBuilder();
			payload.append("{");
			payload.append("\"type\":"+"\"1\"");
			payload.append(",");
			payload.append("\"childId\":\""+t.getStudentId()+"\"");	
			payload.append(",");
			payload.append("\"createTime\":\""+formatter.format(new Date())+"\"");
			payload.append("}");
			if(null!=l&&!"".equals(l.getDeviceToken())&&null!=l.getDeviceToken()){
				PatriarchGerenPush.apnpush(l.getDeviceToken(),ms,1,payload.toString());
			}
			try{
				if(!"".equals(loginin)&&null!=loginin){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(loginin);
					Organization o = ozService.selectByLoginAccounts(loginin);
					if(null == o){
						Teacher th=thService.selectByLoginAccounts(loginin);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("每日跟踪");
					if(t.getStatus().equals(2)){
						sl.setOperation_function("总结");
					}
				    if(t.getStatus().equals(3)){
						sl.setOperation_function("离校");
					}
					systemLogService.save(sl);	
			    }
			}catch (Exception e){
			}
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneSelectSubjectGrade 
	 * @Description: TODO(手机端根据学科月份查询学生成绩曲线图) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelectSubjectGrade")
	public Rest phoneSelectSubjectGrade(Trace t) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			if("".equals(t.getSubject())||null==t.getSubject()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请选择科目！");
				return rest;
			}
			if("".equals(t.getCreateDate())||null==t.getCreateDate()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写时间！");
				return rest;
			}
			if("".equals(t.getStudentId())||null==t.getStudentId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请学生id！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
			t.setCreateDate(formatter.format(Long.parseLong(t.getCreateDate())*1000));
			List<TraceMap> traceList=traceService.selectSubjectGrade(t);
			map.put("traceList", traceList);
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
	 * @Title: phoneSelectShow 
	 * @Description: TODO(手机端根据月份查询行为曲线、学习曲线曲线图) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelectShow")
	public Rest phoneSelectShow(Trace t) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			if("".equals(t.getCreateDate())||null==t.getCreateDate()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写时间！");
				return rest;
			}
			if("".equals(t.getStudentId())||null==t.getStudentId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请学生id！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
			t.setCreateDate(formatter.format(Long.parseLong(t.getCreateDate())*1000));
			List<TraceShow> traceList=traceService.phoneSelectShow(t);
			List<TraceAverage> list=traceService.phoneAverage();//查询所有学生平均评价
			int i=0;
			double average=0;
			for(TraceAverage l : list){
				if(l.getStudentId().equals(t.getStudentId())){
					double number=list.size();
					average=(number-i)/number;
				}
				i++;
			}
			map.put("traceList", traceList);
			map.put("average", (int)(average*100));
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
	 * @Title: phoneSelectShowAverage 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param t
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelectShowAverage")
	public Rest phoneSelectShowAverage(Trace t) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			if("".equals(t.getCreateDate())||null==t.getCreateDate()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写时间！");
				return rest;
			}
			if("".equals(t.getStudentId())||null==t.getStudentId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请学生id！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
			t.setCreateDate(formatter.format(Long.parseLong(t.getCreateDate())*1000));
			Student s=studentService.queryById(t.getStudentId());
//			List<TraceShow> traceList=traceService.phoneSelectShow(t);
			List<TraceAverage> list=traceService.phoneAverageWhere(s.getGrade());//查询所有学生平均评价
			int i=0;
			double average=0;
			for(TraceAverage l : list){
				if(l.getStudentId().equals(t.getStudentId())){
					double number=list.size();
					average=(number-i)/number;
				}
				i++;
			}
//			double ati=0;
			List<TraceAverage> lta =traceService.phoneSelectShowAverage(t);
/*			List<TraceAverage> lta = new ArrayList<TraceAverage>();
			double ls=0;
			for(TraceShow tl : traceList){
				TraceAverage at = new TraceAverage();
				ati++;
				at.setAverage((Double.valueOf(tl.getBehavior())+Double.valueOf(tl.getStudy())+ls)/(ati*2));
				ls=at.getAverage()*ati*2;
				at.setCreateDate(tl.getCreateDate());
				lta.add(at);
			}*/
			map.put("traceList", lta);
			map.put("average", (int)(average*100));
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
	 * @Title: queryByList 
	 * @Description: TODO(签到报表) 
	 * @param @param t
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/queryByList")
	public Rest queryByList(Trace t,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			Pager<Trace> pager=traceService.queryByList(t,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("traceList",pager.getList());
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
	 * @Title: ExportStudent 
	 * @Description: TODO(导出签到报表) 
	 * @param @param response
	 * @param @param t
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/ExportStudent")
	public Rest ExportStudent(HttpServletResponse response,Trace t,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			t.setClassName(new String(t.getClassName().getBytes("iso-8859-1"),"UTF-8"));
			t.setOrganizationName(new String(t.getOrganizationName().getBytes("iso-8859-1"),"UTF-8"));
			Pager<Trace> pager=traceService.queryByList(t,c);
			HSSFWorkbook wb=CommonUtils.ExportStudent(pager.getList());
			response.setContentType("application/x-msdownload");
		    response.setHeader("Content-Disposition","attachment;filename=studentSignReport.xls");
			wb.write(response.getOutputStream());
			rest.setStatus("0");
			rest.setMessage("导出成功");
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
}
