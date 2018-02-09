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

import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.Patriarch;
import com.unionx.wantuo.model.Student;
import com.unionx.wantuo.model.StudentSchool;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.PatriarchService;
import com.unionx.wantuo.service.StudentSchoolService;
import com.unionx.wantuo.service.StudentService;
import com.unionx.wantuo.service.TeacherService;

import com.unionx.wantuo.service.SystemLogService;

import com.unionx.wantuo.utils.CommonUtils;

import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private static Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentSchoolService studentSchoolService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private OrganizationService ozService;
	
	@Autowired
	private TeacherService thService;
	
	@Autowired
	private PatriarchService phService;
	/**
	 * @Title: phoneSelect 
	 * @Description: TODO(根据家长查询学生) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelect")
	public Rest phoneSelect(Student s) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(s.getLoginAccounts())||null==s.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写帐号！");
				return rest;
			}
			s.setStatus(2);
			List<Student> studentList=studentService.select(s);
			map.put("studentList", studentList);
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
	 * @Title: phoneStudentSave 
	 * @Description: TODO(添加学生) 
	 * @param @param s
	 * @param @param organizationAccounts
	 * @param @param beginDate
	 * @param @param endDate
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneStudentSave")
	public Rest phoneStudentSave(Student s,String organizationAccounts) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(organizationAccounts)||null==organizationAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			if("".equals(s.getLoginAccounts())||null==s.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写家长帐号！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			s.setCreateDate(formatter.format(Long.parseLong(s.getCreateDate())*1000));
			Patriarch p = new Patriarch();
			Condition c = new Condition();
			p.setStatus(2);
			p.setLoginAccounts(s.getLoginAccounts());
			Pager<Patriarch> pager=phService.queryByList(p,c);
			if(null==pager||null==pager.getCount()||pager.getCount()<1||pager.getList().size()==0){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("家长帐号不存在！");
				rest.setData(map);
				return rest;
			}
			if("".equals(s.getId())||null==s.getId()){
				s.setStatus(2);
				studentService.save(s);	
			}
			if(!"".equals(s.getId())&&null!=s.getId()){
				StudentSchool ss=new StudentSchool();
				ss.setOrganizationAccounts(organizationAccounts);
				ss.setStudentId(s.getId());
				ss.setBeginDate(s.getCreateDate());
				ss.setEndDate(s.getCreateDate());
				ss.setName(s.getName());
				studentSchoolService.save(ss);
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			}else{
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_ERROR);
			}
			try{
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(organizationAccounts);
				sl.setCreate_date(formatter.format(new Date()));
				sl.setOperation_module("学员管理");
				sl.setOperation_function("添加学员");
				Organization o = ozService.selectByLoginAccounts(organizationAccounts);
				sl.setName(o.getOrganization());
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
	 * @Title: phoneGetById 
	 * @Description: TODO(获取单个学生信息) 
	 * @param @param studentId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneGetById")
	public Rest phoneGetById(String studentId,String organizationAccounts){
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(studentId)||null==studentId){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
			if("".equals(organizationAccounts)||null==organizationAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			Student s=studentService.getById(studentId,organizationAccounts);
			s.setCreateDate(s.getCreateDate().substring(0,10));
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("student",s);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}

	/**
	 * @Title: phoneStudentUpdate 
	 * @Description: TODO(修改学生信息) 
	 * @param @param s
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneStudentUpdate")
	public Rest phoneStudentUpdate(Student s,String login) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(s.getId())||null==s.getId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
			if("".equals(s.getLoginAccounts())||null==s.getLoginAccounts()){
				Patriarch p = new Patriarch();
				Condition c = new Condition();
				p.setStatus(2);
				p.setLoginAccounts(s.getLoginAccounts());
				Pager<Patriarch> pager=phService.queryByList(p,c);
				if(null==pager||null==pager.getCount()||pager.getCount()<1||pager.getList().size()==0){
					rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
					rest.setMessage("家长帐号不存在！");
					rest.setData(map);
					return rest;
				}
			}
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
//			int i = studentService.update(s);
			try{
				if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("学员管理");
					sl.setOperation_function("修改学员");
					Organization o = ozService.selectByLoginAccounts(login);
					if(null == o){
						Teacher th=thService.selectByLoginAccounts(login);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					systemLogService.save(sl);
				}
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
	 * @Title: baobiaoList 
	 * @Description: TODO(机构年级报表) 
	 * @param @param s
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/baobiaoList")
	public Rest baobiaoList(Student s,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			s.setStatus(2);
			Pager<Student> pager=studentService.queryByList(s,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("sList",pager.getList());
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
	 * @Title: GradeBaobiao 
	 * @Description: TODO(导出机构年级报表) 
	 * @param @param response
	 * @param @param s
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/GradeBaobiao")
	public Rest GradeBaobiao(HttpServletResponse response,Student s,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			s.setStatus(2);
			s.setLoginAccounts(new String(s.getLoginAccounts().getBytes("iso-8859-1"),"UTF-8"));
			Pager<Student> pager=studentService.queryByList(s,c);
			HSSFWorkbook wb=CommonUtils.ExportGrade(pager.getList());
			response.setContentType("application/x-msdownload");
		    response.setHeader("Content-Disposition","attachment;filename=gradeReport.xls");
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

